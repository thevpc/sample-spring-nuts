package net.thevpc.samples.springnuts.core.service.impl.service;

import net.thevpc.nuts.util.NOptional;
import net.thevpc.samples.springnuts.core.model.common.AppException;
import net.thevpc.samples.springnuts.core.model.entity.AppUser;
import net.thevpc.samples.springnuts.core.model.entity.AppUserRole;
import net.thevpc.samples.springnuts.core.model.payload.AppPrincipalResponse;
import net.thevpc.samples.springnuts.core.model.payload.LoginRequest;
import net.thevpc.samples.springnuts.core.model.payload.SignupRequest;
import net.thevpc.samples.springnuts.core.service.impl.security.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class AuthService {
    @Autowired
    @Lazy
    private AppUserService appUserService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder encoder;

    @Autowired
    private AppTokenGenerator appTokenGenerator;

    public AppPrincipalResponse login(LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = appTokenGenerator.createToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(item -> item.getAuthority())
                .collect(Collectors.toList());

        return new AppPrincipalResponse(jwt,
                userDetails.getId(),
                userDetails.getUsername(),
                userDetails.getEmail(),
                roles);
    }

    public void signup(SignupRequest signUpRequest) {
        NOptional<AppUser> userByNameOrEmail = appUserService.findUserByNameOrEmailWithPassword(signUpRequest.getUsername(), signUpRequest.getEmail());
        if (userByNameOrEmail.isPresent()) {
            throw new AppException("A001", "Username or email already in use");
        }

        AppUser user = new AppUser()
                .setUsername(signUpRequest.getUsername())
                .setEmail(signUpRequest.getEmail())
                .setPassword(signUpRequest.getPassword())
                .setEnabled(true);

        Set<String> strRoles = signUpRequest.getRole();
        Set<AppUserRole> roles = new HashSet<>();

        if (strRoles != null) {
            strRoles.forEach(role -> {
                AppUserRole adminRole = appUserService.findRoleByName(role).orElse(null);
                if (adminRole != null) {
                    roles.add(adminRole);
                }
            });
        }
        user.setRoles(roles);
        appUserService.addUser(user);
    }


}
