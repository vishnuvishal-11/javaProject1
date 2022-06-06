package org.example;

import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.*;
import java.util.function.BiConsumer;


public class CompleteFuture<U> {

    private BiConsumer<? super String,? super Throwable> fraction;

    @SneakyThrows
    public CompleteFuture<String> asynClass() {
       String e=null;

        UserAddress obj1 = new UserAddress();
        UserAddress obj2 = new UserAddress();
        UserAddress obj3 = new UserAddress();
        UserAddress obj4 = new UserAddress();

        SimpleDateFormat formatter = new SimpleDateFormat("MM/dd/yyyy");
        SimpleDateFormat ft = new SimpleDateFormat("hh:mm:ss");


        obj1.set("emp1", 25, formatter.parse("11/11/1996"), "chennai");
        obj2.set("emp2", 26, formatter.parse("17/12/1995"), "chennai");
        obj3.set("emp3", 27, formatter.parse("20/07/1994"), "chennai");
        obj4.set("emp4", 28, formatter.parse("29/10/1995"), "chennai");

        Implementation<UserAddress> implementation = new Implementation<>();

        ExecutorService executors = Executors.newFixedThreadPool(1);
//
//        CompletableFuture.runAsync(() -> {
//            System.out.println(" In :" + Thread.currentThread().getName() + "& Priority:" + Thread.currentThread().getPriority());
//            System.out.println("-------------------------------");
//            implementation.enque(obj1);
//            implementation.enque(obj2);
//            System.out.println("Time : " +ft.format(new Date()));
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("<*****************************************************************************************************>");
//        }, (Executor) executors).thenRunAsync(() -> {
//            System.out.println("In :"+Thread.currentThread().getName()+" & Priority is:"+Thread.currentThread().getPriority());
//            System.out.println("------------------------------");
//            implementation.enque(obj3);
//            implementation.enque(obj4);
//            implementation.deque();
//            implementation.deque();
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//            System.out.println("Time : " +ft.format(new Date()));
//            System.out.println("<*****************************************************************************************************>");
//        }, (Executor) executors).runAsync(() -> {
//            System.out.println("In :"+Thread.currentThread().getName()+" & Priority:"+Thread.currentThread().getPriority());
//            System.out.println("------------------------------");
//            implementation.deque();
//            implementation.deque();
//            implementation.deque();
//            try {
//                Thread.sleep(3000);
//            } catch (InterruptedException e) {
//               e.printStackTrace();
//            }
//            System.out.println("Time : " +ft.format(new Date()));
//            System.out.println("<*****************************************************************************************************>");
//        }, (Executor) executors);


        CompletableFuture future= CompletableFuture.supplyAsync(() -> {

            try {
                int a=10,b=0;
                System.out.println(a/b);
            } catch (Exception e1) {
                e1.printStackTrace();
               return e1;
            }
//            Callable c = () -> {   // Lambda Expression
//                int a=10,b=0;
//                try {
//               System.out.println(a/b);
//                } catch (ArithmeticException e1) {
//                e1.printStackTrace();
//               return e1.getMessage();
//            }
//
//                return null;
//            };


                return "return was made...";
            },(Executor) executors).whenComplete((fraction,xe)->{
                if(fraction!=null)
                    System.out.println(fraction.toString());
                else
                    System.out.println(xe.getMessage());

        });
        System.out.println("No relation steps....");


       future.get();
      //  System.out.println(future.get());
       // System.out.println(Throwable.getCause());



        return null;


    }





    public static void main(String[] args)throws  Exception {


        ObservableSubject<Observer1> observableSubject= new ObservableSubject<>();
        ObservableSubject<Observer2> observableSubject1= new ObservableSubject<>();
        Observer1<Observer1> observer1=new Observer1<>(observableSubject);
        Observer2<Observer2> observer2=new Observer2<>(observableSubject1);
        observableSubject.add(observer1);
        observableSubject1.add(observer2);
        CompleteFuture completeFuture =new CompleteFuture<>();

        completeFuture.asynClass();




    }
}



