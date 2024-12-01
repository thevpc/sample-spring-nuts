package net.thevpc.samples.springnuts.core.model.common;

import net.thevpc.samples.springnuts.core.model.entity.AppLog;
import net.thevpc.samples.springnuts.core.util.ValidationUtils;
import net.thevpc.nuts.util.NMsg;

import java.io.Serializable;

public class AppLogBuilder implements Serializable {

    private static final long serialVersionUID = 1L;

    private String account;
    private AppLogLevel level;
    private AppLogVerb verb;
    private String message;
    private String description;
    private String groupName;
    private String code;
    private String tenant;

    public static AppLogBuilder ofInfo(AppLogVerb verb, String message) {
        return of(verb, message).setLevel(AppLogLevel.INFO);
    }

    public static AppLogBuilder ofInfo(AppLogVerb verb, NMsg message) {
        return of(verb, message == null ? null : message.toString()).setLevel(AppLogLevel.INFO);
    }

    public static AppLogBuilder ofError(AppLogVerb verb, NMsg message) {
        return ofError(verb, message == null ? null : message.toString());
    }

    public static AppLogBuilder ofError(AppLogVerb verb, String message) {
        return of(verb, message).setLevel(AppLogLevel.SEVERE);
    }

    public static AppLogBuilder ofError(AppLogVerb verb, NMsg message, Throwable throwable) {
        return ofError(verb, message == null ? null : message.toString(), throwable);
    }

    public static AppLogBuilder ofError(AppLogVerb verb, String message, Throwable throwable) {
        if (throwable == null) {
            return of(verb, message).setLevel(AppLogLevel.SEVERE);
        } else {
            return of(verb, message + " : " + throwable.getMessage())
                    .setDescription(throwable)
                    .setLevel(AppLogLevel.SEVERE);
        }
    }


    public static AppLogBuilder of(AppLogVerb verb, String message) {
        return new AppLogBuilder().setMessage(message).setVerb(verb);
    }

    public static AppLogBuilder of(AppLogVerb verb, NMsg message) {
        return new AppLogBuilder().setMessage(message == null ? null : message.toString()).setVerb(verb);
    }

    public String getCode() {
        return code;
    }

    public AppLogBuilder setCode(String code) {
        this.code = code;
        return this;
    }

    public static AppLogBuilder of(String message) {
        return new AppLogBuilder().setMessage(message);
    }

    public String getAccount() {
        return account;
    }

    public AppLogBuilder setAccount(String account) {
        this.account = account;
        return this;
    }

    public AppLogLevel getLevel() {
        return level;
    }

    public AppLogBuilder setLevel(AppLogLevel level) {
        this.level = level;
        return this;
    }



    public String getTenant() {
        return tenant;
    }

    public AppLogBuilder setTenant(String tenant) {
        this.tenant = tenant;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public AppLogBuilder setMessage(String message) {
        this.message = message;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AppLogBuilder setDescription(Throwable description) {
        if (description == null) {
            this.description = null;
        } else {
            this.description = ValidationUtils.stacktrace(description);
        }
        return this;
    }

    public AppLogBuilder setDescription(String description) {
        this.description = description;
        return this;
    }

    public AppLogVerb getVerb() {
        return verb;
    }

    public AppLogBuilder setVerb(AppLogVerb verb) {
        this.verb = verb;
        return this;
    }

    public String getGroupName() {
        return groupName;
    }

    public AppLogBuilder setGroupName(String groupName) {
        this.groupName = groupName;
        return this;
    }

    public AppLog build() {
        AppLog item = new AppLog();
        item.setMessage(getMessage());
        item.setDescription(getDescription());
        item.setAccount(getAccount());
        item.setLevel(getLevel());
        item.setTenant(getTenant());
        item.setVerb(getVerb());
        item.setGroupName(getGroupName());
        item.setCode(getCode());
        return item;
    }
}
