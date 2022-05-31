package security;

import lombok.SneakyThrows;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
import java.util.concurrent.atomic.AtomicReference;

@Component
public class AccessFilter implements Filter {
    Logger logger = LoggerFactory.getLogger(AccessFilter.class);

    @Autowired
    AccessList accessList;
    @Value("${services.service}")
    String uri;

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @SneakyThrows
    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException, NullPointerException {
        HttpServletRequest httpServletRequest = (HttpServletRequest) request;
        HttpServletResponse httpServletResponse = (HttpServletResponse) response;

        try {

            String methodUri = httpServletRequest.getRequestURI();

            if (methodUri.equalsIgnoreCase(uri)) {
                String remoteAddress = request.getRemoteAddr();
                String port = String.valueOf(request.getRemotePort());
                String ipAndPort = remoteAddress + port;

                accessList.getAccessHistory().computeIfPresent(ipAndPort, (CounterNumber, targetDate) -> {
                    Counter counter = accessList.getAccessHistory().get(ipAndPort);
                    //<---  If-1 --->
                    //<--- HARE count gets decremented for existing ipAndPort-->
                    if (counter.getNumber() <= 10) {
                        counter.setNumber(counter.getNumber() + 1);
                        accessList.getAccessHistory().put(ipAndPort, counter);
                        logger.info(" " + counter);
                    }
                    Date date=Calendar.getInstance().getTime();
                    //<---  If-2 --->
                    if (date.compareTo(counter.getTargetDate()) >= 0) {
                        counter.setCounter(0,new Date(Calendar.getInstance().getTimeInMillis() + (1 * 60 * 1000)));                       //Reset is done
                        logger.info("Refreshed :" + counter);
                    }else{
                        if (counter.getNumber() == 10) { //to avoid inputs after reaching threshold to keep on changing target time
                            counter.setCounter(11,new Date(Calendar.getInstance().getTimeInMillis() + (1 * 60 * 1000)));
                            logger.info("Too many inputs..retry after" + counter.getTargetDate());
                        }
                        if (counter.getNumber() >= 10)  //this if condition is must because , without this  counter number having values less
                            //than 9 that didn't reach target time (i.e,If-2 Condition fails) will also throw the exception..
                        throw new RuntimeException("too much of  input in one minute...");
                    }
                    return counter;
                });

                accessList.getAccessHistory().computeIfAbsent(ipAndPort, (counterObj) -> {
                    Counter counter = new Counter();
                    counter.setCounter(0, new Date(Calendar.getInstance().getTimeInMillis() + (1 * 60 * 1000)));
                    logger.info(" " + counter);
                    return counter;
                });

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



