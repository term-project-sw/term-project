package com.example.airbnb.reservation.mapper;

import com.example.airbnb.member.domain.Member;
import com.example.airbnb.reservation.domain.Reservation;
import com.example.airbnb.reservation.dto.ReservationDetailDTO;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
import java.util.Map;

@Mapper
public interface ReservationMapper {
    public List<Reservation> getReservationListSQL(Map<String, Object> allParams);

    public int getReservationListCountSQL(Map<String, Object> allParams);

    public ReservationDetailDTO getReservationDetailSQL(Map<String, Object> allParams);

    public void updateReservationDetailSQL(Map<String, String> allParams);

    public void removeReservationDetailSQL(Map<String, String> allParams);
}
