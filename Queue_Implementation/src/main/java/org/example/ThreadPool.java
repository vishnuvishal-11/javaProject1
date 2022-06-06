package org.example;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThreadPool {
    static final int POOL_SIZE = 3;

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

        SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");
        ExecutorService threadPool = Executors.newFixedThreadPool(POOL_SIZE);
        System.out.println("Performing  Asynchronous Task");

        Runnable r1 = () -> {
            try {
                System.out.println(" In :" + Thread.currentThread().getName() + "& Priority:" + Thread.currentThread().getPriority());
                System.out.println("Time : "+ ft.format(new Date()));
                implementation.enque(obj1);
                System.out.println("<------------------------------------------------------------------------------>");
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        Runnable r2 = () -> {
            try {
                System.out.println(" In :"+Thread.currentThread().getName()+"& Priority:"+Thread.currentThread().getPriority());
                System.out.println("Time : " +ft.format(new Date()));
                implementation.enque(obj2);
                System.out.println("<------------------------------------------------------------------------------>");
                Thread.sleep(1000);
            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        Runnable r3 = () -> {
            try {
                System.out.println(" In :"+Thread.currentThread().getName()+"& Priority:"+Thread.currentThread().getPriority());
                System.out.println("Time : " +ft.format(new Date()));
                implementation.enque(obj3);
                System.out.println("<------------------------------------------------------------------------------>");
                Thread.sleep(1000);

            } catch (Exception e) {
                e.printStackTrace();
            }
        };
        Runnable r4 = () -> {
            try {
                System.out.println(" In :"+Thread.currentThread().getName()+"& Priority:"+Thread.currentThread().getPriority());
                System.out.println("Time : " +ft.format(new Date()));
                implementation.enque(obj4);
                System.out.println("<------------------------------------------------------------------------------>");
            } catch (Exception e) {
                e.printStackTrace();
            }
        };



        threadPool.execute(r1);
        threadPool.execute(r2);
        threadPool.execute(r3);
       // Thread.sleep(10000);
        threadPool.execute(r4);

        System.out.println("<------------------------------------------------------------------------------>");
        threadPool.shutdown();
        Thread.sleep(10000);
        System.out.println("IsShutdown: " + threadPool.isShutdown());
        System.out.println("IsTerminated" + threadPool.isTerminated());

        System.out.println("<------------------------------------------------------------------------------>");
        Thread.sleep(7000);
        System.out.println(" In :"+Thread.currentThread().getName()+"& Priority:"+Thread.currentThread().getPriority());
        System.out.println("Peek is " + implementation.peek());
        System.out.println("List of Elements in Queue : " + implementation.display());
        System.out.println("size is " + implementation.size());




    }


}
