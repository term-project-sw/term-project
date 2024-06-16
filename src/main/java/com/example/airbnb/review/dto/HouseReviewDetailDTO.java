package com.example.airbnb.review.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class HouseReviewDetailDTO {
    private String id;
    private String title;
    private String content;
    private LocalDate updatedAt;
    private int rating;
    private String memberName;
    private String comment;
}
