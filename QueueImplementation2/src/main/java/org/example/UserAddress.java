package org.example;

import java.util.Date;

public class UserAddress {
   String userName;
   int age;
   Date dob;

    @Override
    public String toString() {
        return "" + userName +
                "" + "@" +
                age +
                "@" + dob +
                "" + location +
                "" ;
    }

    String location;


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }


    
    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Date getDob() {
        return dob;
    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }


}



