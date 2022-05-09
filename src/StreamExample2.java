import java.util.Map;
import java.util.HashMap;
import java.util.Set;
import java.util.TreeSet;
import java.util.stream.Collectors;


public class StreamExample2 {
    public static void main(String[] args) throws Exception {

        Map<String, Integer> map = new HashMap<>();
        map.put("java", 10021);map.put("python", 10022);map.put("struct", 10023);
        map.put("clang", 10024);map.put("nodejs", 10025);map.put("mangodb", 10026);
        map.put("html", 10027);map.put("css", 10028);map.put("javascript",10029);map.put("spring_boot", 10030);

        Set<String> hs = new TreeSet<>();
        hs.add("java");  hs.add("python");  hs.add("css");  hs.add("clang");  hs.add("html");  hs.add("nodejs");  hs.add("mangodb");

        Set<String> s = map.keySet();s.removeAll(hs);
  //      Set<String> h=  s.stream().collect(Collectors.toSet());
        System.out.println("Keys exists in map but not in set are...");
        System.out.println(s);
        hs.addAll(s);
        System.out.println("After adding the remaining keys to the set");
        System.out.println(hs);
    }
}
