package security;


import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Data
@Getter
public class Counter  {
    private int number;
    private Date targetDate;

    public Counter setCounter(int number, Date targetDate) {
        this.number = number;
        this.targetDate = targetDate;
       return null;
    }


}

