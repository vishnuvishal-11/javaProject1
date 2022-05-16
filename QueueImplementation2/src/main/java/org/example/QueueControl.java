package org.example;

import java.util.Date;

public class QueueControl {


    public static void main(String[] args) throws Exception {
        UserAddress obj1 = new UserAddress();
        UserAddress obj2 = new UserAddress();
        UserAddress obj3 = new UserAddress();
        UserAddress obj4 = new UserAddress();
        Date date = new Date();
        obj1.setAge(25);
        obj2.setUserName("Employee");
        obj3.setLocation("Chennai");
        obj4.setDob(date);
        Implementation<UserAddress> implementation = new Implementation();
        implementation.enque(obj1);
        implementation.enque(obj2);
        implementation.enque(obj3);
        implementation.enque(obj4);
        System.out.println("<----------------------------------------------------------------->");
        System.out.println("List of Elements in Queue : " + implementation.display());
        System.out.println("<----------------------------------------------------------------->");
        implementation.deque();
        System.out.println("Peek is " + implementation.peek());
        System.out.println("List of Elements in Queue : " + implementation.display());
        System.out.println("size is " + implementation.size());
        System.out.println("<----------------------------------------------------------------->");
        implementation.deque();
        System.out.println("Peek is " + implementation.peek());
        System.out.println("List of Elements in Queue : " + implementation.display());
        System.out.println("size is " + implementation.size());
        System.out.println("<----------------------------------------------------------------->");
        implementation.deque();
        System.out.println("Peek is " + implementation.peek());
        System.out.println("List of Elements in Queue : " + implementation.display());
        System.out.println("size is " + implementation.size());
        System.out.println("<----------------------------------------------------------------->");
        implementation.deque();
        System.out.println("Peek is " + implementation.peek());
        System.out.println("List of Elements in Queue : " + implementation.display());
        System.out.println("size is " + implementation.size());
        System.out.println("<----------------------------------------------------------------->");
        implementation.deque();
        System.out.println("Peek is " + implementation.peek());
        System.out.println("List of Elements in Queue : " + implementation.display());
        System.out.println("size is " + implementation.size());
        System.out.println("<----------------------------------------------------------------->");
        implementation.deque();
        System.out.println("Peek is " + implementation.peek());
        System.out.println("List of Elements in Queue : " + implementation.display());
        System.out.println("size is " + implementation.size());
        System.out.println("<----------------------------------------------------------------->");
        implementation.enque(obj1);
        System.out.println("Peek is " + implementation.peek());
        System.out.println("List of Elements in Queue : " + implementation.display());
        System.out.println("size is " + implementation.size());
        System.out.println("<----------------------------------------------------------------->");

    }
}
