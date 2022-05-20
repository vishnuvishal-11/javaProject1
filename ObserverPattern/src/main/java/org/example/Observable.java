package org.example;

import java.util.List;

public interface Observable {
     void add(Observer b);
     void remove(Observer b);
     void observerNotify(String s, List list2);

}
