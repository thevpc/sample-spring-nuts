package net.thevpc.samples.springnuts.core.web.ws;

import net.thevpc.samples.springnuts.core.model.payload.*;
import net.thevpc.samples.springnuts.core.service.api.CoreModule;
import net.thevpc.samples.springnuts.core.service.impl.service.AuthService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
public class CoreWS {
    @Autowired
    private CoreModule module;

    @Autowired
    private AuthService authService;

    @RequestMapping(value = "/version",method = {RequestMethod.GET,RequestMethod.POST})
    private VersionResponse version() {
        return new VersionResponse();
    }


    @PostMapping("/login")
    public AppPrincipalResponse authenticateUser(@RequestBody LoginRequest loginRequest) {
        return authService.login(loginRequest);
    }

    @PostMapping("/signup")
    public ResponseEntity<?> registerUser(@RequestBody SignupRequest signUpRequest) {
        module.signup(signUpRequest);
        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
