package ServiceImpl;

import Service.QueueInterface;
import org.springframework.stereotype.Component;
import org.springframework.validation.annotation.Validated;
import java.util.ArrayList;
import java.util.List;

@Validated
@Component
public class Implementation<T> implements QueueInterface<T> {



    private static int front = -1, rear = -1;
    List<T> arrli;


    public Implementation() {
        arrli = new ArrayList();
    }

    @Override
    public synchronized void enque( T obj) {
        if (obj != null) {
            rear++;
            if (rear == 0) front = 0;
            arrli.add(rear, obj);

        }

    }

    @Override
    public synchronized T deque() {  T obj = null;
        int temp1 = front;
        T temp2;
        if (rear == -1 || (arrli.get(rear) == null)) {
            return null;
        }
        if (rear == front) {
            temp2 = arrli.get(front);
            arrli.set(front, null);
            rear = -1;
            front = -1;
            obj = (T) (temp2).toString().replaceAll("null", " ")
                    .replaceAll("@0@", "null").replaceAll("@", " ").trim();

            return obj;
        }
        temp2 = arrli.get(front);
        front++;
        arrli.set(temp1, null);
        obj = (T) (temp2).toString().replaceAll("null", " ")
                .replaceAll("@0@", "").replaceAll("@", " ").trim();

        return obj;
    }


    @Override
    public T peek() {
        if (rear < 0) {
            System.out.print("Sry..Queue is Empty ,so ");
            return null;
        } else if (arrli.get(rear) == null) return null;
        else
            return (T) arrli.get(rear).toString().replaceAll("null", "")
                    .replaceAll("@0@", "").replaceAll("@", " ").trim();
    }

    public int size() {
        if (rear == -1) return (0);
        else
            return rear - front + 1;
    }


    public List<String> display() {
        int j;
        List<String> list = new ArrayList<>();
        for (j = front; j <= rear; j++) {
            if (j != -1) {
                if (arrli.get(j) != null) {
                    list.add(arrli.get(j).toString());
//                             .replaceAll("null", "")
//                            .replaceAll("@0@", "null").replaceAll("@", " ").trim());
                }
            }
        }
        return   list;
    }

}