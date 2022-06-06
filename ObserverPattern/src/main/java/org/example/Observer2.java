package org.example;

import java.util.List;

public class Observer2<T> implements Observer {
    ObservableSubject<T> observableSubject;

    public Observer2(ObservableSubject<T> observableSubject) {
        this.observableSubject = observableSubject;
    }

    @Override
    public String toString() {
        return "Observer2" ;
    }

    @Override
    public void update(String s, List list) {
        System.out.print("Observer2 Updated :");
        System.out.println(s);
        System.out.println("Queue ->"+list);
        System.out.println("<--------------------------------------------------->");
    }
}
