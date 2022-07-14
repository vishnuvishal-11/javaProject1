package com.example.dbproject.model;


import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "credentials")
public class Credentials implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Long id;
    @Column(name = "username", nullable = false,unique = true)
    private String userName;
    @Column(name = "password", nullable = false)
    private String password;
    @Column(name = "role", nullable = false)
    private String role;
    @Column(name = "authority", nullable = false)
    private String authority;
    @Column(name = "status", nullable = false)
    private String status;

    @Enumerated(EnumType.STRING)
    private Provider provider;
    public Credentials(String username, String password, String role, String authority,String status) {
        this.userName = username;
        this.password = password;
        this.role = role;
        this.authority = authority;
        this.status=status;
    }
}
