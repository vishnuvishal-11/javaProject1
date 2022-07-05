package com.example.dbproject.model.userSection;

import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.*;
import java.util.Date;

@Data
@NoArgsConstructor
@Entity
@Table(name ="tring_membership" )
public class Tring_membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "membership_code", nullable = false)
    private Long id;

    @Column(name = "valid_till", nullable = true)
    private Date valid_till;

    @Column(name = "created_at", nullable = true)
    private Date created_at;

    @Column(name = "status", nullable = true)
    private Boolean status;
    @OneToOne
    @MapsId
    @JoinColumn(name = "user_id",nullable = true)
    private Users users;
}
