package com.example.airbnb.review.dto;

public record CommentCreateRequest(
        Integer rating,
        String content,
        String title,
        Long memberId,
        Long reviewId
) {
}
