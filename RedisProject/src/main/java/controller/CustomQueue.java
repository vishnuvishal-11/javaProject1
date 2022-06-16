package controller;

import Service.QueueInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
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

    @Override
    public String deque() throws JsonProcessingException,NullPointerException {
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

    @Override
    public int size() {
        return queueInterface.size();
    }
}
