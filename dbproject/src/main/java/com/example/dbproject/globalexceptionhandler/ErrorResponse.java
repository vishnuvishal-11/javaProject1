package com.example.dbproject.globalexceptionhandler;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
@Getter
public class ErrorResponse {
    private LocalDateTime timeStamp;
    private String message;
    private List<String> fieldErrors = new ArrayList<>();

    public LocalDateTime getTimeStamp() {
        return timeStamp;
    }

    public String getMessage() {
        return message;
    }
}
/*
* {
    "name": "user1",
    "about": "about vivek",
    "languages_known": [
        "english",
        "hindi",
        "tamil"
    ],
    "gender": "male",
    "dob": "2019-04-28T14:45:15",
    "debut": "vs Pakistan at karachi",
    "tags": [
        "#Actor",
        "#fitness",
        "#BigBoss"
    ],
    "plans": [
        {
            "type": "insta_dm",
            "price": 20000
        },
        {
            "type": "recorded_video",
            "price": 10000
        }
    ],
    "qualification":"Engineering in cs",
    "birth_place": "chennai",
    "fb_id":"user fbid",
    "insta_id":"user insta id",
    "marital_status":"single"


}
*
*
*  */