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
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

@Component
@EnableCaching
public class RedisFilter implements Filter {
//    Logger logger = LoggerFactory.getLogger(AccessFilter.class);
//
//    @Autowired
//    AccessList accessList;
//    @Value("${services.service}")
//    String uri;
//
//    @Override
//    public void init(FilterConfig filterConfig) throws ServletException {
//        Filter.super.init(filterConfig);
//    }
//
//    @SneakyThrows
//    @Override
//    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, NullPointerException {
//        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
//        HttpServletResponse httpServletResponse = (HttpServletResponse) response;
//
//        try {
//
//            String methodUri = httpServletRequest.getRequestURI();
//
//            if (methodUri.equalsIgnoreCase(uri)) {
//                String remoteAddress = request.getRemoteAddr();
//                String port = String.valueOf(request.getRemotePort());
//                String ipAndPort = remoteAddress + port;
//
//                accessList.getAccessHistory().computeIfPresent(ipAndPort, (CounterNumber, targetDate) -> {
//                    Counter counter = accessList.getAccessHistory().get(ipAndPort);
//
//                    if ((Calendar.getInstance().getTime().compareTo(counter.getTargetDate()) >= 0 && counter.getNumber() != 9)) //10
//                        counter.setCounter(0, new Date(Calendar.getInstance().getTimeInMillis() + (1 * 60 * 1000)));
//                    else if (counter.getNumber() <= 8)
//                        counter.setNumber(counter.getNumber() + 1);
//                    else if (counter.getNumber() == 9) {
//                        counter.setCounter(10, new Date(Calendar.getInstance().getTimeInMillis() + (1 * 60 * 1000)));
//                        logger.info("Too many inputs..retry after" + counter.getTargetDate());
//                        throw new RuntimeException("too much of  input in one minute...");
//                    } else if (counter.getNumber() > 9)
//                        throw new RuntimeException("too much of  input in one minute...");
//
//                    return counter;
//                });
//
//                accessList.getAccessHistory().computeIfAbsent(ipAndPort, (counterObj) -> {
//                    Counter counter = new Counter();
//                    counter.setCounter(0, new Date(Calendar.getInstance().getTimeInMillis() + (1 * 60 * 1000)));
//                    return counter;
//                });
//
//            }
//
//            chain.doFilter(request, response);
//        } catch (RuntimeException e) {
//            httpServletResponse.setStatus(HttpStatus.FORBIDDEN.value());
//        }
//    }
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }


    Logger logger = LoggerFactory.getLogger(RedisFilter.class);
    AccessList accessList;
    AccessListRepository accessListRepository;

    @Value("${services.service}")
    String uri;

    RedisTemplate<String, AccessList> redisTemplate;
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
                String remoteAddress = request.getRemoteAddr();
                String port = String.valueOf(request.getRemotePort());
                String ipAndPort = remoteAddress + port;

                if (redisService.existsById(ipAndPort)) {

                    redisService.findById(ipAndPort).getAccessHistory().computeIfPresent(ipAndPort, (CounterNumber, targetDate) -> {
                        Counter counter = accessList.getAccessHistory().get(ipAndPort);

                        if ((Calendar.getInstance().getTime().compareTo(counter.getTargetDate()) >= 1 && counter.getNumber() != 10)) {
                            counter.setCounter(1, new Date(Calendar.getInstance().getTimeInMillis() + (1 * 60 * 1000)));
                            redisService.save(accessList);
                        } else if (counter.getNumber() <= 9) {
                            counter.setNumber(counter.getNumber() + 1);
                            redisService.save(accessList);
                        } else if (counter.getNumber() == 10) {
                            counter.setCounter(11, new Date(Calendar.getInstance().getTimeInMillis() + (1 * 60 * 1000)));
                            logger.info("Too many inputs..retry after" + counter.getTargetDate());
                            redisService.save(accessList);
                            throw new RuntimeException("too much of  input in one minute...");
                        } else if (counter.getNumber() > 10)
                            throw new RuntimeException("too much of  input in one minute...");

                        return counter;
                    });
                } else {
                    Counter counter = new Counter();
                    counter.setCounter(1, new Date(Calendar.getInstance().getTimeInMillis() + (1 * 60 * 1000)));
                    accessList = new AccessList(ipAndPort, counter);
                    accessList.setAccessHistory(ipAndPort, counter);
                    redisService.save(accessList);
                    redisService.findById(ipAndPort)
                            .getAccessHistory().computeIfAbsent(ipAndPort, (counterObj) -> {

                                return counter;
                            });
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
}
