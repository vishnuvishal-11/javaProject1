package org.tech;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import techno.GsonSample;

import java.io.FileWriter;

public class Main extends GsonSample  {
    public Main(int id, String name, String branch, String designation,String[] languages_known) {
        super(id, name, branch, designation,languages_known);
    }

    public static void main(String[] args) {
        Gson gson = new Gson();
      Main m = new Main(1001,"Employee1","Information Technology",
              "Developer",new String[]{"java","mangoDB","spring boot"});
      try (FileWriter writer = new FileWriter("C:\\Users\\vishn\\IdeaProjects\\SampleMavenProject\\target\\classes\\org\\tech\\m.json")){
          gson.toJson(m, writer);}
      catch (Exception e){
          e.printStackTrace();
      }

        System.out.println(gson.toJson(m));
        gson = new GsonBuilder().setPrettyPrinting().create();

    }
}