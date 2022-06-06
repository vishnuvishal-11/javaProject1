package org.example;

import java.text.SimpleDateFormat;

public class QueueControl {


    public static void main(String[] args) throws Exception {
        UserAddress obj1 = new UserAddress();
        UserAddress obj2 = new UserAddress();
        UserAddress obj3 = new UserAddress();
        UserAddress obj4 = new UserAddress();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");

        obj1.set("emp1", 25, formatter.parse("11/11/1996"), "chennai");
        obj2.set("emp2", 26, formatter.parse("17/12/1995"), "chennai");
        obj3.set("emp3", 27, formatter.parse("20/07/1994"), "chennai");
        obj4.set("emp4", 28, formatter.parse("29/10/1995"), "chennai");

        Implementation<UserAddress> implementation = new Implementation();


        Thread t1 = new Thread(() -> {
            System.out.println(" In :"+Thread.currentThread().getName()+"& Priority:"+Thread.currentThread().getPriority());
            implementation.enque(obj1);
            implementation.enque(obj2);
            System.out.println("<------------------------------------------------------------------------------>");
            },"Thread1");
        Thread t2 = new Thread(() -> {
            System.out.println("In :"+Thread.currentThread().getName()+" & Priority is:"+Thread.currentThread().getPriority());
            implementation.enque(obj3);
            implementation.enque(obj4);
            implementation.deque();
            implementation.deque();
            System.out.println("<------------------------------------------------------------------------------->");
            },"Thread2");
        Thread t3 = new Thread(() -> {
            System.out.println("In :"+Thread.currentThread().getName()+" & Priority:"+Thread.currentThread().getPriority());
            implementation.deque();
            implementation.deque();
            implementation.deque();
            System.out.println("<-------------------------------------------------------------------------------->");
            },"Thread3");


        t1.start();
        t1.join();
        System.out.println("Is "+t1.getName()+" alive now: "+ t1.isAlive());
        System.out.println("<------------------------------------------------------------------------------>");
        t2.start();
        t2.join();
        System.out.println("Is "+t2.getName()+" alive now: "+ t2.isAlive());
        System.out.println("<------------------------------------------------------------------------------>");
        t3.start();
        t3.join();
        System.out.println("Is "+t3.getName()+" alive now: "+ t3.isAlive());
        System.out.println("<------------------------------------------------------------------------------>");

        System.out.println("In :"+Thread.currentThread().getName()+" Thread"+" & Priority:"+Thread.currentThread().getPriority());
        System.out.println("Peek is " + implementation.peek());
        System.out.println("List of Elements in Queue : " + implementation.display());
        System.out.println("size is " + implementation.size());


//        System.out.println("<----------------------------------------------------------------->");
//        System.out.println("size is " + implementation.size());
//        System.out.println("List of Elements in Queue : " + implementation.display());
//        System.out.println("<----------------------------------------------------------------->");
//        implementation.deque();
//        System.out.println("Peek is " + implementation.peek());
//        System.out.println("List of Elements in Queue : " + implementation.display());
//        System.out.println("size is " + implementation.size());
//        System.out.println("<----------------------------------------------------------------->");
//        implementation.deque();
//        System.out.println("Peek is " + implementation.peek());
//        System.out.println("List of Elements in Queue : " + implementation.display());
//        System.out.println("size is " + implementation.size());
//        System.out.println("<----------------------------------------------------------------->");
//        implementation.deque();
//        System.out.println("Peek is " + implementation.peek());
//        System.out.println("List of Elements in Queue : " + implementation.display());
//        System.out.println("size is " + implementation.size());
//        System.out.println("<----------------------------------------------------------------->");
//        implementation.deque();
//        System.out.println("Peek is " + implementation.peek());
//        System.out.println("List of Elements in Queue : " + implementation.display());
//        System.out.println("size is " + implementation.size());
//        System.out.println("<----------------------------------------------------------------->");
//        implementation.deque();
//        System.out.println("Peek is " + implementation.peek());
//        System.out.println("List of Elements in Queue : " + implementation.display());
//        System.out.println("size is " + implementation.size());
//        System.out.println("<----------------------------------------------------------------->");
//        implementation.deque();
//        System.out.println("Peek is " + implementation.peek());
//        System.out.println("List of Elements in Queue : " + implementation.display());
//        System.out.println("size is " + implementation.size());
//        System.out.println("<----------------------------------------------------------------->");
//        implementation.enque(obj1);
//        System.out.println("Peek is " + implementation.peek());
//        System.out.println("List of Elements in Queue : " + implementation.display());
//        System.out.println("size is " + implementation.size());
//        System.out.println("<----------------------------------------------------------------->");

    }
}
