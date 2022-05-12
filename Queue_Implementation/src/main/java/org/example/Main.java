package org.example;
public class Main {
    static int capacity=10;

    public static void main(String[] args) {
        Implementation implementation = new Implementation(capacity);
        NonPrimitiveType<Integer> obj1=new NonPrimitiveType(1001);
        implementation.enque(obj1.toString());
        implementation.enque(new NonPrimitiveType(1002).toString());
        System.out.println("List of Elements in Queue : "+implementation.display());
        System.out.println(implementation.deque()+" is removed");
        System.out.println("Peek is "+implementation.peek());
        System.out.println("List of Elements in Queue : "+implementation.display());
        System.out.println(implementation.deque()+" is removed");
        System.out.println("List of Elements in Queue : "+implementation.display());
        System.out.println("Peek is "+implementation.peek());


    }
}
