package net.thevpc.samples.springnuts.core.web.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

@RestControllerAdvice(basePackages = {"com.avidea.insurance.webapi.web.ws"})
public class AppResponseBodyAdvice implements ResponseBodyAdvice {
    private static final Logger LOG = LoggerFactory.getLogger(AppResponseBodyAdvice.class);

    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return !"org.springframework.http.converter.ByteArrayHttpMessageConverter".equals(converterType.getName());
    }

    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType,
                                  Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        try {
            return ApiResponse.ofAny(body);
        } catch (Exception e) {
            return ApiResponse.ofError(e);
        }
    }


}
