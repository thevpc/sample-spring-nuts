package net.thevpc.samples.springnuts.core.web.config;

import net.thevpc.samples.springnuts.core.App;
import jakarta.servlet.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import java.io.IOException;

@Component
//@Order(1)
@Slf4j
public class TransFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        // Initialization logic if needed
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        App.getCallInfo().setTechnicalDetails(Boolean.parseBoolean(request.getParameter("debug")));
        chain.doFilter(request, response);
    }

    @Override
    public void destroy() {
        // Cleanup logic if needed
        Filter.super.destroy();
    }
}
