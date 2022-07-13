package com.example.dbproject.dto;

import lombok.*;
import lombok.extern.slf4j.Slf4j;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.*;

@Slf4j
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
//@RequiredArgsConstructor
public class CelebrityDto {


    private Long id;

    @NotNull
    @NotEmpty
    private String firstName;

    @NotNull
    @NotEmpty
    private String lastName;

    @NotNull
    @NotEmpty
    private String about;
    @NotNull
    @NotEmpty
    private List<String> languages_known;

    @NotNull
    @NotEmpty
    private String gender;

    private Date dob;
    private String debut;
    @NotNull
    @NotEmpty
    private List<String> tags;
    @NotNull
    @NotEmpty
    private List<Object> plans;
    private String qualification;
    private String birth_place;
    private String fb_id;
    private String insta_id;
    private String marital_status;

//    public CelebrityDto(Long id, String name, String about, List<String> languages_known, String gender, Date dob, String debut, List<String> tags, List<Object> plans) {
//        this.id = id;
//        this.name = name;
//        this.about = about;
//        this.languages_known = languages_known;
//        this.gender = gender;
//        this.dob = dob;
//        this.debut = debut;
//        this.tags = tags;
//        this.plans = plans;
//    }
//
//    public CelebrityDto(Long id, String name, String about, List<String> languages_known, String gender, Date dob,
//                        String debut, List<String> tags, List<Object> plans, String qualification, String birth_place,
//                        String fb_id, String insta_id, String marital_status) {
//        this.id = id;
//        this.name = name;
//        this.about = about;
//        this.languages_known = languages_known;
//        this.gender = gender;
//        this.dob = dob;
//        this.debut = debut;
//        this.tags = tags;
//        this.plans = plans;
//        this.qualification = qualification;
//        this.birth_place = birth_place;
//        this.fb_id = fb_id;
//        this.insta_id = insta_id;
//        this.marital_status = marital_status;
//    }
}
