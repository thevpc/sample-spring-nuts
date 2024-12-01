package net.thevpc.samples.springnuts.core.service.impl.service;

import org.springframework.security.core.Authentication;

public interface AppTokenGenerator {
    String createToken(Authentication authentication);
}
