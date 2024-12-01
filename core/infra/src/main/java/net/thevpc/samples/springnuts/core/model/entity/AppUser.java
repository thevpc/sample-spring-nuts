package net.thevpc.samples.springnuts.core.model.entity;

import java.util.HashSet;
import java.util.Set;

public class AppUser {
  private Long id;
  private String uuid;

  private String username;

  private String email;

  private String password;

  private boolean enabled;

  private Set<AppUserRole> roles = new HashSet<>();

  public AppUser() {
  }

  public String getUuid() {
    return uuid;
  }

  public AppUser setUuid(String uuid) {
    this.uuid = uuid;
    return this;
  }

  public Long getId() {
    return id;
  }

  public AppUser setId(Long id) {
    this.id = id;
    return this;
  }

  public String getUsername() {
    return username;
  }

  public AppUser setUsername(String username) {
    this.username = username;
    return this;
  }

  public String getEmail() {
    return email;
  }

  public AppUser setEmail(String email) {
    this.email = email;
    return this;
  }

  public Set<AppUserRole> getRoles() {
    return roles;
  }

  public AppUser setRoles(Set<AppUserRole> roles) {
    this.roles = roles;
    return this;
  }

  public boolean isEnabled() {
    return enabled;
  }

  public AppUser setEnabled(boolean enabled) {
    this.enabled = enabled;
    return this;
  }

  public String getPassword() {
    return password;
  }

  public AppUser setPassword(String password) {
    this.password = password;
    return this;
  }
}
