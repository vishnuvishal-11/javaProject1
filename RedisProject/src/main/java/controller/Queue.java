package controller;
import Redis.RedisService;
import Service.QueueInterface;
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

    @Autowired
    RedisService redisService;

    Logger logger =  LoggerFactory.getLogger(Queue.class);



    @GetMapping("/display")
    public List<String> display() {
        logger.trace("trace-ShowOnServer Method has been Accessed..." + logger.isTraceEnabled());
        logger.debug("debug-ShowOnServer Method has been Accessed..." + logger.isDebugEnabled());
        return queueInterface.display();
    }

    @GetMapping("/size")
    public int countOnServer() {
        logger.trace("Size Method has been Accessed...");
        return queueInterface.size();
    }


    @SneakyThrows
    @PostMapping("/enque")
    //  @RateLimiter(name ="dropOnServer")
            public  ResponseEntity<String> enque(@Valid @RequestBody UserRequest userRequest, HttpServletRequest request) {

        if (!(userRequest.getUserName().equals("null") || userRequest.getDob().equals("null") || userRequest.getLocation().equals("null"))) {
            queueInterface.enque(userRequest);
            logger.trace("Enque Method has been Accessed...");
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            logger.info("Enque Method has been Accessed but Error Occurred...");
            return new ResponseEntity<>("not a valid input", HttpStatus.BAD_REQUEST);
        }

    }


    @DeleteMapping("/delete")
    //   @RateLimiter(name ="removeFromServer")
    public String delete() {
        String deletedElement = (String) queueInterface.deque();
        if (deletedElement == null || deletedElement.isEmpty()) {
            logger.trace("Delete Method has been Accessed But Queue is Empty...");
            return "empty queue";
        } else {
            logger.trace("Delete Method has been Accessed ...");
            return deletedElement;
        }

    }
//
//    @GetMapping("/redis")
//    public Map<Object, Object> map() {
//
//      return redisService.display();
//
//    }
    }




