package com.example.dbproject.dto.celebritySection;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import java.util.*;

@Slf4j
@Data
public class CelebrityDto {


    private Long id;
    private String name;
    private String about;
    private List<String> languages_known;


    private String gender;
    private Date dob;
    private String debut;
    private List<String> tags;
    private List<Object> plans;

    public CelebrityDto(Long id, String name, String about, List<String> languages_known, String gender, Date dob, String debut, List<String> tags, List<Object> plans) {
        this.id = id;
        this.name = name;
        this.about = about;
        this.languages_known = languages_known;
        this.gender = gender;
        this.dob = dob;
        this.debut = debut;
        this.tags = tags;
        this.plans = plans;
    }





}
