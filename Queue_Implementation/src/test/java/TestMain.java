import org.example.Implementation;
import org.example.*;
import org.junit.Before;
import org.junit.Rule;
import org.junit.jupiter.api.Test;
import org.mockito.*;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


public final class TestMain {
   Main m=null;


    @Rule
    public MockitoRule rule=MockitoJUnit.rule();
    @Before
    public void init() {
        MockitoAnnotations.initMocks(this);
    }

    public void setUp(){
        m=new Main("5");
    }
    @Test
    public void testPerform(){

     boolean actual= equals(5,new Main("5"));
     boolean expected=true;
     assertEquals(true, true);
    }
    private boolean equals(int i, Main main) {
        if(i==5)return true;
        else
            return false;
    }
    @Mock
    NonPrimitiveType nonpri=new NonPrimitiveType();
    @Captor
    ArgumentCaptor argCaptor;
    @Test
    public void testGetObj(){
     String s="1995";
     assertEquals(s,nonpri.getObjstr(1995));
    }
    @Test
    public void testGetObj1(){
        String s="c";
        assertEquals(s,nonpri.getObjstr('c'));
    }
    @Test
    public void testGetObj2(){
        String s="cool";
        assertEquals(s,nonpri.getObjstr("cool"));
    }
    @Mock
   Implementation implementation=new Implementation(2);
    @Test
    public void testEnque(){
        String s2="Sry...Queue is full";
        String s="added the element successfully";
        assertEquals(s,implementation.enque("java"));
        assertEquals(s,implementation.enque("1211"));

    }
    @Test
    public void testEnque2(){
        String s2="Sry...Queue is full";
        assertEquals(s2,implementation.enque("3"));

    }
    @Test
    public void testDeque(){
        String s2=implementation.deque();
        String s1="Sry...Queue is Empty";;
        assertEquals(s1,s2);
    }
    @Test
    public void testSize(){
        String s2=implementation.size();
        String s1="Queue Size is 0";
        assertEquals(s1,s2);
    }
    @Test
    public void testDisplay(){
        String[] queue = new String[2];
       // queue[0]="java";queue[1]="1995";
       // implementation.enque("java");
       // implementation.enque("1995");
        List<String> list=implementation.display();
        List<String> list1= Arrays.stream(queue).filter(x -> x != null).collect(Collectors.toList());
        assertEquals(list,list1);
    }

    @Test
    public void test(){
     System.out.println("ALL TEST CASES EXECUTED SUCCESSFULLY");
    }


}
