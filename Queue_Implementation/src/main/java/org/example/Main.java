package org.example;
public class Main {
    static int capacity=10;

    public static void main(String[] args) throws Exception {
        Implementation implementation = new Implementation(capacity);
        NonType<NonPrimitiveType> obj1=new NonType<>();
        implementation.enque(obj1.toObj(121.1));
        implementation.enque(obj1.toObj(1000.1));
        System.out.println("List of Elements in Queue : "+implementation.display());
        System.out.println(implementation.deque().getClass()+" is removed");
        System.out.println("Peek is "+implementation.peek());
        System.out.println("List of Elements in Queue : "+implementation.display());
        System.out.println(implementation.deque().getClass()+" is removed");
        System.out.println("List of Elements in Queue : "+implementation.display());
        System.out.println("Peek is "+implementation.peek());


    }
}
