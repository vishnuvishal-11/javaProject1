package org.example;

import java.util.ArrayList;
import java.util.List;


public class Implementation<T> implements QueueInterface<T> {
    private static int front = -1, rear = -1;
    private int size;
    T obj = null;
    public Implementation() {

    }

    public Implementation(int size) {

        this.size = size;
        // System.out.println(size);
    }

    public Object[] queue = new Object[10];


    @Override
    public void enque(T obj) {
        if (rear != size - 1) {
            rear++;
            if (rear == 0) front = 0;
            queue[rear] = obj;
        }
        // return("added the element successfully");
        // return null;
    }

    @Override
    public T deque() {
        int temp1 = front, temp = front;
        T temp2 = null;
        if (rear == front) {
            if (front == size - 1) {
                front = rear = -1;
                System.out.println("Sry...Queue is Empty");
                return (T) null;
            }
        }
        temp2 = (T) queue[front];
        front++;
        queue[temp1] = null;
        temp1 = size;
        size = temp1 - 1;
        return (T) (temp2);
    }

    @Override
    public T peek() {
        if (rear < 0) {
            System.out.println("Sry...Queue is Empty");
            return null;
        } else
            return (T) (queue[rear]);
    }

    public int size() {

        if (rear == -1) return (0);
        else {
            int i = rear - front + 1;
            return i;
        }

    }

    public List<String> display() {
        int i = this.size();
        int j;
        List<String> list = new ArrayList<>();
        for (j = 0; j <= i; j++) {
            if (queue[j] != null)
                list.add(queue[j].toString());
            else
                continue;
        }
        return list;
    }

}




