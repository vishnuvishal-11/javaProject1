package org.example;
public class Main {
    static int capacity;
    public Main(String capacity) {
        this.capacity = Integer.parseInt(capacity);
    }
    public static void main(String[] args) {
        Main main= new Main("10");
        Implementation implementation = new Implementation(capacity);
        NonPrimitiveType obj1=new NonPrimitiveType();
        implementation.enque(obj1.getObjstr(1001));
        implementation.enque(obj1.getObjstr("JAva"));
        implementation.enque(obj1.getObjstr(11.01));
        implementation.enque(obj1.getObjstr('c'));
        System.out.println(implementation.display());
        System.out.println(implementation.size());
        System.out.println(implementation.deque());
        System.out.println(implementation.size());
        System.out.println(implementation.display());
        System.out.println(implementation.deque());
        System.out.println(implementation.size());
        System.out.println(implementation.display());
        System.out.println(implementation.peek());
    }
}