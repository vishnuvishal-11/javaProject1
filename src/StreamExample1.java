import java.util.HashSet;
import java.util.*;
import java.lang.*;
import java.util.stream.Collectors;

public class StreamExample1 {
    public static void main(String args[]){
    Set<String>  set = new HashSet<>();
        System.out.println("Enter the number of string u Wish to enter");
    Scanner sn=new Scanner(System.in);
    int p= sn.nextInt();
    String s;
          for(int i=0;i<p;i++){System.out.println("Enter the String "+i+" (Not Case Sensitive):");
        s=sn.next();s=s.toLowerCase();
        //if((s.length()>2)||(s.charAt(0)=='a' ||s.charAt(0)=='c'))
        set.add(s);
          }

     Set<String> sp=          set.stream()
               .filter(str-> (str.charAt(0)=='a' ||str.charAt(0)=='c'||str.charAt(2)== 'a' ||str.charAt(2)=='c')).collect(Collectors.toSet());
                     //  .forEach(System.out::println);

        if(sp.isEmpty()) System.out.println("No Elements Found with first or third letter as 'a' or 'c' ...");
        else System.out.println(sp);
    }
}