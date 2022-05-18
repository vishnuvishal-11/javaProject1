package org.example;


import java.util.ArrayList;
import java.util.List;
public class ObservableSubject<T>  {

    static List<Observer> list = new ArrayList();
    private int NumberOfInsertion;
    private int NumberOfDeletion;
    private Observer b;


    public void add(Observer b){
        this.b=b;
        list.add(this.b);
    }
    public void remove(Observer b){
        this.b=b;
        list.remove(this.b);
    }

    public void observerNotify(String s,List list2){
        for (Observer i : list) {
            i.update(s,list2);


        }
    }



}

