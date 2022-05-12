package org.example;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.List;


public  class Implementation implements QueueInterface {
    private static int front = -1, rear = -1;
    private static Object queue[];
    private static int length;
   public Implementation(int size) {
        queue = new Object[size];
        length= queue.length;
    }
    @Override
    public String enque(Object obj) {
        if (rear ==length - 1)
            return("Sry...Queue is full");
            rear++;
        if(rear==0)front=0;
           queue[rear] = obj;
           return("added the element successfully");
          // return null;
    }
    @Override
    public Object deque() {
       int temp1 = front, temp = front;Object temp2 = null;
        if (rear == front) {
            if (front == length - 1) {
                front = rear = -1;
                return ("Sry...Queue is Empty");
            } }
             temp2 = queue[front];
            front++;
            queue[temp1] = null;
            temp1 = length;
            length = temp1 - 1;
            


        return (temp2);
    }
    public Object peek() {
        if (rear < 0) {
            return("Sry...Queue is Empty");
        } else
            return(queue[rear]);
    }

    public int size() {

        if(rear==-1)return(0);
        else {
            List<Object> list = Arrays.stream(queue).filter(x -> x != null).collect(Collectors.toList());
            int i=list.size();
            return(i);
        }

    }

    public List<Object> display() {
            List<Object> list = Arrays.stream(queue).filter(x -> x != null).collect(Collectors.toList());
            return list;
    }

}


