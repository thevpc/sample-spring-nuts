package net.thevpc.samples.springnuts.core.dal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(
        name = "AppUser",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "username"),
                @UniqueConstraint(columnNames = "email"),
                @UniqueConstraint(columnNames = "uuid")
        })
public class AppUserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String uuid;

    @NotBlank
    @Size(max = 30)
    private String username;

    @NotBlank
    @Size(max = 80)
    @Email
    private String email;

    @Size(max = 120)
    private String password;

    @Transient
    private String plainPassword;

    private boolean enabled;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "app_user_roles",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "role_id"))
    private Set<AppUserRoleEntity> roles = new HashSet<>();

    public AppUserEntity() {
    }

    public String getUuid() {
        return uuid;
    }

    public AppUserEntity setUuid(String uuid) {
        this.uuid = uuid;
        return this;
    }

    public Long getId() {
        return id;
    }

    public AppUserEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getUsername() {
        return username;
    }

    public AppUserEntity setUsername(String username) {
        this.username = username;
        return this;
    }

    public String getEmail() {
        return email;
    }

    public AppUserEntity setEmail(String email) {
        this.email = email;
        return this;
    }

    public String getPassword() {
        return password;
    }

    public AppUserEntity setPassword(String password) {
        this.password = password;
        return this;
    }

    public Set<AppUserRoleEntity> getRoles() {
        return roles;
    }

    public AppUserEntity setRoles(Set<AppUserRoleEntity> roles) {
        this.roles = roles;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public AppUserEntity setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public String getPlainPassword() {
        return plainPassword;
    }

    public AppUserEntity setPlainPassword(String plainPassword) {
        this.plainPassword = plainPassword;
        return this;
    }
}
