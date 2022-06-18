package controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import model.UserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.*;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;
import rabbitconfig.Config;
import java.io.IOException;
import java.util.Properties;
import static org.springframework.amqp.rabbit.core.RabbitAdmin.QUEUE_MESSAGE_COUNT;
import static rabbitconfig.Config.*;
@Slf4j
@Component("rabbitq")
public class RabbitQ implements  QueueSelector {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    private AmqpAdmin rabbitAdmin;

    @Autowired
    @Qualifier("que")
    private Queue queue;

    @Autowired
    private TopicExchange exchange;

    @Autowired
    private Binding binding;



    @Override
    public void enque(UserRequest userRequest) throws IOException {
        rabbitTemplate.convertAndSend(Config.EXCHANGE, Config.ROUTING_KEY, userRequest);
        log.info("RabbitQ - Enque Method has been Accessed ...");
    }

    @Override
    public String deque() {
        ObjectMapper mapper = new ObjectMapper();
//        rabbitAdmin=new RabbitAdmin(rabbitTemplate);
        Object message = rabbitTemplate.receiveAndConvert(QUEUE);
        Message msg= rabbitTemplate.receive(QUEUE);
        try {
            String body = message.toString();
            byte[] bytes= msg.getBody();
            String s = new String(bytes);
            log.info("payload...: "+s);
            log.info("RabbitQ - Deque accessed...: ");
          //  return mapper.writeValueAsString(body);
            return s;
        } catch (Exception e) {
            return "null";
        }

    }

    @Override
    public int size() {
       rabbitAdmin=new RabbitAdmin(rabbitTemplate);
        Properties property=rabbitAdmin.getQueueProperties(QUEUE);
        log.info("RabbitQ - size accessed...: ");
        return (int) property.get(QUEUE_MESSAGE_COUNT);

        }
    }


