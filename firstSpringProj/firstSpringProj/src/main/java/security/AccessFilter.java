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

//                  Counter counter = null;
//
//               Date target = new Date();
//                //<--- if condition-Block where count gets decremented for existing ipAndPort-->
//                if (accessList.getAccessHistory().containsKey(ipAndPort)) {
//                    counter = accessList.getAccessHistory().get(ipAndPort);
//                    //<--- Nested if-1   -->
//                    if (counter.getNumber() <= 10) {
//                        counter.setNumber(counter.getNumber() + 1);                // here we are just incrementing the counter
//                        accessList.getAccessHistory().put(ipAndPort, counter);
//                        logger.info("  out of 10 only: " + counter.getNumber() + " inputs  inserted in this one minute");
//                    }
//                    //<--- Nested if-2   -->
//                    if ((Calendar.getInstance().getTime()).compareTo(counter.getTargetDate()) >= 0 && counter.getNumber() < 9) {
//                        //<--- If the Target time is reached but inputs are less than threshold  then counter is refreshed here -->
//                        long timeInSec = Calendar.getInstance().getTimeInMillis();
//                        target = new Date(timeInSec + (1 * 60 * 1000));
//                        counter.setCounter(0, target);
//                        logger.info("Refreshed :" + counter);
//                    }
//                    //<--- Nested if-3   -->
//                    if ((Calendar.getInstance().getTime()).compareTo(counter.getTargetDate()) >= 0) {
//                        //<--- If the Target time is not reached but num of  inputs has reached threshold  then counter is Retrieved back  here -->
//                        long timeInSec = Calendar.getInstance().getTimeInMillis();
//                        target = new Date(timeInSec + (1 * 60 * 1000));
//                        counter.setCounter(0,target);
//                        logger.info("Retrieved back .... " + counter);
//                    }
//                    //<--- Nested if-4   -->
//                    if (counter.getNumber() >= 10) {
//                        //<--- here to 2 condition checks are must if u stop the increment at counter number 10 at nested block 1 (line no 55) ,then
//                        // all inputs after blocked state will change the target time with respect to the time that input is given  -->
//                        if (counter.getNumber() == 10) {
//                            long timeInSecs = Calendar.getInstance().getTimeInMillis();
//                            Date afterAdding1Minute = new Date(timeInSecs + (1 * 60 * 1000));      //target time is set
//                            counter.setCounter(10, afterAdding1Minute);
//                            logger.info("Too many inputs..retry after" + counter.getTargetDate());
//                        }
//                        throw new RuntimeException("too much of  input in one minute...");
//                    }
//                } else {
//                    //<--- else block- where newly entered ipAndPort Deals here and target counter is set-->
//                    long timeInSec = Calendar.getInstance().getTimeInMillis();
//                    target = new Date(timeInSec + (1 * 60 * 1000));       //target limit is set here itself at the beginning
//                    counter = new Counter();
//                    counter.setCounter(0, target);           //before this target ,if u reach 10 inputs . then enque will be blocked(nested if-4)
//                    accessList.getAccessHistory().put(ipAndPort, counter);       // or else refreshed it will be refreshed (nested if-2)
//                    logger.info(" " + counter);
//                }
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
                    if ((date.compareTo(counter.getTargetDate()) >= 0 && counter.getNumber() < 9)||
                            (date.compareTo(counter.getTargetDate()) >= 0)) {
                        //<--- Condition 1 :check if Target time is reached but num of  inputs are less than threshold , then counter is refreshed here -->
                        //<--- Condition 2 :check if Target time is not reached but num of inputs are equal or greater than threshold , then counter is retrieved here -->
                        counter.setCounter(0,new Date(Calendar.getInstance().getTimeInMillis() + (1 * 60 * 1000)));                       //Reset is done
                        logger.info("Refreshed :" + counter);
                    }
                    //<---  If-3 --->
                    if (counter.getNumber() >= 10) {
                        //<--- here to 2 condition checks are must if u stop the increment at counter number 10 at If -1 (line no 102) ,then
                        // all inputs after blocked state will change the target time with respect to the time that input is given  -->
                        if (counter.getNumber() == 10) {
                            counter.setCounter(11,new Date(Calendar.getInstance().getTimeInMillis() + (1 * 60 * 1000)));
                            logger.info("Too many inputs..retry after" + counter.getTargetDate());
                        }
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



