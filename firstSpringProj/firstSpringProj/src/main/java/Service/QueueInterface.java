
package Service;



import java.util.List;


public interface QueueInterface<T> {

 void  enque(T obj);



    T deque();
    T peek();
    List<String> display();
    int size();

}
