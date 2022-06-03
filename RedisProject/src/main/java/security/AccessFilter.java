//package security;
//
//import lombok.SneakyThrows;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//import javax.servlet.*;
//import javax.servlet.annotation.WebFilter;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.*;
//
//
//@Component
//@WebFilter("custom")
//public class AccessFilter implements Filter,Factory {
//    Logger logger = LoggerFactory.getLogger(AccessFilter.class);
//
//
//
//
//
//@Value("${Ratelimit.count}")
//    int count;
//
//@Value("${Ratelimit.penalty.minutes}")
//    int penalty;
//    @Autowired
//    AccessList accessList;
//    @Value("${services.service}")
//    String uri;
//
//
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
//        logger.info("CustomFilter is Used...." );
//
//        try {
//
//            String methodUri = httpServletRequest.getRequestURI();
//
//            if (methodUri.equalsIgnoreCase(uri)) {
//                String ip=null ;
//
//                if (request != null) {
//                    ip = ((HttpServletRequest) request).getHeader("X-FORWARDED-FOR");
//                    if (ip == null || "".equals(ip)) {
//                        ip = request.getRemoteAddr();
//                    }
//                }
//
//                String finalIp = ip;
//                accessList.getAccessHistory().computeIfPresent(ip, (CounterNumber, targetDate) -> {
//                    Counter counter = accessList.getAccessHistory().get(finalIp);
//
//                    if ((Calendar.getInstance().getTime().compareTo(counter.getTargetDate()) >= 0 && counter.getNumber() != count))
//                        counter.setCounter(0, new Date(Calendar.getInstance().getTimeInMillis() + (penalty * 60 * 1000)));
//                    else if (counter.getNumber() <= count-1)
//                        counter.setNumber(counter.getNumber() + 1);
//                    else if (counter.getNumber() == count) {
//                        counter.setCounter(count+1, new Date(Calendar.getInstance().getTimeInMillis() + (penalty * 60 * 1000)));
//                        logger.info("Too many inputs..retry after" + counter.getTargetDate());
//                        throw new RuntimeException("too much of  input in one minute...");
//                    } else if (counter.getNumber() > count)
//                        throw new RuntimeException("too much of  input in one minute...");
//
//                    return counter;
//                });
//
//                accessList.getAccessHistory().computeIfAbsent(ip, (counterObj) -> {
//                    Counter counter = new Counter();
//                    counter.setCounter(1, new Date(Calendar.getInstance().getTimeInMillis() + (penalty * 60 * 1000)));
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
//
//    @Override
//    public void destroy() {
//        Filter.super.destroy();
//    }
//
//    @Override
//    public void display() {
//        logger.info("CustomFilter is Used...." );
//    }
//}
//
//
//
