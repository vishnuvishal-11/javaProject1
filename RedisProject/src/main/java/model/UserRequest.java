package model;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;
import org.springframework.validation.annotation.Validated;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@Validated
@EntityScan
@RedisHash("items")
public class UserRequest implements Serializable {
    @Id
    @NotEmpty
    @NotNull(message = "name cannot be null")
    String userName;

    int age = 0;
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


    public void set(String userName, int age, String dob, String location) {
        this.userName = userName;
        this.age = age;
        this.dob = dob;
        this.location = location;
    }


}



