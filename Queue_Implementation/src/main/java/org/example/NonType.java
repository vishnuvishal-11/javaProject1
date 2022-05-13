package org.example;

public class NonType<T> {  T ob;
    public  NonType() {
    }
    public  NonType(Object b) {
        T ob= (T) b;
        return;
    }


    public Object toObj(Object b) { T ob=(T)b;
        return ob;
    }

   // public T getObj(Object b){
        // NonPrimitiveType<T> ab=new NonPrimitiveType(this.toObj(b));
      //  return (T)b;

}

