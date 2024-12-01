package net.thevpc.samples.springnuts.core;

import net.thevpc.samples.springnuts.core.model.common.AppPrincipal;
import net.thevpc.nuts.util.NOptional;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class App implements ApplicationContextAware {
    public static final String API_VERSION = "1.0.0";
    public static final String APP_NAME = "MyApplication";
    public static final InheritableThreadLocal<CallInfo> callInfo = new InheritableThreadLocal<>();
    private static ApplicationContext globalApplicationContext;

    public static class CallInfo {
        boolean technicalDetails;

        public boolean isTechnicalDetails() {
            return technicalDetails;
        }

        public CallInfo setTechnicalDetails(boolean technicalDetails) {
            this.technicalDetails = technicalDetails;
            return this;
        }
    }

    public static CallInfo getCallInfo() {
        CallInfo u = callInfo.get();
        if (u == null) {
            callInfo.set(u = new CallInfo());
        }
        return u;
    }

    public static NOptional<ApplicationContext> context() {
        if (globalApplicationContext == null) {
            return NOptional.ofNamedEmpty("context");
        }
        return NOptional.of(globalApplicationContext);
    }

    public static NOptional<AppPrincipal> principal() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if(authentication!=null) {
            Object p = authentication.getPrincipal();
            if (p instanceof AppPrincipal) {
                return NOptional.of((AppPrincipal) p);
            }
        }
        return NOptional.ofNamedEmpty("principal");
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        globalApplicationContext = applicationContext;
    }
}
