package net.thevpc.samples.springnuts.core.util;

import net.thevpc.samples.springnuts.core.model.common.AppException;
import net.thevpc.nuts.util.NAssert;
import net.thevpc.nuts.text.NMsg;
import net.thevpc.nuts.util.NOptional;
import net.thevpc.nuts.util.NStringUtils;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.util.Date;
import java.util.UUID;

public class ValidationUtils {


    public static Instant requireDateAsInstant(String item, String name) {
        return requireDateAsInstant(item, name, null);
    }

    public static Instant requireDateAsInstant(String item, String name, String errorCode) {
        String s = NAssert.requireNonBlank(item, name);
        return parseRequiredDateAsInstant(s, name, errorCode);
    }


    public static String formatDate(Instant date) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat("yyyy-MM-dd").format(Date.from(date));
    }

    public static String formatDateTime(Date date) {
        if (date == null) {
            return null;
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
    }

    public static Instant parseRequiredDateAsInstant(String date, String name) {
        return parseRequiredDateAsInstant(date,name,null);
    }

    public static Instant parseRequiredDateAsInstant(String date, String name, String errorCode) {
        Date d = null;
        try {
            d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            throw new AppException(
                    errorCode == null ? "INVALID_ARGUMENT" : errorCode
                    , "Invalid " + name + ". expected date in the format yyyy-MM-dd");
        }
        return d.toInstant();
    }

    public static NOptional<Instant> parseDateAsInstant(String date, String name, String errorCode) {
        Date d = null;
        try {
            d = new SimpleDateFormat("yyyy-MM-dd").parse(date);
        } catch (ParseException e) {
            return NOptional.ofEmpty(()-> NMsg.ofC("Invalid %s. expected date in the format yyyy-MM-dd",name ));
        }
        return NOptional.of(d.toInstant());
    }

    public static String newUUID() {
        return UUID.randomUUID().toString();
    }

    public static String safePasswordLiteral(String password) {
        if (password == null) {
            return "";
        }
        return "'" + NStringUtils.repeat('*', password.length()) + "'";

    }

    public static Throwable rootException(Throwable th) {
        if (th == null) {
            return th;
        }
        while (th.getCause() != null && th.getCause() != th) {
            th = th.getCause();
        }
        return th;
    }

    public static String stacktrace(Throwable th) {
        ByteArrayOutputStream b = new ByteArrayOutputStream();
        PrintStream out = new PrintStream(b);
        th.printStackTrace(out);
        out.flush();
        return b.toString();
    }

    public static String truncate(String details, int max) {
        if (details != null) {
            if (max > 0) {
                if (details.length() > max) {
                    return details.substring(0, max);
                }
            }
        }
        return details;
    }

    public static Date parseRequiredDateTime(String date, String name) {
        return parseRequiredDateTime(date, name, null);
    }

    public static Date parseRequiredDateTime(String date, String name, String errorCode) {
        Date d = null;
        try {
            d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
        } catch (ParseException e) {
            throw new AppException(
                    errorCode == null ? "INVALID_ARGUMENT" : errorCode
                    , "Invalid " + name + ". expected date in the format yyyy-MM-dd");
        }
        return d;
    }

    public static NOptional<Date> parseDateTime(String date, String name) {
        return parseDateTime(date, name, null);
    }

    public static NOptional<Date> parseDateTime(String date, String name, String errorCode) {
        Date d = null;
        try {
            d = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(date);
        } catch (ParseException e) {
            return NOptional.ofEmpty(()->NMsg.ofC("Invalid %s. expected date in the format yyyy-MM-dd",name));
        }
        return NOptional.of(d);
    }

}
