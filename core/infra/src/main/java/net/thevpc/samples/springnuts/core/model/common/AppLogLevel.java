package net.thevpc.samples.springnuts.core.model.common;

import java.util.logging.Level;

public enum AppLogLevel {
    FINEST,
    FINER,
    FINE,
    CONFIG,
    INFO,
    WARNING,
    SEVERE,
    ;
    static AppLogLevel of(Level logLevel){
        return of((logLevel==null?Level.SEVERE : logLevel).intValue());
    }
    static AppLogLevel of(int logLevel){
        if(logLevel<=300){
            return FINEST;
        }
        if(logLevel<=400){
            return FINER;
        }
        if(logLevel<=500){
            return FINE;
        }
        if(logLevel<=700){
            return CONFIG;
        }
        if(logLevel<=800){
            return INFO;
        }
        if(logLevel<=900){
            return WARNING;
        }
        if(logLevel<=1000){
            return WARNING;
        }
        return SEVERE;
    }
    public Level toJULLevel(){
        return switch (this){
            case SEVERE -> Level.SEVERE;
            case CONFIG -> Level.CONFIG;
            case FINE -> Level.FINE;
            case FINEST -> Level.FINEST;
            case FINER -> Level.FINER;
            case WARNING -> Level.WARNING;
            case INFO -> Level.INFO;
        };
    }
}
