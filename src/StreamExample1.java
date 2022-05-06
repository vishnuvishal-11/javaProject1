import java.util.HashSet;
import java.util.*;
import java.util.stream.*;
import java.lang.*;

public class StreamExample1 {
    public static void main(String args[]){
    Set<String>  set = new HashSet<String>();
        System.out.println("Enter the number of string u Wish to enter");
    Scanner sn=new Scanner(System.in);
    int p= sn.nextInt();
    String s;
          for(int i=0;i<p;i++){System.out.println("Enter the String"+i+1+" (Not Case Sensitive):");
        s=sn.next();s=s.toLowerCase();
        if((s.length()>2)||(s.charAt(0)=='a' ||s.charAt(0)=='c'))
        set.add(s);
          }

               set.stream()
               .filter(str-> (str.charAt(0)=='a' ||str.charAt(0)=='c'||str.charAt(2)== 'a' ||str.charAt(2)=='c'))
                       .forEach(System.out::println);

        if(set.isEmpty()) System.out.println("No Elements Found with first or third letter as 'a' or 'c' ...");
    }
}
