import org.example.Implementation;
import org.example.UserAddress;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;


class QueueControl {

      static   Implementation<UserAddress> imple ;
     static  UserAddress obj1 ;
     static  UserAddress obj2 ;
     static   List<UserAddress> arrli ;
        @BeforeAll
        public static void setup(){
            imple = new Implementation();
            obj1 = new UserAddress();
            obj2 = new UserAddress();
            arrli = new ArrayList();
            obj1.setAge(25);
            obj2.setUserName("Employee");
        }


@Test
    public void testSize() {

        imple.enque(obj1);
        imple.enque(obj2);

        assertEquals(9, imple.size());


    }

    @Test
    public void testDisplay() {
        obj1.setAge(25);
        obj2.setUserName("Employee");
        imple.enque(obj1);
        imple.enque(obj2);
        imple.deque();
        imple.deque();
        imple.deque();
        imple.deque();



        List<String> list2 = imple.display();
        assertNotEquals("[userName:  age:null dob: location:Chennai, userName:  age: 25  dob: location:, userName:Employee  age:null dob: location:, userName:  age: 25  dob: location:, userName:Employee  age:null dob: location:, userName:  age: 25  dob: location:, userName:Employee  age:null dob: location:]",
                list2);

    }


  @Test
    public void testDeque()throws IndexOutOfBoundsException  {

           // obj1.setAge(25);

           // imple.enque(obj1);
            imple.enque(obj2);
            assertEquals("userName:   age: 121  dob:  location: is removed from queue and is of type : class org.example.UserAddress",
                    imple.deque()+" is removed from queue and is of type : class org.example.UserAddress");

    }

    @Test
    public void testPeek() {

        obj1.setAge(25);
        obj2.setUserName("Employee");
        imple.enque(obj1);
        imple.enque(obj2);
        Object i = imple.peek();
        assertEquals(i, "userName:Employee  age: dob: location:");


    }

    @Test
    public void testGet() {

        UserAddress obj4 = new UserAddress();
        obj4.setAge(121);
        imple.enque(obj4);
        assertEquals(121, obj4.getAge());


    }

    @Test
    public void testGet2() {

        obj1.setAge(25);
        obj2.setUserName("Employee");
        imple.enque(obj1);
        imple.enque(obj2);


        assertEquals("Employee", obj2.getUserName());


    }

    @Test
    public void testGet3() {
        UserAddress obj4 = new UserAddress();
        Date date = new Date();
        Date date2 = new Date();
        obj4.setDob(date);
        imple.enque(obj4);
        assertEquals(date2, obj4.getDob());


    }

    @Test
    public void testGet4() {
        UserAddress obj3 = new UserAddress();
        obj3.setLocation("Chennai");
        imple.enque(obj3);
        assertEquals("Chennai", obj3.getLocation());


    }



}
