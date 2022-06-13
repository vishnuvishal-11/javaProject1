package controller;

import model.UserRequest;
import org.springframework.http.ResponseEntity;

public interface Queue {
    void enque(UserRequest userRequest);
}
