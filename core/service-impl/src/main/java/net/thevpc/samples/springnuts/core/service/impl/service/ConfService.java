package net.thevpc.samples.springnuts.core.service.impl.service;

import net.thevpc.samples.springnuts.core.ann.Secret;
import net.thevpc.samples.springnuts.core.dal.entity.AppConfEntity;
import net.thevpc.samples.springnuts.core.dal.repository.AppConfEntityRepository;
import net.thevpc.samples.springnuts.core.service.impl.security.AppSecurityUtils;
import net.thevpc.nuts.util.NAssert;
import net.thevpc.nuts.util.NOptional;
import net.thevpc.nuts.util.NStringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.lang.reflect.Field;
import java.util.List;

@Service
public class ConfService {
    private static final String DEFAULT_MASTER_PASSWORD = "rombatakaya1243!";
    @Value("${app.security.master-secret:}")
    private String masterSecret;

    @Autowired
    @Lazy
    private AppConfEntityRepository confRepository;

    public void unsetConf(String groupName, String name) {
        unsetConf(null, groupName, name);
    }

    public void unsetConf(String tenant, String groupName, String name) {
        tenant = resolveTenant(tenant);
        List<AppConfEntity> old = confRepository.findByTenantAndGroupNameAndName(tenant, groupName, name);
        confRepository.deleteAll(old);
    }

    private static String resolveTenant(String tenant) {
        return NStringUtils.firstNonBlank(NStringUtils.trim(tenant), "ALL");
    }

    public void setConfValue(String groupName, String name, String value) {
        setConfValue(null, groupName, name, value);
    }

    public void setConfStruct(String groupName, String name, Object value, boolean enabled) {
        setConfStruct(null, groupName, name, value, enabled);
    }

    public void setConfStruct(String tenant, String groupName, String name, Object value, boolean enabled) {
        Class<?> clazz = value.getClass();
        while(clazz != null) {
            for (Field f : clazz.getDeclaredFields()) {
                f.setAccessible(true);
                Object u = null;
                try {
                    u = f.get(value);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
                String s = Stringifier.INSTANCE.toString(u);
                if (s != null) {
                    Secret sec = f.getAnnotation(Secret.class);
                    if (sec != null) {
                        s = AppSecurityUtils.encryptString(s, resolveSecret(null));
                    }
                }
                setConfValue(tenant, groupName, name + "." + f.getName(), s, enabled);
            }
            clazz=clazz.getSuperclass();
        }
    }

    public void setConfValue(String tenant, String groupName, String name, String value) {
        NAssert.requireNonBlank(groupName, "group");
        NAssert.requireNonBlank(name, "name");
        tenant = resolveTenant(tenant);
        List<AppConfEntity> old = confRepository.findByTenantAndGroupNameAndName(tenant, groupName, name);
        if (old.isEmpty()) {
            AppConfEntity conf = new AppConfEntity();
            conf.setTenant(tenant);
            conf.setGroupName(groupName);
            conf.setName(name);
            conf.setValue(value);
            conf.setEnabled(true);
            confRepository.save(conf);
        } else {
            AppConfEntity conf = old.get(0);
            conf.setValue(value);
            //conf.setEnabled(true);
            confRepository.save(conf);
        }
    }

    public void setConfPassword(String groupName, String name, String value, String secret, boolean enabled) {
        setConfPassword(null, groupName, name, value, secret, enabled);
    }

    public void setConfPassword(String tenant, String groupName, String name, String value, String secret, boolean enabled) {
        String encryptedValue = (value == null || value.isEmpty()) ? value : AppSecurityUtils.encryptString(value, resolveSecret(secret));
        setConfValue(tenant, groupName, name, encryptedValue, enabled);
    }

    public void setConfValue(String groupName, String name, String value, boolean enabled) {
        setConfValue(null, groupName, name, value, enabled);
    }

    public void setConfValue(String tenant, String groupName, String name, String value, boolean enabled) {
        NAssert.requireNonBlank(groupName, "group");
        NAssert.requireNonBlank(name, "name");
        tenant = resolveTenant(tenant);
        List<AppConfEntity> old = confRepository.findByTenantAndGroupNameAndName(tenant, groupName, name);
        if (old.isEmpty()) {
            AppConfEntity conf = new AppConfEntity();
            conf.setTenant(tenant);
            conf.setGroupName(groupName);
            conf.setName(name);
            conf.setValue(value);
            conf.setEnabled(enabled);
            confRepository.save(conf);
        } else {
            AppConfEntity conf = old.get(0);
            conf.setValue(value);
            conf.setEnabled(enabled);
            confRepository.save(conf);
        }
    }

    public NOptional<String> getConfValue(String groupName, String name) {
        return getConfValue(null, groupName, name);
    }

    public NOptional<String> getConfPassword(String groupName, String secret, String name) {
        return getConfPassword(null, groupName, name, secret);
    }

    public NOptional<String> getConfPassword(String tenant, String groupName, String name, String secret) {
        return getConfValue(tenant, groupName, name)
                .map(x -> ((x == null || x.isEmpty()) ? x : AppSecurityUtils.decryptString(x, resolveSecret(secret))));
    }

    public <T> NOptional<T> getConfStruct(String groupName, String name,Class<T> clazz) {
        return getConfStruct(null,groupName,name,clazz);
    }

    public <T> NOptional<T> getConfStruct(String tenant, String groupName, String name,Class<T> clazz) {
        T t=null;
        Class clazz2=clazz;
        while(clazz2 != null) {
            for (Field f : clazz2.getDeclaredFields()) {
                String s = getConfValue(tenant, groupName, name + "." + f.getName()).orNull();
                if (s != null) {
                    Secret sec = f.getAnnotation(Secret.class);
                    if (sec != null) {
                        s = AppSecurityUtils.decryptString(s, resolveSecret(null));
                    }
                }
                Object o = Stringifier.INSTANCE.fromString(s,f.getType());
                f.setAccessible(true);
                if(t==null){
                    try {
                        t = clazz.getConstructor().newInstance();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
                try {
                    f.set(t,o);
                } catch (IllegalAccessException e) {
                    throw new RuntimeException(e);
                }
            }
            clazz2=clazz2.getSuperclass();
        }
        return NOptional.ofNamed(t,name);
    }

    public NOptional<String> getConfValue(String tenant, String groupName, String name) {
        NAssert.requireNonBlank(groupName, "group");
        NAssert.requireNonBlank(name, "name");
        tenant = resolveTenant(tenant);
        List<AppConfEntity> old = confRepository.findByTenantAndGroupNameAndName(tenant, groupName, name);
        if (old.isEmpty()) {
            return NOptional.ofNamedEmpty(tenant + "." + groupName + "." + name);
        } else {
            AppConfEntity conf = old.get(0);
            if (conf.isEnabled() && conf.getValue() != null) {
                return NOptional.of(conf.getValue());
            }
            return NOptional.ofNamedEmpty(tenant + "." + groupName + "." + name);
        }
    }

    public void setConfEnabled(String groupName, String name, boolean enabled, String defaultValue) {
        setConfEnabled(null, groupName, name, enabled, defaultValue);
    }

    public void setConfEnabled(String tenant, String groupName, String name, boolean enabled, String defaultValue) {
        NAssert.requireNonBlank(groupName, "group");
        NAssert.requireNonBlank(name, "name");
        tenant = resolveTenant(tenant);
        List<AppConfEntity> old = confRepository.findByTenantAndGroupNameAndName(tenant, groupName, name);
        if (old.isEmpty()) {
            AppConfEntity conf = new AppConfEntity();
            conf.setTenant(tenant);
            conf.setGroupName(groupName);
            conf.setName(name);
            conf.setValue(defaultValue);
            conf.setEnabled(enabled);
            confRepository.save(conf);
        } else {
            AppConfEntity conf = old.get(0);
            conf.setEnabled(enabled);
            confRepository.save(conf);
        }
    }


    private String resolveSecret(String secret) {
        return NStringUtils.firstNonBlank(secret, masterSecret, DEFAULT_MASTER_PASSWORD);
    }

}
