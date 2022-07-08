package com.example.dbproject.model;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.io.IOException;
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
    @JsonProperty
    @JsonSerialize(using=NumericBooleanSerializer.class)
    @JsonDeserialize(using=NumericBooleanDeserializer.class)
    @Column(name = "status", nullable = true)
    private Boolean status=null;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id",referencedColumnName = "user_id")
    @MapsId
    @JsonIgnore
    private Users users;


    public TringMembership(Date valid_till, Date created_at, Boolean status) {
        this.valid_till = valid_till;
        this.created_at = created_at;
        this.status = status;
     //   this.user=user;,Users user
    }
    public static class NumericBooleanSerializer extends JsonSerializer<Boolean> {

        @Override
        public void serialize(Boolean bool, JsonGenerator generator, SerializerProvider provider) throws IOException, JsonProcessingException {
            generator.writeString(bool ? "1" : "0");
        }
    }

    public static class NumericBooleanDeserializer extends JsonDeserializer<Boolean> {

        @Override
        public Boolean deserialize(JsonParser parser, DeserializationContext context) throws IOException, JsonProcessingException {
            return !"0".equals(parser.getText());
        }
    }

}

