package org.tech;
import com.google.gson.Gson;
import techno.GsonSample;
import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws Exception {
        Gson gson = new Gson();
        GsonSample m = new GsonSample("1001","Employee1","Information Technology",
              "Developer",new String[]{"java","mangoDB","spring boot"});
        GsonSample m2 = new GsonSample("1001","Employee1","Information Technology",
                "Developer",new String[]{"java","mangoDB","spring boot"});
        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
        Model mo = new Model(5510,"Inspiron 15 series",
                    sdf.parse("11-11-2011"),"Dell", Arrays.asList(m,m2));
        try (FileWriter writer = new
              FileWriter("C:\\Users\\vishn\\IdeaProjects\\MavenProject\\target\\classes\\org\\tech\\m.json"))
      {
          gson.toJson(mo, writer);}
      catch (Exception e){
          e.printStackTrace();
      }
        System.out.println(gson.toJson(mo));

    }
}