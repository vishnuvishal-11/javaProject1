package org.example;

import lombok.SneakyThrows;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Executor;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CompleteFuture<V> {
    @SneakyThrows
    public CompleteFuture<Void> asynClass() {

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

        Implementation<UserAddress> implementation = new Implementation();

        ExecutorService executors = Executors.newFixedThreadPool(1);
        CompletableFuture.runAsync(() -> {
            System.out.println(" In :" + Thread.currentThread().getName() + "& Priority:" + Thread.currentThread().getPriority());
            System.out.println("-------------------------------");
            implementation.enque(obj1);
            implementation.enque(obj2);
            System.out.println("Time : " +ft.format(new Date()));
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("<*****************************************************************************************************>");
        }, (Executor) executors).thenRunAsync(() -> {
            System.out.println("In :"+Thread.currentThread().getName()+" & Priority is:"+Thread.currentThread().getPriority());
            System.out.println("------------------------------");
            implementation.enque(obj3);
            implementation.enque(obj4);
            implementation.deque();
            implementation.deque();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Time : " +ft.format(new Date()));
            System.out.println("<*****************************************************************************************************>");
        }, (Executor) executors).runAsync(() -> {
            System.out.println("In :"+Thread.currentThread().getName()+" & Priority:"+Thread.currentThread().getPriority());
            System.out.println("------------------------------");
            implementation.deque();
            implementation.deque();
            implementation.deque();
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
               e.printStackTrace();
            }
            System.out.println("Time : " +ft.format(new Date()));
            System.out.println("<*****************************************************************************************************>");
        }, (Executor) executors);


         return null;


    }




    public static void main(String[] args)throws  Exception {


        ObservableSubject<Observer1> observableSubject= new ObservableSubject<>();
        ObservableSubject<Observer2> observableSubject1= new ObservableSubject<>();
        Observer1 observer1=new Observer1(observableSubject);
        Observer2 observer2=new Observer2(observableSubject1);
        observableSubject.add(observer1);
        observableSubject1.add(observer2);
        CompleteFuture completeFuture =new CompleteFuture<>();

        completeFuture.asynClass();


    }


}
