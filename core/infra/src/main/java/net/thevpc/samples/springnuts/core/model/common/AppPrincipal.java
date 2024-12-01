package net.thevpc.samples.springnuts.core.model.common;


import net.thevpc.samples.springnuts.core.model.entity.AppUser;

public interface AppPrincipal {
    AppUser getUser();
    
    Long getId();

    String getEmail();

    String getPassword();

    String getUsername();
}
