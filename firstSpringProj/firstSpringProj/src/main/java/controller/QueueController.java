package controller;

import Service.QueueInterface;
import model.UserRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;


@RestController
public class QueueController {
    @Autowired
    QueueInterface queueInterface;


    @GetMapping("/display")
    public ResponseEntity<List<String>> visible() {
        return new ResponseEntity<>(queueInterface.display(), HttpStatus.OK);
    }


    @PostMapping("/add")
    public void post(@RequestBody UserRequest userRequest) {
        queueInterface.enque(userRequest);

    }

    @DeleteMapping("/delete")
    public String delete() {
        String deletedElement = (String) queueInterface.deque();
        if (deletedElement.isEmpty())
            return "empty queue";
        else
            return deletedElement;


    }
}
