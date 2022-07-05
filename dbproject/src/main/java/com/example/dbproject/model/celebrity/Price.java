package com.example.dbproject.model.celebrity;

import lombok.*;
import javax.persistence.*;
import java.io.Serializable;


@Getter
@Setter
@NoArgsConstructor
@Entity
public class Price implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "type")
    private String type;
    @Column(name = "price")
    private int price;

    public Price(String type, int price) {
        this.type = type;
        this.price = price;
    }


}
