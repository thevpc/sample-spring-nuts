package net.thevpc.samples.springnuts.core.service.impl.service;

import net.thevpc.samples.springnuts.core.App;
import net.thevpc.samples.springnuts.core.dal.repository.AppLogEntityRepository;
import net.thevpc.samples.springnuts.core.model.common.AppException;
import net.thevpc.samples.springnuts.core.model.common.AppLogLevel;
import net.thevpc.samples.springnuts.core.model.common.AppLogVerb;
import net.thevpc.samples.springnuts.core.model.common.AppPrincipal;
import net.thevpc.samples.springnuts.core.model.entity.AppLog;
import net.thevpc.samples.springnuts.core.service.impl.converter.AppLogConverter;
import net.thevpc.samples.springnuts.core.util.ValidationUtils;
import net.thevpc.nuts.util.NBlankable;
import net.thevpc.nuts.util.NStringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.slf4j.event.Level;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class LogService {
    private static final Logger LOG = LoggerFactory.getLogger(LogService.class);
    @Autowired
    @Lazy
    private AppLogEntityRepository logRepository;
    public void doLogError(Throwable throwable, String groupName) {
        AppLog log = new AppLog();
        throwable = ValidationUtils.rootException(throwable);
        log.setDescription(ValidationUtils.stacktrace(throwable));
        log.setMessage(NStringUtils.firstNonBlank(throwable.getMessage(), "Error"));
        if (throwable instanceof AppException) {
            String code = ((AppException) throwable).getInfo().getCode();
            if (code != null) {
                log.setCode(code);
            }
        }
        if (log.getCode() == null) {
            log.setCode("Exception::" + throwable.getClass().getName());
        }
        log.setGroupName(groupName);
        log.setLevel(AppLogLevel.SEVERE);
    }

    public void doLog(AppLog log) {
        if (log.getAccount() == null) {
            AppPrincipal principal = App.principal().orNull();
            log.setAccount(principal==null?null:principal.getUsername());
        }
        if (log.getVerb() == null) {
            log.setVerb(AppLogVerb.DEFAULT);
        }
        if (NBlankable.isBlank(log.getCode())) {
            log.setCode("DEFAULT");
        }
        if (NBlankable.isBlank(log.getGroupName())) {
            log.setGroupName("DEFAULT");
        }
        if (NBlankable.isBlank(log.getCode())) {
            log.setCode("DEFAULT");
        }
        if (NBlankable.isBlank(log.getLevelValue())) {
            log.setLevel(AppLogLevel.FINE);
        }
        log.setDate(Instant.now());
        log.setLevelValue(log.getLevel().ordinal());
        log.setCode(ValidationUtils.truncate(log.getCode(), 255));
        log.setMessage(ValidationUtils.truncate(log.getMessage(), 1024));
        log.setDescription(ValidationUtils.truncate(log.getDescription(), 8192));
        logRepository.save(AppLogConverter.INSTANCE.toEntity(log));
        LOG.atLevel(convertJULToSLF4J(log.getLevel().toJULLevel())).log("[{}][{}][{}][{}]{}",log.getTenant(), log.getGroupName(), log.getCode(), log.getVerb(), log.getMessage());
    }
    public static org.slf4j.event.Level convertJULToSLF4J(java.util.logging.Level julLevel) {
        int logLevel = julLevel.intValue();
        if(logLevel<=300){
            //FINEST
            return org.slf4j.event.Level.DEBUG;
        }
        if(logLevel<=400){
            return org.slf4j.event.Level.DEBUG;
        }
        if(logLevel<=500){
            return org.slf4j.event.Level.TRACE;
        }
        if(logLevel<=700){
            //CONFIG
            return org.slf4j.event.Level.INFO;
        }
        if(logLevel<=800){
            return org.slf4j.event.Level.INFO;
        }
        if(logLevel<=900){
            return org.slf4j.event.Level.WARN;
        }
        if(logLevel<=1000){
            return Level.ERROR;
        }
        //ALL
        return Level.ERROR;
    }

}
