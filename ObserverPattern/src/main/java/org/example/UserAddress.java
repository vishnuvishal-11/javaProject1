package org.example;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import java.util.Date;


@Getter
@Setter
@NoArgsConstructor
public class UserAddress {
    @NonNull
   String userName;
   int age;
    Date dob;
    String location;

//    public UserAddress(String userName, int age, Date dob, String location) {
//    }

    @Override
    public String toString() {
        return "userName:" + userName +" "+
                " age:" + "@" +
                age +
                "@" +" dob:"+ dob +
                "" +" location:"+ location +
                "" ;
    }






    public void set(@NonNull String userName,int age,Date dob,String location) {
        this.userName = userName;
        this.age = age;
        this.dob = dob;
        this.location = location;
    }



}



