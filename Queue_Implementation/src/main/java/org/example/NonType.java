package org.example;

public class NonType<T> {  Object ob;
    public NonType(Object b) { ob=b;
        return;
    }

    @Override
    public String toString() {
        return " " +
                " " + ob +
                " ";
    }

    public NonType getObj(Object b){
        NonType<T> ab=new NonType(b);
        return ab;
    }
}

