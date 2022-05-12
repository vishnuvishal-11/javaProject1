package org.example;

import java.util.Arrays;
import java.util.stream.Collectors;
import java.util.List;


public  class Implementation implements QueueInterface {
    private static int front = -1, rear = -1;
    private static String queue[];
    private static int length;
   public Implementation(int size) {
        queue = new String[size];
       // System.out.print(queue.length);
        length= queue.length;
    }
    @Override
    public String enque(String obj) {
        if (rear ==length - 1)
            return("Sry...Queue is full");
        rear++;
        if(rear==0)front=0;
        queue[rear] = obj;
        return("added the element successfully");

    }
    @Override
    public String deque() {
        if (rear == front) {
            if (front == length - 1)
                front = rear = -1;
            return("Sry...Queue is Empty");
        } else{ int temp1=front,temp=front;
           String temp2= queue[front];
            front++;
            queue[temp1]=null;
            temp1= length;
            length=temp1-1;
            return(temp2+" is deleted from queue");
        }
    }

    public String peek() {
        if (rear < 0) {
            return("Sry...Queue is Empty");
        } else
            return(queue[rear]);
    }

    public String size() {

        if(rear==-1)return("Size is zero");
        else {
            List<String> list = Arrays.stream(queue).filter(x -> x != null).collect(Collectors.toList());
            int i=list.size();

            return("Queue Size is "+i);
        }

    }

    public List<String> display() {
            List<String> list = Arrays.stream(queue).filter(x -> x != null).collect(Collectors.toList());
            return list;
    }
}


