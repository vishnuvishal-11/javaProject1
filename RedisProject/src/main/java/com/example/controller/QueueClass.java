package com.example.controller;

import com.example.Repository.UserRequestRepository;
import com.example.Service.QueueInterface;
import com.example.model.Converter;
import com.example.ipServices.IPaddress;
import com.example.model.UserRequestDto;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import com.example.model.UserRequest;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.List;

@Component
@RestController
@RequestMapping("/queue")
@Slf4j
public class QueueClass {
    @Autowired
    QueueInterface queueInterface;
    @Autowired
    IPaddress ipaddress;
    @Value("${dynamic.queue}")
    String queuestring;
    @Autowired
    @Qualifier("customqueue")
    QueueSelector queuecustom;
    @Autowired
    @Qualifier("rabbitq")
    QueueSelector queuerabbit;

    @Autowired
    private RabbitTemplate rabbitTemplate;
    @Autowired
    @Qualifier("convert")
    Converter converter;
    @Autowired
    UserRequestRepository userRequestRepository;

    @GetMapping("/display")
    public List<com.example.model.UserRequestDto> display() {
        log.trace("trace-ShowOnServer Method has been Accessed..." + log.isTraceEnabled());
        log.debug("debug-ShowOnServer Method has been Accessed..." + log.isDebugEnabled());
        List<UserRequest> findAll = queueInterface.display();
        return converter.entityToDto(findAll);
    }

    @GetMapping("/size")
    public int size() {
        int size = 0;
        if (queuestring.equalsIgnoreCase("rabbit")) {
            size = queuerabbit.size();
        } else {
            size = queuecustom.size();
        }
        return size;
    }

    @PostMapping("/enque")
    @SneakyThrows
    public ResponseEntity<String> enque(@Valid @RequestBody com.example.model.UserRequestDto userRequestDto) {

        if (!(userRequestDto.getFirstName().equals("null")  || userRequestDto.getLocation().equals("null"))) {
            UserRequest userRequest= converter.dtoToEntity(userRequestDto);
            if (queuestring.equalsIgnoreCase("rabbit")) {

                queuerabbit.enque(userRequest);
            } else {
                queuecustom.enque(userRequest);
            }
            return new ResponseEntity<>(HttpStatus.ACCEPTED);
        } else {
            log.info("Enque Method has been Accessed but Error Occurred...");
            return new ResponseEntity<>("not a valid input", HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping("/deque")
    public Serializable deque() {
        Object deque = null;
        try {
            if (queuestring.equalsIgnoreCase("rabbit")) {
                deque = queuerabbit.deque();
            } else {
                deque = queuecustom.deque();
            }
            return converter.entityToDto((UserRequest) deque);
        } catch (Exception e) {
            return "empty queue";
        }

    }

}




