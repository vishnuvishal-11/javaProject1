package controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import model.UserRequest;
import org.springframework.http.ResponseEntity;

public interface Queue {
    void enque(UserRequest userRequest);
    String deque() throws JsonProcessingException;
    int size();
}
