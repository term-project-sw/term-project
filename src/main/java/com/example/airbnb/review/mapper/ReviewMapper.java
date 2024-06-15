package com.example.airbnb.review.mapper;

import com.example.airbnb.review.domain.Review;
import com.example.airbnb.review.dto.ReviewDetailDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReviewMapper {
    public List<Review> getReviewListSQL(Map<String, Object> allParams);

    public int getReviewListCountSQL(Map<String, Object> allParams);

    public ReviewDetailDTO getReviewDetailSQL(Map<String, Object> allParams);

    public void updateReviewDetailSQL(Map<String, String> allParams);

    public void removeReviewDetailSQL(Map<String, String> allParams);
}
