package org.example;

public class QueueControl {
    static int capacity = 10;

    public static void main(String[] args) throws Exception {
        UserAddress<Double> obj1 = new UserAddress<>();
        UserAddress<Double> obj2 = new UserAddress<>();
        UserAddress<Double> obj3 = new UserAddress<>();
        UserAddress<Double> obj4 = new UserAddress<>();
        obj1.set(121.1);
        obj2.set(221.1);
        obj3.set(321.1);
        obj4.set(421.1);
//        Implementation<UserAddress> implementation = new Implementation(capacity);
//        implementation.enque(obj1);
//        implementation.enque(obj2);
//        implementation.enque(obj3);
//        implementation.enque(obj4);

        Implementation<Double> implementation2 = new Implementation(capacity);
        implementation2.enque(obj1.get());
        implementation2.enque(obj2.get());
        implementation2.enque(obj3.get());
        implementation2.enque(obj4.get());


//        System.out.println("size is " + implementation.size());
//        System.out.println("List of Elements in Queue : " + implementation.display());
//        System.out.print(implementation.deque().get());
//        System.out.println(" is removed");
//        System.out.println("Peek is " + implementation.peek());


        System.out.println("size is " + implementation2.size());
        System.out.println("List of Elements in Queue : " + implementation2.display());
        System.out.print(implementation2.deque());
        System.out.println(" is removed");
        System.out.println("Peek is " + implementation2.peek());

    }
}
