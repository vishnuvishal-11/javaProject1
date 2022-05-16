package org.example;

import java.text.SimpleDateFormat;

public class QueueControl {


    public static void main(String[] args) throws Exception {
        UserAddress obj1 = new UserAddress();
        UserAddress obj2 = new UserAddress();
        UserAddress obj3 = new UserAddress();
       // UserAddress obj4 = new UserAddress();
        //Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        obj1.setAge(25);
        obj1.setUserName("SeniorEmployee");
        obj1.setLocation(" Chennai");
        obj1.setDob(formatter.parse("11/11/1995"));
        obj2.setUserName("emp1");
        obj2.setLocation(" Chennai");
        obj2.setDob(formatter.parse("17/12/1995"));
        obj3.set("emp3",26,formatter.parse("11/11/1995"),"chennai");

        Implementation<UserAddress> implementation = new Implementation();
        implementation.enque(null);
        implementation.enque(obj1);
        implementation.enque(obj2);
        implementation.enque(obj3);
       // implementation.enque(obj4);
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
