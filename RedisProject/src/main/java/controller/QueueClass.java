package controller;
import Service.QueueInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ipServices.IPaddress;
import lombok.SneakyThrows;
import model.UserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import javax.validation.Valid;
import java.net.UnknownHostException;

import static rabbitconfig.Config.QUEUE;
@Component
@RestController
@RequestMapping("/queue")
public class QueueClass {
    @Autowired
    QueueInterface queueInterface;
    @Autowired
    IPaddress ipaddress;
    @Value("${dynamic.queue}")
    String queuestring;
    @Autowired
    @Qualifier("customq")
    Queue queuecustom;
    @Autowired
    @Qualifier("rabbitq")
    Queue queuerabbit;
    Logger logger =  LoggerFactory.getLogger(QueueClass.class);

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @GetMapping("/display")
    public String display() {
        logger.trace("trace-ShowOnServer Method has been Accessed..." + logger.isTraceEnabled());
        logger.debug("debug-ShowOnServer Method has been Accessed..." + logger.isDebugEnabled());
        return queueInterface.display();
    }

    @GetMapping("/size")
    public int size() {
        int size=0;
        logger.trace("Size Method has been Accessed...");
        if (queuestring.equalsIgnoreCase("rabbit")){
            size= queuerabbit.size();
        }
        else { size=queuecustom.size();
        }

        return size;
    }
    @SneakyThrows
    @PostMapping("/enque")
    //  @RateLimiter(name ="dropOnServer")
            public  ResponseEntity<String> enque(@Valid @RequestBody UserRequest userRequest) throws UnknownHostException {

        if (!(userRequest.getUserName().equals("null") || userRequest.getDob().equals("null") || userRequest.getLocation().equals("null"))) {

            logger.info(queuestring +" queue is selected");
            if (queuestring.equalsIgnoreCase("rabbit")){
                queuerabbit.enque(userRequest);
            }
            else {queuecustom.enque(userRequest);
            }
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            logger.info("Enque Method has been Accessed but Error Occurred...");
            return new ResponseEntity<>("not a valid input", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deque")
    //   @RateLimiter(name ="removeFromServer")
    public String deque() throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
       // return mapper.writeValueAsString(deque);
        String deque=null;
        if (queuestring.equalsIgnoreCase("rabbit")){
             deque=queuerabbit.deque();
        }
        else {  deque=queuecustom.deque();
        }
        return mapper.writeValueAsString(deque);

    }

    }




