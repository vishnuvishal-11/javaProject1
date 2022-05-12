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

        int[] queue={};
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
        assertEquals(s2,imple.enque(1000));
        assertEquals(s2,imple.enque(1211));
        assertEquals(s2,imple.enque(129));

    }
    @Test
    public void testEnque2(){
        Implementation implementation=new Implementation(2);
        implementation.enque(1000);
        implementation.enque(1211);
        String s2="Sry...Queue is full";
        assertEquals(s2,implementation.enque("3"));

    }
    @Test
    public void testDeque(){
        Implementation implementation=new Implementation(2);
        implementation.enque(1000);
        implementation.enque(1211);
       // implementation.deque();
       //implementation.deque();
        int s1=1000;
        assertEquals(s1,implementation.deque());
    }

    @Test
    public void testPeek(){
        Implementation implementation=new Implementation(2);
        Object i=implementation.peek();
        String j=null;
        assertEquals(i,j);

    }
    @Test
    public void testGetobj(){ int ob=5;
        NonPrimitiveType ab=new NonPrimitiveType(ob);

        assertNotEquals(ob,ab.getObj(ob));

    }
    @Test
    public void testToString(){ Object ob=5;
        NonPrimitiveType ab=new NonPrimitiveType(ob);

        assertNotEquals("5",ab.toString());

        assertNotEquals(ob,new NonPrimitiveType(ob));

    }
    @Test
    public void testGetobj2(){ int ob=5;
        NonPrimitiveType ab=new NonPrimitiveType(ob);

        assertNotEquals(ob,ab.getObj(ob));

    }
    @Test
    public void testToString2(){ Object ob=5;
        NonType abc=new NonType(ob);

        assertNotEquals("5",abc.toString());

        assertNotEquals(ob,new NonType(ob));
        System.out.println("ALL TEST CASES EXECUTED SUCCESSFULLY");
    }

}
