package org.example;


import java.util.ArrayList;
import java.util.List;
public class ObservableSubject<T>implements Observable  {

    static List<Observer> list = new ArrayList();

    private Observer b;

@Override
    public void add(Observer b){
        this.b=b;
        list.add(this.b);
    }
    @Override
    public void remove(Observer b){
        this.b=b;
        list.remove(this.b);
    }
@Override
    public void observerNotify(String s,List list2){
        for (Observer i : list) {
            i.update(s,list2);


        }
    }



}

