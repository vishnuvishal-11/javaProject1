package controller;

import Service.QueueInterface;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import model.UserRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
@Slf4j
@Component("customq")
public class CustomQueue implements QueueSelector {
    @Autowired
    QueueInterface queueInterface;

    @Override
    public void enque(UserRequest userRequest) {


        queueInterface.enque(userRequest);
        log.info(" customQ - Enque Method has been Accessed...");

    }

    @Override
    public String deque() throws JsonProcessingException,NullPointerException {
        String deletedElement = (String) queueInterface.deque();
        if (deletedElement == null || deletedElement.isEmpty()) {
            log.info(" customQ - deque has been Accessed But Queue is Empty...");
            return "empty queue";
        } else {
            log.info(" customQ - deque has been Accessed ...");
            ObjectMapper mapper = new ObjectMapper();
            return mapper.writeValueAsString(deletedElement);
        }
    }

    @Override
    public int size() { log.info(" customQ - size has been Accessed ...");
        return queueInterface.size();
    }
}
