
package Service;

public interface QueueInterface<T> {

 void  enque(T obj);



    T deque();
    T peek();
    String display();
    int size();

}
