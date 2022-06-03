package security;

import Redis.AccessListRepository;
import Redis.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Component
@EnableCaching
@WebFilter("redis")
public class RedisFilter implements Filter, Factory {
    Logger logger = LoggerFactory.getLogger(RedisFilter.class);


    @Value("${services.service}")
    String uri;

    @Value("${Ratelimit.count}")
    int count;

    @Value("${Ratelimit.penalty.minutes}")
    int penalty;

    @Autowired
    RedisService redisService;


    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;


        try {
            String methodUri = httpServletRequest.getRequestURI();
            if (methodUri.equalsIgnoreCase(uri)) {
                String ip = null;
                if (request != null) {
                    ip = ((HttpServletRequest) request).getHeader("X-FORWARDED-FOR");
                    if (ip == null || "".equals(ip)) {
                        ip = request.getRemoteAddr();
                    }
                }
                Date date;

                if (redisService.existsById(ip)) {
                    int check = (int) redisService.findById(ip);
                    if (redisService.findById(ip) != count && Calendar.getInstance().getTime().compareTo(redisService.findDateById(ip)) >= 0) {
                        date = new Date(Calendar.getInstance().getTimeInMillis() + (penalty * 60 * 1000));
                        redisService.save(ip, 1, date);
                    } else if (redisService.findById(ip) <= count - 1) {
                        logger.info("incremented");
                        check = check + 1;
                        date = new Date(Calendar.getInstance().getTimeInMillis() + (penalty * 60 * 1000));
                        redisService.save(ip, check, date);
                    } else if (check == count) {
                        date = new Date(Calendar.getInstance().getTimeInMillis() + (penalty * 60 * 1000));
                        redisService.save(ip, count + 1, date);
                        logger.info("too many inputs ..try after .. " + date);
                        throw new RuntimeException("too much of  input in one minute...");
                    } else if (check > count) {
                        throw new RuntimeException("too much of  input in one minute...");
                    }
                } else {
                    logger.info("in else..newly inserted");
                    date = new Date(Calendar.getInstance().getTimeInMillis() + (penalty * 60 * 1000));
                    redisService.save(ip, 1, date);
                }
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

    @Override
    public void display() {
        //logger.info("RedisFilter is Used....");
    }
}
