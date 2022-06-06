package org.example;

import java.util.ArrayList;
import java.util.List;

public class Implementation<T> implements QueueInterface<T> {
    ObservableSubject<Observer1> observableSubject= new ObservableSubject<>();
        private static int front = -1, rear = -1;
        List<T> arrli;


        public Implementation() {
            arrli = new ArrayList();
        }

        @Override
        public synchronized void enque(T obj) {  T ob = null;
            if (obj != null) {
                rear++;
                if (rear == 0) front = 0;
                arrli.add(rear, obj);
                ob = (T) (obj).toString().replaceAll("null", " ")
                        .replaceAll("@0@", "null").replaceAll("@", " ").trim();
             //   System.out.println("A Object has entered the queue");
                observableSubject.observerNotify("A Object : ["+ob+"] has entered the queue",this.display());
            }

        }

        @Override
        public synchronized T deque() {  T obj = null;
            int temp1 = front;
            T temp2;
            if (rear == -1 || (arrli.get(rear) == null)) {
             //   System.out.println("Queue is empty ..So no deque is possible...");
                observableSubject.observerNotify("Someone tried to Deque bur queue is null..so failed",this.display());
                return null;
            }
            if (rear == front) {
                temp2 = arrli.get(front);
                arrli.set(front, null);
                rear = -1;
                front = -1;
                obj = (T) (temp2).toString().replaceAll("null", " ")
                        .replaceAll("@0@", "null").replaceAll("@", " ").trim();
             //   System.out.println(obj + " is removed from queue and is of type : " + temp2.getClass());
                observableSubject.observerNotify("["+obj + "] is removed from queue and is of type : " + temp2.getClass(),this.display());
                return obj;
            }
            temp2 = arrli.get(front);
            front++;
            arrli.set(temp1, null);
            obj = (T) (temp2).toString().replaceAll("null", " ")
                    .replaceAll("@0@", "").replaceAll("@", " ").trim();
          //  System.out.println(obj + " is removed from queue and is of type : " + temp2.getClass());
            observableSubject.observerNotify("["+obj + "] is removed from queue and is of type : " + temp2.getClass(),this.display());
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
                        list.add(arrli.get(j).toString().replaceAll("null", "")
                                .replaceAll("@0@", "null").replaceAll("@", " ").trim());
                    }
                }
            }
            return list;
        }

    }






