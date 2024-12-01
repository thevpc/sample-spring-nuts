package net.thevpc.samples.springnuts.core.web.config;

import net.thevpc.samples.springnuts.core.App;
import net.thevpc.samples.springnuts.core.model.common.AppException;
import net.thevpc.samples.springnuts.core.model.common.AppExceptionInfo;
import com.fasterxml.jackson.annotation.JsonGetter;
import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;

public class ApiResponse<T> implements Serializable {
    private static final long serialVersionUID = 1L;

    private T data;
    private boolean success;
    private String apiVersion;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    protected String error;

    /**
     * Instantiates a new Api response.
     */
    public ApiResponse() {
        this.success = true;
        this.apiVersion = App.API_VERSION;
    }

    /**
     * Instantiates a new Api response.
     *
     * @param data    the data
     * @param success the success
     */
    public ApiResponse(T data, boolean success) {
        this.data = data;
        this.success = success;
        this.apiVersion = App.API_VERSION;
    }

    /**
     * Instantiates a new Api response.
     *
     * @param data the data
     */
    public ApiResponse(T data) {
        this.data = data;
        this.success = true;
        this.apiVersion = App.API_VERSION;
    }

    public static ApiResponse<AppExceptionInfo> ofError(AppExceptionInfo info) {
        return new ApiResponse<>(info, false);
    }

    public static ApiResponse<AppExceptionInfo> ofError(AppException info) {
        return ofError(info.getInfo());
    }

    public static ApiResponse<?> ofAny(Object body) {
        if (body == null) {
            return new ApiResponse<>(null);
        } else {
            if (body instanceof AppException) {
                AppException ae = (AppException) body;
                //LogHelper.logApiException(ae,LOG);
                return ApiResponse.ofError(ae);
            } else if (body instanceof AppExceptionInfo) {
                return ApiResponse.ofError((AppExceptionInfo) body);
            } else if (body instanceof ApiResponse) {
                return (ApiResponse<?>) body;
            }else {
                return ApiResponse.ofSuccess(body);
            }
        }
    }

    public static <T> ApiResponse<T> ofSuccess(T any) {
        return new ApiResponse<>(any);
    }

    public static ApiResponse<AppExceptionInfo> ofError(Exception exception) {
        if (exception instanceof AppException) {
            return ofError(((AppException) exception).getInfo());
        } else {
            return ofError(new AppException("ERROR", exception.getMessage(), exception));
        }
    }

    /**
     * Gets data.
     *
     * @return the data
     */
    public T getData() {
        return data;
    }

    /**
     * Sets data.
     *
     * @param data the data
     */
    public void setData(T data) {
        this.data = data;
    }

    /**
     * Gets success.
     *
     * @return the success
     */
    public Boolean getSuccess() {
        return success;
    }

    /**
     * Sets success.
     *
     * @param success the success
     */
    public void setSuccess(Boolean success) {
        this.success = success;
    }

    @JsonGetter(value = "api-version")
    public String getApiVersion() {
        return apiVersion;
    }

    public ApiResponse<T> setApiVersion(String apiVersion) {
        this.apiVersion = apiVersion;
        return this;
    }

    /**
     * Gets error.
     *
     * @return the error
     */
    public String getError() {
        return error;
    }

    /**
     * Sets error.
     *
     * @param error the error
     */
    public void setError(String error) {
        this.error = error;
    }


}
