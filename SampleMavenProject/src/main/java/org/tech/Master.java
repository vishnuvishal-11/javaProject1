package org.tech;

import com.google.gson.Gson;

import java.io.FileWriter;
import java.text.SimpleDateFormat;
import java.util.Arrays;

public class Master {
    public static void main(String[] args) throws Exception {

Employee employee=new Employee("1011","Employee2","Marketing",
        "Developer",new String[]{"java","mangoDB","spring boot"});
        Employee employee2=new Employee("1012","Employee3","support",
              "Developer",new String[]{"java17","mangoDB","spring boot"});

        SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
EmployeeData employeeData=new EmployeeData(5510,"Inspiron 15 series",
        sdf.parse("22-12-2011"),"Dell", Arrays.asList(employee,employee2));
        Gson gson = new Gson();
        try (FileWriter writer = new
                FileWriter("C:\\Users\\vishn\\IdeaProjects\\SampleMavenProject\\target\\classes\\org\\tech\\m.json"))
        {
            gson.toJson(employeeData, writer);}
        catch (Exception e){
            e.printStackTrace();
        }
        System.out.println(gson.toJson(employeeData));

    }
}
