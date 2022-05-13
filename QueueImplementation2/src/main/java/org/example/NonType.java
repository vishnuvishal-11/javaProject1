package org.example;

public class NonType<T> {


    private T t;

    public NonType(T v) {
        t = v;
        this.get();
        return;
    }

    public T set(T t) {
        this.t = t;
        return null;
    }

    public T get() { this.toObj(this.t);
        return t;
    }

    public NonType() {
        return;
    }


     public T toObj(T b)
     {
     return  b;
    }

    public T getObj(Object b) {
        //  NonPrimitiveType<T> ab=new NonPrimitiveType(this.toObj(b));
        return (T) b;

    }
}

