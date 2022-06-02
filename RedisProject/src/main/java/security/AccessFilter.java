//package security;
//
//import lombok.SneakyThrows;
//import org.slf4j.Logger;
//import org.slf4j.LoggerFactory;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.beans.factory.annotation.Value;
//import org.springframework.http.HttpStatus;
//import org.springframework.stereotype.Component;
//
//import javax.servlet.*;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//import java.util.*;
//import java.util.concurrent.atomic.AtomicReference;
//
//@Component
//public class AccessFilter implements Filter {
//    Logger logger = LoggerFactory.getLogger(AccessFilter.class);
//

//import org.springframework.beans.factory.annotation.Value;

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
//                accessList.getAccessHistory().computeIfAbsent(ipAndPort, (counterObj) -> {
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
//}
//
//
//
