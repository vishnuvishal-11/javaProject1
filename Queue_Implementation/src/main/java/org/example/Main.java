package org.example;

public class Main {
    public static void main(String[] args) {
        int capacity=10;
        Implementation implementation = new Implementation(capacity);
        NonPrimitiveType obj1=new NonPrimitiveType();
        implementation.enque(obj1.getObjstr(1001));
        implementation.enque(obj1.getObjstr("JAva"));
        implementation.enque(obj1.getObjstr(11.01));
        implementation.enque(obj1.getObjstr('c'));
        implementation.display();
        implementation.size();
        implementation.deque();
        implementation.size();
        implementation.display();
        implementation.deque();
        implementation.size();
        implementation.display();







    }
}