package com.example.airbnb.review.service;

import com.example.airbnb.reservation.dto.ReservationDetailDTO;
import com.example.airbnb.review.dto.ReviewDetailDTO;

import java.util.Map;

public interface ReviewServiceIF {
    // 리뷰정보 리스트 Abstract Method
    public Map<String, Object> getReviewListService(Map<String, Object> allParams);

    // 리뷰 상세 정보 Abstract Method
    public ReviewDetailDTO getReviewDetailService(Map<String, Object> allParams);

    // 리뷰 정보 수정 Abstract Method
    public Map<String, Object> modifyReviewService(Map<String, String> allParams);

    // 리뷰 정보 삭제 Abstract Method
    public Map<String, Object> removeReviewService(Map<String, String> allParams);
}
