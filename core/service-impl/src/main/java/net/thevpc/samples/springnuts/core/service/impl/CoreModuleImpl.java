package net.thevpc.samples.springnuts.core.service.impl;

import net.thevpc.samples.springnuts.core.service.api.CoreModule;
import net.thevpc.samples.springnuts.core.service.impl.service.AuthService;
import net.thevpc.samples.springnuts.core.service.impl.service.AppUserService;
import lombok.experimental.Delegate;
import org.springframework.stereotype.Service;

@Service
public class CoreModuleImpl implements CoreModule {
    @Delegate
    private AuthService authService;

    @Delegate
    private AppUserService appUserService;

}
