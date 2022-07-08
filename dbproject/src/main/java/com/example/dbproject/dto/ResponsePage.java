package com.example.dbproject.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;


@NoArgsConstructor
@Getter
@Setter
public class ResponsePage<T> {
//    int recordCount;
//    T response;

    public ResponsePage( List<T> list) {
    }
}
