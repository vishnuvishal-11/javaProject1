package com.example.dbproject.dto.userSection;

import com.example.dbproject.model.userSection.Card_details;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

@Slf4j
@Data
public class UserDto {

    private Long id;
    private String name;
    private String password;
    private String email;
    private String invite_code;
    private String referred_code;
    private String status;
    private List<Object> card_details;



//    public UserDto( String name, String password, String email, String invite_code, String referred_code, String status) {
//        this.name = name;
//        this.password = password;
//        this.email = email;
//        this.invite_code = invite_code;
//        this.referred_code = referred_code;
//        this.status = status;
//    }

    public UserDto( Long id,String name, String password, String email, String invite_code, String referred_code, String status,List<Object> card_details) {
        this.id=id;
        this.name = name;
        this.password = password;
        this.email = email;
        this.invite_code = invite_code;
        this.referred_code = referred_code;
        this.status = status;
        this.card_details=card_details;
    }
}
