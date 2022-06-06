package org.example;

import java.util.List;
import java.util.Optional;

public class Observer1<T>implements Observer {
    ObservableSubject<T> observableSubject;

    public Observer1(ObservableSubject<T> observableSubject) {
        this.observableSubject = observableSubject;
    }


    @Override
    public String toString() {
        return "Observer1";
    }

    @Override
    public void update(String s, List list) {
        System.out.print("Observer1 Updated :");
        System.out.println(s);
        System.out.println("Queue ->"+list);
        System.out.println("<--------------------------------------------------->");


    }
}
