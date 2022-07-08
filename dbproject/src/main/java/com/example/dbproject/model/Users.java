package com.example.dbproject.model;

import com.example.dbproject.model.CardDetails;
import com.example.dbproject.model.TringMembership;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.annotations.LazyToOne;
import org.hibernate.annotations.LazyToOneOption;
import org.springframework.lang.NonNull;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name ="users" )
public class Users implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "user_name", nullable = false)
    @NonNull
    private String name;

    @Column(name = "password", nullable = false)
    @NonNull
    private String password;

    @Column(name = "email", nullable = false,unique = true)
    @NonNull
    private String email;

    @Column(name = "invite_code", nullable = false,unique = true,updatable = false)
    @NonNull
    private String invite_code;

    @Column(name = "referred_code",updatable = false)
    @NonNull
    private String referred_code;

    @Column(name = "status", nullable = false)
    @NonNull
    private String status;

    @OneToMany(targetEntity = CardDetails.class, cascade = CascadeType.ALL, orphanRemoval = true)
    @JoinColumn(name = "user_fk", referencedColumnName = "user_id")
    private List<CardDetails> card_details = new ArrayList<>();

    @OneToOne(mappedBy = "users", cascade = CascadeType.ALL, orphanRemoval = true, fetch = FetchType.LAZY)
    @PrimaryKeyJoinColumn
    @LazyToOne( LazyToOneOption.NO_PROXY )
    private TringMembership tring_membership;

    public Users(String name, String password, String email, String invite_code, String referred_code, String status) {
        this.name = name;
        this.password = password;
        this.email = email;
        this.invite_code = invite_code;
        this.referred_code = referred_code;
        this.status = status;

    }
}
