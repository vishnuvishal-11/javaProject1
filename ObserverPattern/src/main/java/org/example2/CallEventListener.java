package org.example2;


import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CallEventListener {




    public CallEventListener() {

    }

    public void doAsynFuctions(CallBack callback) throws ParseException {


        System.out.println("Performing  Asynchronous Task");
        System.out.println("Inserting Objects in queue");
        callback.callBack();

//        ObservableSubject<Observer1> observableSubject= new ObservableSubject<>();
//        ObservableSubject<Observer2> observableSubject1= new ObservableSubject<>();
//        Observer1 observer1=new Observer1(observableSubject);
//        Observer2 observer2=new Observer2(observableSubject1);
//        observableSubject.add(observer1);
//        observableSubject1.add(observer2);


//        new Thread(() -> {
//            System.out.println("Performing operation in Asynchronous Task");
//            System.out.println(" In :" + Thread.currentThread().getName() + "& Priority:" + Thread.currentThread().getPriority());
//            System.out.println("-------------------------------");
//
//            System.out.println("<*****************************************************************************************************>");
//            callback.callBack();
//
//        },"thread1").start();
//
//        new Thread(() -> {
//            System.out.println("Performing operation in Asynchronous Task");
//            System.out.println(" In :" + Thread.currentThread().getName() + "& Priority:" + Thread.currentThread().getPriority());
//            System.out.println("-------------------------------");
//
//            implementation.deque();
//            System.out.println("<*****************************************************************************************************>");
//            callback.callBack();
//
//        }).start();


    }

    public static void main(String[] args) throws ParseException {
        ExecutorService executors = Executors.newFixedThreadPool(2);
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
        Thread.currentThread().setPriority(9);
//        new Thread(() -> {
//
//        },"thread1").start();


        CallBack callback = () -> {
            System.out.println(" In :" + Thread.currentThread().getName() + "& Priority:" + Thread.currentThread().getPriority());
            implementation.enque(obj1);
            implementation.enque(obj2);
            implementation.deque();
            implementation.enque(obj3);
            implementation.enque(obj4);
            System.out.println("Performing callback after Asynchronous Task");

            return null;
        };
        executors.execute(()->{
            System.out.println("Performing operation in Asynchronous Task in thread");
            Thread.currentThread().setPriority(6);
            System.out.println(" In :" + Thread.currentThread().getName() + "& Priority:" + Thread.currentThread().getPriority());
            System.out.println("-------------------------------");
            implementation.enque(obj1);
            implementation.enque(obj2);
            System.out.println("<*****************************************************************************************************>");
        });
       // executors.execute(main());


        CallEventListener callEventListener = new CallEventListener();
        callEventListener.doAsynFuctions(callback);



    }


}
