package com.example.model;

import lombok.*;

import javax.validation.constraints.*;
import java.io.Serializable;
import java.time.LocalDate;

@NoArgsConstructor
@Getter
@Setter
@Data
public class UserRequestDto implements Serializable {


    @NotEmpty
    private String firstName;
    @NotEmpty
    private String lastName;

    private int age = 0;




    @Override
    public String toString() {
        return "UserRequestDto{" +
                ", FirstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", age=" + age +
                ", dob=" + dob +
                ", location='" + location + '\'' +
                '}';
    }

    private LocalDate dob;

    public int getDay() {
        return day;
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @NotEmpty
    @NotNull(message = "loc cannot be null")
    private String location;

    @Max(31)
    int day;

    public UserRequestDto( String firstName,String lastName, int age, LocalDate dob, String location, int day, int year, int month) {
        this.firstName = firstName;
        this.lastName=lastName;
        this.age = age;
        this.dob = dob;
        this.location = location;
        this.day = day;
        this.year = year;
        this.month = month;
    }

    @Min(1700)
    int year;
    @Max(12)
    int month;


}
