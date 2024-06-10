package com.example.airbnb.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReviewDetailDTO {
    private String id;
    private String title;
    private String content;
    private LocalDate updatedAt;
    private String houseName;
    private int rating;
    private int member;
}
