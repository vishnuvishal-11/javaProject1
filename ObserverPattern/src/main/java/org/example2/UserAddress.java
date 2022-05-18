package org.example2;

import java.util.Date;



public class UserAddress {

   String userName;
   int age;

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

    public UserAddress() {
    }

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






    public void set(String userName,int age,Date dob,String location) {
        this.userName = userName;
        this.age = age;
        this.dob = dob;
        this.location = location;
    }



}



