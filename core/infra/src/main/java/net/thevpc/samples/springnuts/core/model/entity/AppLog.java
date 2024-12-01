package net.thevpc.samples.springnuts.core.model.entity;

import net.thevpc.samples.springnuts.core.model.common.AppLogLevel;
import net.thevpc.samples.springnuts.core.model.common.AppLogVerb;

import java.time.Instant;

public class AppLog {
    private Long id;
    private String tenant;

    private Instant date;


    private AppLogLevel level;

    private int levelValue;
    private AppLogVerb verb;

    private String groupName;

    private String code;

    private String message;

    private String description;

    private String account;

    public static AppLog ofError(Throwable th){
        AppLog l=new AppLog();
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

    public AppLog setTenant(String tenant) {
        this.tenant = tenant;
        return this;
    }

    public Long getId() {
        return id;
    }

    public String getAccount() {
        return account;
    }

    public AppLog setAccount(String account) {
        this.account = account;
        return this;
    }

    public AppLog setId(Long id) {
        this.id = id;
        return this;
    }

    public String getGroupName() {
        return groupName;
    }

    public AppLog setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public AppLog setMessage(String message) {
        this.message = message;
        return this;
    }

    public AppLogLevel getLevel() {
        return level;
    }

    public AppLog setLevel(AppLogLevel level) {
        this.level = level;
        return this;
    }

    public int getLevelValue() {
        return levelValue;
    }

    public AppLog setLevelValue(int levelValue) {
        this.levelValue = levelValue;
        return this;
    }

    public String getCode() {
        return code;
    }

    public AppLog setCode(String code) {
        this.code = code;
        return this;
    }

    public Instant getDate() {
        return date;
    }

    public AppLog setDate(Instant date) {
        this.date = date;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AppLog setDescription(String description) {
        this.description = description;
        return this;
    }

    public AppLogVerb getVerb() {
        return verb;
    }

    public AppLog setVerb(AppLogVerb verb) {
        this.verb = verb;
        return this;
    }
}
