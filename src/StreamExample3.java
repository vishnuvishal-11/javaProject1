//SAME PREVIOUS QUESTION BUT USED DYNAMIC APPROACH..
import java.util.*;
import java.util.stream.Collectors;


    public class StreamExample3 {
        public static void main(String[] args)throws Exception {

            Map<String, Integer> map = new HashMap<>();
            Scanner sn=new Scanner(System.in);
            System.out.println("enter the number of entries u wish to enter in MAP");
            int p= sn.nextInt(),i=0;String s;

            while(i<p) {   System.out.println("enter MAP key "+i+ " as String");s=sn.next();
                if(map.containsKey(s)){
                    System.out.println("KEy exists already..try another");
                    continue;}
                System.out.println("enter the value for above key..(int)");
                int value=sn.nextInt();
                map.put(s, value);i++;
            }
            Set<String> st = map.keySet();

            Set<String> hs = new TreeSet<>();
            i=0;System.out.println("Enter the no of entries u need to have in Set");
            System.out.println("Enter value less than"+st.size());
            int size=sn.nextInt();
            if(size<=st.size()) {
                while (i < size) { System.out.println("Entered a key from the given options "+st);
                    s = sn.next();
                    if (st.contains(s)){ hs.add(s);
                    i++;}
                    else{ System.out.println("Entered key does not exist in given map.. try again");
                    }
                }
            } else System.out.println("pls Enter value less than"+st.size());

            st.removeAll(hs);
            Set<String> h=  st.stream().collect(Collectors.toSet());
            System.out.println("Keys exists in map but not in set are...");
            System.out.println(h);
            hs.addAll(h);

            System.out.println("After adding the remaining keys to the set");
            System.out.println(hs);
        }
    }


