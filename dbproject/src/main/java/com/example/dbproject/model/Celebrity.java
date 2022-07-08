package com.example.dbproject.model;


import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.vladmihalcea.hibernate.type.json.JsonBinaryType;
import lombok.*;
import org.hibernate.annotations.*;
import org.springframework.lang.NonNull;
import javax.persistence.*;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import java.io.Serializable;
import java.util.*;

@TypeDef(name = "jsonb", typeClass = JsonBinaryType.class)
@Getter
@Setter
@NoArgsConstructor
@Entity
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.ANY)
public class Celebrity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "celebrity_id", nullable = false)
    private Long id;


    @Column(name = "celebrity_name", nullable = false)
    @NonNull
    private String name;

//    @Column(name = "first_name", nullable = false)
//    @NonNull
//    private String firstName;
//
//    @Column(name = "last_name", nullable = false)
//    @NonNull
//    private String lastName;

    @Column(name = "about", nullable = false)
    @NonNull
    private String about;

    @Type(type = "jsonb")
    @Column(name = "languages_known", nullable = false, columnDefinition = "jsonb")
    @NonNull
    private List<String> languages_known;

    @Column(name = "gender", nullable = false)
    @NonNull
    private String gender;

    @Column(name = "dob", nullable = false)
    @NonNull
    private Date dob;
    @Type(type = "jsonb")
    @Column(name = "tags", nullable = false, columnDefinition = "jsonb")
    @NonNull
    private List<String> tags;

    @Column(name = "qualification")
    private String qualification;

    @Column(name = "birth_place")
    private String birth_place;

    @Column(name = "fb_id")
    private String fb_id;

    @Column(name = "insta_id")
    private String insta_id;

    @Column(name = "debut")
    private String debut;

    @Column(name = "marital_status")
    private String marital_status;

    public List<Price> getPrices() {
        return prices;
    }

    public void setPrices(List<Price> prices) {
        this.prices = prices;
    }

    @OneToMany(targetEntity = Price.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "celebrity_fk", referencedColumnName = "celebrity_id")
    private List<Price> prices = new ArrayList<>();

    public Celebrity(String name, String about, List<String> languages_known, String gender, Date dob, String debut, List<String> tags) {
        this.name = name;
        this.about = about;
        this.languages_known = languages_known;
        this.gender = gender;
        this.dob = dob;
        this.debut = debut;
        this.tags = tags;

    }

    public Celebrity(String name, String about, List<String> languages_known, String gender, Date dob, List<String> tags, String qualification, String birth_place, String fb_id, String insta_id, String debut, String marital_status) {
        this.name = name;
        this.about = about;
        this.languages_known = languages_known;
        this.gender = gender;
        this.dob = dob;
        this.tags = tags;
        this.qualification = qualification;
        this.birth_place = birth_place;
        this.fb_id = fb_id;
        this.insta_id = insta_id;
        this.debut = debut;
        this.marital_status = marital_status;
    }



}
