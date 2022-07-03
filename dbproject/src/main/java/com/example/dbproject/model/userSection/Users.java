package com.example.dbproject.model.userSection;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Data
@NoArgsConstructor
@Entity
@Table(name ="users" )
public class Users implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "user_name", nullable = false)
    private String name;

    @Column(name = "password", nullable = false)
    private String password;


    @Column(name = "email", nullable = false,unique = true)
    private String email;

    @Column(name = "invite_code", nullable = false,unique = true,updatable = false)
    private String invite_code;

    @Column(name = "referred_code",updatable = false)
    private String referred_code;

    @Column(name = "status", nullable = false)
    private String status;

    @OneToMany(targetEntity = Card_details.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_fk", referencedColumnName = "user_id")
    private List<Card_details> card_details = new ArrayList<>();


    public Users(String name, String password, String email, String invite_code, String referred_code, String status) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.invite_code = invite_code;
        this.referred_code = referred_code;
        this.status = status;
    }
}
