package org.example;

import java.util.List;

public interface QueueInterface {

    String enque(String obj);

    String deque();
    String peek();
    List<String> display();
    String size();
}
