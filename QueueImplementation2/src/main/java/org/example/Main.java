package org.example;
public class Main {
    static int capacity=10;

    public static void main(String[] args) throws Exception {
        NonType<Double>  obj1=new NonType<>(1001.2);
     // NonType.t = obj1.set(1001.2);
     //   NonType<Double> obj2=  obj1.get();
        Implementation<Double> implementation = new Implementation(capacity);
        implementation.enque(obj1.toObj(1002.1));
        implementation.enque(obj1.toObj(2002.1));
        implementation.enque(obj1.toObj(3002.2));
      //  implementation.enque((Integer)obj1.toObj(2000));
     ///   implementation.enque((Integer)obj1.toObj(3000));
      //  implementation.enque((Integer)obj1.toObj(4000));
      //  implementation.enque((Integer)obj1.toObj(6000));
      //  implementation.enque(6000);
        System.out.println("size is "+implementation.size());
        System.out.println("List of Elements in Queue : "+implementation.display());
        System.out.println(implementation.deque()+" is removed");
        System.out.println("Peek is "+implementation.peek());
        System.out.println("List of Elements in Queue : "+implementation.display());
        System.out.println("size is "+implementation.size());
        System.out.println(implementation.deque()+" is removed");
        System.out.println("List of Elements in Queue : "+implementation.display());
        System.out.println("Peek is "+implementation.peek());
        System.out.println(implementation.deque()+" is removed");
        System.out.println("size is "+implementation.size());

    }
}
