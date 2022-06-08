package Redis;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import security.AccessList;
import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@Configuration
@Service
public class FilterSelector implements Filter {

    @Value("${dynamic.filter:#{custom}}")
    String filter;

    Logger logger = LoggerFactory.getLogger(FilterSelector.class);

    @Autowired
    AccessList accessList;
    @Value("${services.service}")
    String uri;

    @Autowired
    @Qualifier("custom")
    FactoryInterface factoryInterface;

    @Autowired
    @Qualifier("cache")
    FactoryInterface factoryInterfaceRedis;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {

        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
        try {
            String methodUri = httpServletRequest.getRequestURI();
            String ip = null;
            if (methodUri.equalsIgnoreCase(uri)) {
                if (request != null) {
                    ip = ((HttpServletRequest) request).getHeader("X-FORWARDED-FOR");
                    if (ip == null || "".equals(ip)) {
                        ip = request.getRemoteAddr();
                    }
                }
                if (filter.equalsIgnoreCase("cache"))
                    factoryInterfaceRedis.filter(ip);
                else factoryInterface.filter(ip);
            }

            chain.doFilter(request, response);
        } catch (RuntimeException e) {
            httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
        }
    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}


