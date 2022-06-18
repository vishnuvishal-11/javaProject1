package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.UserRequest;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

public interface QueueSelector {
    void enque(UserRequest userRequest) throws IOException;
    String deque() throws JsonProcessingException;
    int size();
}
