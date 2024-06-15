package com.example.airbnb.review.mapper;

import com.example.airbnb.review.domain.Review;
import com.example.airbnb.review.dto.HouseReviewDetailDTO;
import com.example.airbnb.review.dto.ReviewDetailDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReviewMapper {
    // 게스트 마이리뷰 리스트
    public List<Review> getReviewListSQL(Map<String, Object> allParams);


    public int getReviewListCountSQL(Map<String, Object> allParams);

    public ReviewDetailDTO getReviewDetailSQL(Map<String, Object> allParams);

    public void updateReviewDetailSQL(Map<String, String> allParams);

    public void removeReviewDetailSQL(Map<String, String> allParams);

    public List<Review> getHouseReviewListSQL(Map<String, Object> allParams);

    public int getHouseReviewListCountSQL(Map<String, Object> allParams);

    public HouseReviewDetailDTO getHouseReviewDetailSQL(Map<String, Object> allParams);

    void insertReviewSQL(Map<String, String> allParams);

    int selectDuplicateReviewSQL(Map<String, String> allParams);
}
