package controller;

import Service.QueueInterface;
import io.github.resilience4j.ratelimiter.annotation.RateLimiter;
import lombok.extern.slf4j.Slf4j;
import model.UserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Component
@RestController
@Slf4j
@RequestMapping("/queue")
public class QueueController {

  @Autowired
    QueueInterface queueInterface;

      Logger logger= LoggerFactory.getLogger(QueueController.class);

    @GetMapping("/display")
    @RateLimiter(name ="showOnServer")
    public ResponseEntity<List<String>> showOnServer() {
        logger.trace("trace-ShowOnServer Method has been Accessed...");
        logger.isTraceEnabled();
        logger.debug("debug-ShowOnServer Method has been Accessed...");
        logger.isDebugEnabled();
        logger.info("info-ShowOnServer Method has been Accessed...");
        logger.isInfoEnabled();
        logger.warn("warn-ShowOnServer Method has been Accessed...");
        logger.isWarnEnabled();
        logger.error("error-ShowOnServer Method has been Accessed...");
        logger.isErrorEnabled();
        return new ResponseEntity<>(queueInterface.display(), HttpStatus.OK);
    }

    @GetMapping("/size")
    public int countOnServer() {
        logger.trace("CountOnServer Method has been Accessed...");
        return queueInterface.size();
    }


    @PostMapping("/validate")
   @RateLimiter(name ="dropOnServer")
    public  String dropOnServer( @Valid  @RequestBody UserRequest userRequest) {

        if(!(userRequest.getUserName().equals("null") ||userRequest.getDob().equals("null")||userRequest.getLocation().equals("null"))) {
            queueInterface.enque(userRequest);
            logger.trace("DropOnServer Method has been Accessed...");
            return null;
        }
        else{
            logger.info("ShowOnServer Method has been Accessed but Error Occurred...");
            return "NOt A Valid Input...SRY";
        }

    }
//    public ResponseEntity<String> rateLimiterFallback(String ignoredName, io.github.resilience4j.ratelimiter.RequestNotPermitted ignoredEx){
//        logger.trace("DropOnServer Method has been Accessed...");
//     //   logger.error("BUt ... Too many request..Service has Been Disabled Temporarily");
//        return new ResponseEntity<String>("order service does not permit further calls", HttpStatus.TOO_MANY_REQUESTS);
//
//    }

    @DeleteMapping("/delete")
    @RateLimiter(name ="removeFromServer")
    public String removeFromServer() {
        String deletedElement = (String) queueInterface.deque();
        if (deletedElement==null || deletedElement.isEmpty()) {
            logger.trace("RemoveOnServer Method has been Accessed But Queue is Empty...");
            return "empty queue";
        }
        else {
            logger.trace("RemoveOnServer Method has been Accessed ...");
            return deletedElement;
        }


    }
}
