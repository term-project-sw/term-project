package com.example.airbnb.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class HouseReviewListDTO {
    private String id;
    private String title;
    private String memberName;
    private LocalDate updateAt;
    private int rating;
}
