package com.example.dbproject.model.user;


import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
@Entity
@Table(name = "card_details")
public class CardDetails implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id", nullable = false)
    private Long id;

    @Column(name = "card_number", nullable = false)
    private Long card_number;


    @Column(name = "card_holder_name", nullable = false)
    private String card_holder_name;

    @Column(name = "phone_number", nullable = false)
    private Long phone_number;

    @Column(name = "expiry_date", nullable = false)
    private Date expiry_date;

    public CardDetails(Long card_number, String card_holder_name, Long phone_number, Date expiry_date) {
        this.card_number = card_number;
        this.card_holder_name = card_holder_name;
        this.phone_number = phone_number;
        this.expiry_date = expiry_date;
    }

}
