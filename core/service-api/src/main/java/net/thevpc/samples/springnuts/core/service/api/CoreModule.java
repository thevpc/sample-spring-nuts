package net.thevpc.samples.springnuts.core.service.api;

import net.thevpc.samples.springnuts.core.model.payload.*;

public interface CoreModule {
    AppPrincipalResponse login(LoginRequest request);


    VersionResponse version();

    void signup(SignupRequest signUpRequest);
}
