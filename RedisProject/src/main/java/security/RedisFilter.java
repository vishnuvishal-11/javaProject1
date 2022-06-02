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

    @Value("${Ratelimit.count}")
    int count;

    @Value("${Ratelimit.penalty.minutes}")
    int penalty;

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
                        Counter counter = accessList.getCounter();
                        if ((Calendar.getInstance().getTime().compareTo(counter.getTargetDate()) >= 1 && counter.getNumber() != count)) {
                            Date date =new Date(Calendar.getInstance().getTimeInMillis() + (penalty * 60 * 1000));
                            counter.setTargetDate(date);
                            counter.setCounter(1,date);
                            accessList.setCounter(counter);
                            redisService.save(accessList);
                        } else if (counter.getNumber() <= count-1) {
                            counter.setNumber(counter.getNumber() + 1);
                            accessList.setCounter(counter);
                            redisService.save(accessList);
                    logger.info("in if" + accessList.getCounter());
                    logger.info("in if find by counter" + redisService.findById(ipAndPort));
                        } else if (counter.getNumber() == count) {
                            Date date =new Date(Calendar.getInstance().getTimeInMillis() + (penalty * 60 * 1000));
                            counter.setTargetDate(date);
                            counter.setCounter(count+1, date);
                            logger.info("Too many inputs..retry after" + counter.getTargetDate());
                            accessList.setCounter(counter);
                            redisService.save(accessList);
                            throw new RuntimeException("too much of  input in one minute...");
                        } else if (counter.getNumber() > count)
                            throw new RuntimeException("too much of  input in one minute...");
                } else {
                    Counter counter = new Counter();
                    Date date =new Date(Calendar.getInstance().getTimeInMillis() + (penalty * 60 * 1000));
                    counter.setTargetDate(date);
                    counter.setCounter(1, date);
                    accessList = new AccessList();
                    accessList.setIp(ipAndPort);
                    accessList.setCounter(counter);
                    redisService.save(accessList);
                    logger.info("in else" + accessList.getCounter());
                    logger.info("in else find by counter" + redisService.findById(ipAndPort));
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
