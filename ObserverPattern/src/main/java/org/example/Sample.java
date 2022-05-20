package org.example;

import java.util.concurrent.Callable;

class Square implements Callable {
    int num;
    Square(int num) {
        this.num = num;
    }

    @Override
    public Object call() throws Exception {
        int result = num*num;
        System.out.println("Square of " + num + " is: " + result);
        return result;
    }

}

public class Sample {
    public static void main(String[] args){












    }




}
