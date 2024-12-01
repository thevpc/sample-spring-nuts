package net.thevpc.samples.springnuts.core.service.impl.service;

import java.time.Instant;
import java.util.HashMap;
import java.util.Map;

public class Stringifier {
    public static final Stringifier INSTANCE = new Stringifier();
    private Map<Class, StringifierAction> maps = new HashMap<Class, StringifierAction>();

    public Stringifier() {
        register(String.class, any -> any);
        register(int.class, any -> any.isEmpty() ? null : Integer.parseInt(any));
        register(Integer.class, any -> any.isEmpty() ? null : Integer.parseInt(any));
        register(double.class, any -> any.isEmpty() ? null : Double.parseDouble(any));
        register(Double.class, any -> any.isEmpty() ? null : Double.parseDouble(any));
        register(long.class, any -> any.isEmpty() ? null : Long.parseLong(any));
        register(Long.class, any -> any.isEmpty() ? null : Long.parseLong(any));
        register(boolean.class, any -> any.isEmpty() ? null : Boolean.parseBoolean(any));
        register(Boolean.class, any -> any.isEmpty() ? null : Boolean.parseBoolean(any));
        register(Instant.class, any -> any.isEmpty() ? null : Instant.parse(any));
    }

    public void register(Class clazz, StringifierAction action) {
        maps.put(clazz, action);
    }

    private interface StringifierAction {
        default String toString(Object any) {
            return any == null ? null : String.valueOf(any);
        }

        Object fromString(String any);
    }

    public final String toString(Object any) {
        if(any==null){
            return null;
        }
        StringifierAction m = getStringifierAction(any.getClass());
        return m.toString(any);
    }

    public final Object fromString(String any,Class clazz) {
        if(any==null){
            return null;
        }
        StringifierAction m = getStringifierAction(clazz);
        return m.fromString(any);
    }

    private StringifierAction getStringifierAction(Class clazz) {
        StringifierAction m = maps.get(clazz);
        if(m==null){
            throw new IllegalArgumentException("unsupported stringifier : "+ clazz);
        }
        return m;
    }
}
