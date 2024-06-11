package com.example.airbnb.member.mapper;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.example.airbnb.reservation.domain.Reservation;

@Mapper
public interface HostMapper {
	public List<Reservation> getReservationListSQL(Map<String,String> allParams);
}
