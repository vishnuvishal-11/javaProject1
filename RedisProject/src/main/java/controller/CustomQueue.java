package controller;

import Service.QueueInterface;
import model.UserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;

@Component("customq")
public class CustomQueue implements Queue {
    @Autowired
    QueueInterface queueInterface;
    Logger logger =  LoggerFactory.getLogger(CustomQueue.class);
    @Override
    public void enque(UserRequest userRequest) {


        queueInterface.enque(userRequest);
        logger.trace("Enque Method has been Accessed...");

    }
}
