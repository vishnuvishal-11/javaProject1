package controller;
import Service.QueueInterface;
import com.example.firstSpringProj.FirstSpringProjApplication;
import ipServices.IPaddress;
import lombok.SneakyThrows;
import model.UserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import security.Counter;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.*;

@Component
@RestController
@RequestMapping("/queue")
public class Queue {
    @Autowired
    QueueInterface queueInterface;
    @Autowired
    IPaddress ipaddress;


    Logger logger = LoggerFactory.getLogger(Queue.class);

    @GetMapping("/display")
    //@RateLimiter(name ="showOnServer")
    public ResponseEntity<List<String>> showAll() {
        logger.trace("trace-ShowOnServer Method has been Accessed..." + logger.isTraceEnabled());
        logger.debug("debug-ShowOnServer Method has been Accessed..." + logger.isDebugEnabled());
        logger.info("info-ShowOnServer Method has been Accessed..." + logger.isInfoEnabled());
        logger.warn("warn-ShowOnServer Method has been Accessed..." + logger.isWarnEnabled());
        logger.error("error-ShowOnServer Method has been Accessed..." + logger.isErrorEnabled());
        return new ResponseEntity<>(queueInterface.display(), HttpStatus.OK);
    }

    @GetMapping("/size")
    public int countOnServer() {
        logger.trace("CountOnServer Method has been Accessed...");
        return queueInterface.size();
    }


    @SneakyThrows
    @PostMapping("/enque")
    //  @RateLimiter(name ="dropOnServer")
            public  ResponseEntity<String> enqueOnServer(@Valid @RequestBody UserRequest userRequest, HttpServletRequest request) {

        // if(ipaddress.getClientIp(request)) {
     //   ipaddress.getClientIp(request);

        if (!(userRequest.getUserName().equals("null") || userRequest.getDob().equals("null") || userRequest.getLocation().equals("null"))) {
            queueInterface.enque(userRequest);
            logger.trace("DropOnServer Method has been Accessed...");
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            logger.info("ShowOnServer Method has been Accessed but Error Occurred...");
            return new ResponseEntity<>("not a valid input", HttpStatus.BAD_REQUEST);
        }

    }


    @DeleteMapping("/delete")
    //   @RateLimiter(name ="removeFromServer")
    public String deletePeek() {
        String deletedElement = (String) queueInterface.deque();
        if (deletedElement == null || deletedElement.isEmpty()) {
            logger.trace("RemoveOnServer Method has been Accessed But Queue is Empty...");
            return "empty queue";
        } else {
            logger.trace("RemoveOnServer Method has been Accessed ...");
            return deletedElement;
        }

    }

    @GetMapping("/map")
    public Set<String> mapInServer(HttpServletRequest request) {
        ipaddress.getClientIp(request);
        Set<String> set = ipaddress.getList();
        logger.trace("map Method has been Accessed...");

        if (set.isEmpty())
            return null;
        else
            return set;
    }



}
