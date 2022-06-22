package com.example.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.example.model.UserRequest;

import java.io.IOException;

public interface QueueSelector {
    void enque(UserRequest userRequest) throws IOException;
    Object deque() throws JsonProcessingException;
    int size();
}
