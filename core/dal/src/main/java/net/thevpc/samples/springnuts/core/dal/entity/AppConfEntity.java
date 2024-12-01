package net.thevpc.samples.springnuts.core.dal.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(
        name = "AppConf",
        uniqueConstraints = {
                @UniqueConstraint(columnNames = "name"),
                @UniqueConstraint(columnNames = "uuid")
        })
public class AppConfEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    @Size(max = 30)
    private String tenant;

    @NotBlank
    @Size(max = 255)
    private String groupName;

    @NotBlank
    @Size(max = 255)
    private String name;

    @Size(max = 3000)
    private String value;

    private boolean enabled;

    public Long getId() {
        return id;
    }

    public AppConfEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTenant() {
        return tenant;
    }

    public AppConfEntity setTenant(String tenant) {
        this.tenant = tenant;
        return this;
    }

    public String getGroupName() {
        return groupName;
    }

    public AppConfEntity setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public String getName() {
        return name;
    }

    public AppConfEntity setName(String name) {
        this.name = name;
        return this;
    }

    public String getValue() {
        return value;
    }

    public AppConfEntity setValue(String value) {
        this.value = value;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public AppConfEntity setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public AppConfEntity copy() {
        AppConfEntity c = new AppConfEntity();
        c.id = id;
        c.tenant = tenant;
        c.name = name;
        c.groupName = groupName;
        c.value = value;
        c.enabled = enabled;
        return c;
    }
}
