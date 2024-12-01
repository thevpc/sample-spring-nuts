package net.thevpc.samples.springnuts.core.dal.entity;

import net.thevpc.samples.springnuts.core.model.common.AppLogLevel;
import net.thevpc.samples.springnuts.core.model.common.AppLogVerb;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.time.Instant;

@Entity
@Table(name = "AppLog")
public class AppLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String tenant;

    @Temporal(TemporalType.TIMESTAMP)
    private Instant date;


    @Enumerated(EnumType.STRING)
    private AppLogLevel level;

    private int levelValue;
    @Enumerated(EnumType.STRING)
    private AppLogVerb verb;

    @NotBlank
    @Size(max = 30)
    private String groupName;

    @NotBlank
    @Size(max = 255)
    private String code;

    @NotBlank
    @Size(max = 1024)
    private String message;

    @Size(max = 8192)
    private String description;

    @Size(max = 64)
    private String account;

    public static AppLogEntity ofError(Throwable th){
        AppLogEntity l=new AppLogEntity();
        Throwable th2=th;
        while(th2.getCause()!=null && !th2.getCause().equals(th)){
            th2=th2.getCause();
        }
        String msg = th2.getMessage();
        if(msg==null || msg.length()==0){
            msg=th2.toString();
        }
        l.setMessage(msg);
        return l;
    }

    public String getTenant() {
        return tenant;
    }

    public AppLogEntity setTenant(String tenant) {
        this.tenant = tenant;
        return this;
    }

    public Long getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public AppLogEntity setAccount(String account) {
        this.account = account;
        return this;
    }

    public AppLogEntity setId(Long id) {
        this.id = id;
        return this;
    }

    public String getGroupName() {
        return groupName;
    }

    public AppLogEntity setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public AppLogEntity setMessage(String message) {
        this.message = message;
        return this;
    }

    public AppLogLevel getLevel() {
        return level;
    }

    public AppLogEntity setLevel(AppLogLevel level) {
        this.level = level;
        return this;
    }

    public int getLevelValue() {
        return levelValue;
    }

    public AppLogEntity setLevelValue(int levelValue) {
        this.levelValue = levelValue;
        return this;
    }

    public String getCode() {
        return code;
    }

    public AppLogEntity setCode(String code) {
        this.code = code;
        return this;
    }

    public Instant getDate() {
        return date;
    }

    public AppLogEntity setDate(Instant date) {
        this.date = date;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AppLogEntity setDescription(String description) {
        this.description = description;
        return this;
    }

    public AppLogVerb getVerb() {
        return verb;
    }

    public AppLogEntity setVerb(AppLogVerb verb) {
        this.verb = verb;
        return this;
    }
}
