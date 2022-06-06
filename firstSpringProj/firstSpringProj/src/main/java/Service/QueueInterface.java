
package Service;



import org.springframework.stereotype.Component;

import java.util.List;

@Component
public interface QueueInterface<T> {

 void  enque(T obj);



    T deque();
    T peek();
    List<String> display();
    int size();

}
