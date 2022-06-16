package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import model.UserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.mapping.model.Property;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import rabbitconfig.Config;

import java.util.Arrays;
import java.util.Properties;

import static org.springframework.amqp.rabbit.core.RabbitAdmin.QUEUE_MESSAGE_COUNT;
import static rabbitconfig.Config.QUEUE;

@Component("rabbitq")
public class RabbitQ implements  Queue {
    @Autowired
    private RabbitTemplate rabbitTemplate;
   // @Autowired
    RabbitAdmin rabbitAdmin;

    Logger logger = LoggerFactory.getLogger(RabbitQ.class);

    @Override
    public void enque(UserRequest userRequest) {
        rabbitTemplate.convertAndSend(Config.EXCHANGE, Config.ROUTING_KEY, userRequest);
//        rabbitTemplate.convertAndSend(topic.getName(), "rabbit", userRequest.toString());
        logger.trace("Enque Method has been Accessed in rabbitmq...");
    }

    @Override
    public String deque() {
        ObjectMapper mapper = new ObjectMapper();
        Message message = rabbitTemplate.receive(QUEUE);
        try {
            String msg= message.getMessageProperties().toString();
          // String msg= Arrays.toString(arr);
            return mapper.writeValueAsString(msg);
        } catch (Exception e) {
            return "null";
        }

    }

    @Override
    public int size() { rabbitAdmin=new RabbitAdmin(rabbitTemplate);
        Properties property=rabbitAdmin.getQueueProperties(QUEUE);
            int count = (int) property.get(QUEUE_MESSAGE_COUNT);
            return count;

        }
    }


