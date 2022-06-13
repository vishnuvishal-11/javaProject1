package controller;
import Service.QueueInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import ipServices.IPaddress;
import lombok.SneakyThrows;
import model.UserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;
import rabbitconfig.Config;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;

import java.net.UnknownHostException;
import java.util.*;

@Component
@RestController
@RequestMapping("/queue")
public class QueueClass {
    @Autowired
    QueueInterface queueInterface;
    @Autowired
    IPaddress ipaddress;
    @Value("${dynamic.queue}")
    String queuestr;
    @Autowired
            @Qualifier("customq")
    Queue queuecus;
    @Autowired
    @Qualifier("rabbitq")
    Queue queuerab;
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private TopicExchange topic;
    Logger logger =  LoggerFactory.getLogger(QueueClass.class);



    @GetMapping("/display")
    public String display() {
        logger.trace("trace-ShowOnServer Method has been Accessed..." + logger.isTraceEnabled());
        logger.debug("debug-ShowOnServer Method has been Accessed..." + logger.isDebugEnabled());
        return queueInterface.display();
    }

    @GetMapping("/size")
    public int size() {
        logger.trace("Size Method has been Accessed...");
        return queueInterface.size();
    }


    @SneakyThrows
    @PostMapping("/enque")
    //  @RateLimiter(name ="dropOnServer")
            public  ResponseEntity<String> enque(@Valid @RequestBody UserRequest userRequest) throws UnknownHostException {

        if (!(userRequest.getUserName().equals("null") || userRequest.getDob().equals("null") || userRequest.getLocation().equals("null"))) {
//
//            queueInterface.enque(userRequest);
//            logger.trace("Enque Method has been Accessed...");
//            logger.trace("Enque Method has been Accessed in rabbitmq...");
//
//            rabbitTemplate.convertAndSend(Config.EXCHANGE,Config.ROUTING_KEY, userRequest);
//            rabbitTemplate.convertAndSend(topic.getName(), "rabbit", userRequest.toString());
            logger.info(queuestr +" queue is selected");
            if (queuestr.equalsIgnoreCase("rabbit")){
                queuerab.enque(userRequest);
            }
            else {queuecus.enque(userRequest);
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
        String deletedElement = (String) queueInterface.deque();
        if (deletedElement == null || deletedElement.isEmpty()) {
            logger.trace("deque has been Accessed But Queue is Empty...");
            return "empty queue";
        } else {
            logger.trace("deque has been Accessed ...");
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(deletedElement);
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




