package org.example;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.List;

class NonPrimitiveType<T>{
    String objstr;
    public String getObjstr(Number s) {
        objstr=s.toString();
        return objstr;
    }
    public String getObjstr(char s) {
        objstr=Character.toString(s);
        return objstr;
    }
    public String getObjstr(String s) {
        return s;
    }

}
public  class Implementation implements QueueInterface {
    private static int front = -1, rear = -1;
    private static String queue[];
    private static int length;

    Implementation(int size) {
        queue = new String[size];
       // System.out.print(queue.length);
        length= queue.length;
    }

    NonPrimitiveType obj1, obj2;

    @Override
    public void enque(String obj) {
        if (rear ==length - 1)
            System.out.print("Sry...Queue is full");
        rear++;
        if(rear==0)front=0;
        queue[rear] = obj;

    }

    @Override
    public void deque() {
        if (rear == front) {
            if (front == length - 1)
                front = rear = -1;
            System.out.println("Sry...Queue is Empty");
        } else{ int temp=front;
            front++;
            System.out.println(queue[temp]+" is deleted from queue");
            queue[temp]=null;
           temp= length;
           length=temp-1;
        }
    }

    public void peek() {
        if (rear < 0) {
            System.out.println("Sry...Queue is Empty");
        } else
            System.out.print(queue[rear]);

    }

    public void size() {
      //  int temp;
      //  if (front != rear) {
      //  temp = rear - front + 1;
      //  System.out.println("Size of queue is:" + temp);
           // System.out.println(queue[front]);
           // System.out.println(queue[rear]);
      //  }
      //  else
      //  System.out.println("Sry...Queue is Empty");
        List<String> list= Arrays.stream(queue).filter(x -> x != null).collect(Collectors.toList());
        System.out.println(list.size());

    }

    public void display() {
        System.out.println("Elements in queue are....");
       //  int i;
        // if(front==-1)i=0;
       //  else i=front;
       //  for ( ; i <=rear; i++) {
        //     System.out.println(queue[i]);
         //   }
        List<String> list= Arrays.stream(queue).filter(x -> x != null).collect(Collectors.toList());
        System.out.println(list);



    }
}
