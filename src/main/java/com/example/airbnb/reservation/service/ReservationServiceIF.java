package com.example.airbnb.reservation.service;

import com.example.airbnb.reservation.dto.ReservationDetailDTO;

import java.util.Map;

public interface ReservationServiceIF {

    // 예약정보 리스트 Abstract Method
    public Map<String, Object> getReservationListService(Map<String, Object> allParams);

    // 예약 상세 정보 Abstract Method
    public ReservationDetailDTO getReservationDetailService(Map<String, Object> allParams);

    // 예약정보 수정 Abstract Method
    public Map<String, Object> modifyReservationService(Map<String, String> allParams);

    // 예약정보 삭제 Abstract Method
    public Map<String, Object> removeReservationService(Map<String, String> allParams);
}
