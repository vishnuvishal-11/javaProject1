package org.example;

import java.text.SimpleDateFormat;

public class QueueControl {


    public static void main(String[] args) throws Exception {
        UserAddress obj1 = new UserAddress();
        UserAddress obj2 = new UserAddress();
        UserAddress obj3 = new UserAddress();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
//        obj1.setAge(25);
//        obj1.setUserName("SeniorEmployee");
//        obj1.setLocation(" Chennai");
//        obj1.setDob(formatter.parse("11/11/1995"));
//        obj2.setUserName("emp1");
//        obj2.setLocation(" Chennai");
//        obj2.setDob(formatter.parse("17/12/1995"));
        obj1.set("emp1", 25, formatter.parse("11/11/1994"), "chennai");
        obj2.set("emp2", 26, formatter.parse("17/12/1995"), "chennai");
        obj3.set("emp3", 27, formatter.parse("20/107/1996"), "chennai");

        Implementation<UserAddress> implementation = new Implementation();


        Thread t1 = new Thread(() -> {
            implementation.enque(obj1);});
        Thread t2 = new Thread(() -> {
            implementation.enque(obj2);});
        Thread t3 = new Thread(() -> {
            implementation.enque(obj3);});
        t1.start();
//        try{Thread.sleep(1000);}
//        catch (Exception e){ e.printStackTrace();}
        t1.join();                        //Or can be done with sleep inside try-catch
        t2.start();
        t2.join();
//        try{  Thread.sleep(1000);}
//        catch (Exception e){ }
        t3.start();
        t3.join();
//        try{  Thread.sleep(1000);}
//        catch (Exception e){ }
//        try {



//        }
//        catch (Exception e){ }//tr

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
