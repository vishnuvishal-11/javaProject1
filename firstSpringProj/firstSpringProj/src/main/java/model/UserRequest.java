package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;


@Getter
@Setter
@NoArgsConstructor
@Component
@Scope(value="prototype")
@EntityScan
public class UserRequest {

    @NonNull
   String userName;
   String age;
    String dob;
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






    public void set(@NonNull String userName, String age, String dob, String location) {
        this.userName = userName;
        this.age = age;
        this.dob = dob;
        this.location = location;
    }



}



