package com.example.airbnb.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import java.time.LocalDate;



@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReviewListDTO {
    private String id;
    private String title;
    private String houseName;
    private LocalDate updateAt;
    private int rating;
}
