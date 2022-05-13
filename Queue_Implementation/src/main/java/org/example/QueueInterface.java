package org.example;

import java.util.List;

public interface QueueInterface<T> {

 void  enque(T obj);


   // String enque(Object obj);

    T deque();
    T peek();
    List<String> display();
    int size();
}
