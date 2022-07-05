package com.example.dbproject.model.user;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "tring_membership")
public class TringMembership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "membership_code", nullable = false)
    private Long id;

    @Column(name = "valid_till", nullable = true)
    private Date valid_till=null;

    @Column(name = "created_at", nullable = true)
    private Date created_at=null;

    @Column(name = "status", nullable = true)
    private Boolean status=null;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    @MapsId
    private Users users;


    public TringMembership(Date valid_till, Date created_at, Boolean status) {
        this.valid_till = valid_till;
        this.created_at = created_at;
        this.status = status;
     //   this.user=user;,Users user
    }

}
