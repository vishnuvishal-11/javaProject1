package model;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.validation.annotation.Validated;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;


@Getter
@Setter
@NoArgsConstructor
@Validated
public class UserRequest {
@NotEmpty
@NotNull(message = "name cannot be null")
    String userName;

    int age=0;
    @NotEmpty
    @NotNull(message = "dob cannot be null")
    String dob;
    @NotEmpty
    @NotNull(message = "dob cannot be null")
    String location;




    @Override
    public String toString() {
        return "userName:" + userName + " " +
                " age:" +
                age +
                 " dob:" + dob +
                "" + " location:" + location +
                "";
    }

    public void set( String userName,int age , String dob, String location) {
            this.userName = userName;
            this.age = age;
            this.dob = dob;
            this.location = location;
    }


}



