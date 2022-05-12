package org.example;

public class NonPrimitiveType<T> {
   Object ob;
    public  NonPrimitiveType(Object b) {
        ob=b;
          return;
    }

    @Override
    public String toString() {
        return " " +
                " " + ob +
                " ";
    }

    public NonPrimitiveType getObj(int i){
        NonPrimitiveType<T> ab=new NonPrimitiveType(ob);
        return ab;
    }





}
