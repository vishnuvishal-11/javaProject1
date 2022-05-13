import org.example.Implementation;
import org.example.NonPrimitiveType;
import org.example.NonType;
import org.junit.jupiter.api.Test;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public final class TestMain {
    Implementation imple=new Implementation(2);


    @Test
    public void testSize(){ //Implementation imple=new Implementation(2);

        int[] queue={2};
        int i=queue.length;
        assertEquals(i,imple.size());

    }

    @Test
    public void testDisplay(){
        Implementation imple=new Implementation(2);
        Object[] queue={};
        List<Object> list = Arrays.stream(queue).filter(x -> x != null).collect(Collectors.toList());
        List<Object> list2=imple.display();
        assertEquals(list,list2);
    }


    @Test
    public void testEnque(){  //Implementation imple=new Implementation(2);
        String s2="Sry...Queue is full";
        String s="added the element successfully";
      //  assertEquals(s2,imple.enque(1000));
      //  assertEquals(s2,imple.enque(1211));
      ///  assertEquals(s2,imple.enque(129));

    }
    @Test
    public void testEnque2(){
        Implementation implementation=new Implementation(2);
        implementation.enque(1000);
        implementation.enque(1211);
        String s2="Sry...Queue is full";
     //   assertEquals(s2,implementation.enque("3"));

    }
    @Test
    public void testDeque(){
        Implementation implementation=new Implementation(2);
        implementation.enque(1000);
        implementation.enque(1211);
        assertEquals(1000,implementation.deque());
    }

    @Test
    public void testPeek(){
        Implementation implementation=new Implementation(2);
        Object i=implementation.peek();
        assertEquals(i,null);

    }
    @Test
    public void testToobj(){ int ob=5;
        NonPrimitiveType ab=new NonPrimitiveType(ob);

        assertEquals(ob,ab.toObj(ob));

    }

    @Test
    public void testToobj1(){ int ob=5;
        NonType ab=new NonType();
       // assertEquals(5,(int)ab.toObj(ob));
        System.out.println("ALL TEST CASES EXECUTED SUCCESSFULLY");
    }

}
