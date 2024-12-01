package net.thevpc.samples.springnuts.core.model.common;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;

public class AppExceptionInfo {
    private String code;
    private String description;
    private String technicalType;
    private String technicalDetails;

    public AppExceptionInfo() {
    }

    public AppExceptionInfo(String code, String description) {
        this.code = code;
        this.description = description;
    }

    public String getTechnicalType() {
        return technicalType;
    }

    public AppExceptionInfo fill(Throwable th) {
        if (th != null) {
            this.setTechnicalType(getClass().getSimpleName());
            ByteArrayOutputStream b = new ByteArrayOutputStream();
            PrintStream out = new PrintStream(b);
            th.printStackTrace(out);
            out.flush();
            this.setTechnicalDetails(b.toString());
        }
        return this;
    }

    public AppExceptionInfo setTechnicalType(String technicalType) {
        this.technicalType = technicalType;
        return this;
    }

    public String getTechnicalDetails() {
        return technicalDetails;
    }

    public AppExceptionInfo setTechnicalDetails(String technicalDetails) {
        this.technicalDetails = technicalDetails;
        return this;
    }

    public String getCode() {
        return code;
    }

    public AppExceptionInfo setCode(String code) {
        this.code = code;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public AppExceptionInfo setDescription(String description) {
        this.description = description;
        return this;
    }
}
