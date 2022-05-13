package org.example;

public class UserAddress<T> {
   T ob;

    public T get() {
        return ob;
    }

    public void set(T ob) {
        this.ob = ob;
    }
    public UserAddress toT(){
        return (UserAddress) ob;

    }
}


