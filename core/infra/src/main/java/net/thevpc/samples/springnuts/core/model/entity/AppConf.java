package net.thevpc.samples.springnuts.core.model.entity;

public class AppConf {
    private Long id;

    private String tenant;

    private String groupName;

    private String name;

    private String value;

    private boolean enabled;

    public Long getId() {
        return id;
    }

    public AppConf setId(Long id) {
        this.id = id;
        return this;
    }

    public String getTenant() {
        return tenant;
    }

    public AppConf setTenant(String tenant) {
        this.tenant = tenant;
        return this;
    }

    public String getGroupName() {
        return groupName;
    }

    public AppConf setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public String getName() {
        return name;
    }

    public AppConf setName(String name) {
        this.name = name;
        return this;
    }

    public String getValue() {
        return value;
    }

    public AppConf setValue(String value) {
        this.value = value;
        return this;
    }

    public boolean isEnabled() {
        return enabled;
    }

    public AppConf setEnabled(boolean enabled) {
        this.enabled = enabled;
        return this;
    }

    public AppConf copy() {
        AppConf c = new AppConf();
        c.id = id;
        c.tenant = tenant;
        c.name = name;
        c.groupName = groupName;
        c.value = value;
        c.enabled = enabled;
        return c;
    }
}
