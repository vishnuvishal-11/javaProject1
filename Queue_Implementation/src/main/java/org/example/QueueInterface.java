package org.example;

import java.util.List;

public interface QueueInterface {

    String enque(Object obj);

    Object deque();
    Object peek();
    List<Object> display();
    int size();
}
