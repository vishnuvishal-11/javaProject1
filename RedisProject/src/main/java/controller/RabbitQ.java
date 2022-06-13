package controller;

import model.UserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import rabbitconfig.Config;
@Component("rabbitq")
public class RabbitQ implements  Queue{
    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    private TopicExchange topic;
    Logger logger =  LoggerFactory.getLogger(RabbitQ.class);
    @Override
    public void enque(UserRequest userRequest) {
        rabbitTemplate.convertAndSend(Config.EXCHANGE,Config.ROUTING_KEY, userRequest);
        rabbitTemplate.convertAndSend(topic.getName(), "rabbit", userRequest.toString());
        logger.trace("Enque Method has been Accessed in rabbitmq...");
    }
}
