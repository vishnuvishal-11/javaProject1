package org.example;

public class NonPrimitiveType<T> {
   T ob;
    public  NonPrimitiveType() {
          return;
    }
    public  NonPrimitiveType(Object b) {
        T ob= (T) b;
        return;
    }


    public Object toObj(Object b) { T ob=(T)b;
        return ob;
    }

  //  public T getObj(Object b){
       // NonPrimitiveType<T> ab=new NonPrimitiveType(this.toObj(b));
     //   return (T)b;
    }


