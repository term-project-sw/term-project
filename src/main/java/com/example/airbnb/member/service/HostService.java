package com.example.airbnb.member.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Service;

import com.example.airbnb.house.domain.House;
import com.example.airbnb.member.mapper.HostMapper;
import com.example.airbnb.reservation.domain.Reservation;

@Service
public class HostService {
	private HostMapper hostMapper;
	/**
	 * HostService 생성자
	 * 
	 * @author Seong
	 * @param HostMapper
	 */
	public HostService(HostMapper hostMapper) {
		this.hostMapper = hostMapper;
	}
	/**
	 * host 예약정보리스트 조회
	 * 
	 * @author Seong
	 * @param HostMapper
	 */
	public List<Reservation> getHostResevationListService(Long id){
		//내림차순 초기화
//		if(allParams.get("sortType") == null) {
//			allParams.put("sortType","1");
//		}
//		if(allParams.get("itemsPerPage") == null) {
//			allParams.put("itemsPerPage","10");
//		}
//		
//		int itemsPerPage = Integer.parseInt(allParams.get("itemsPerPage"));
//		int sortType = Integer.parseInt(allParams.get("sortType"));
		
		List<House> houselist=this.getHouseListService(id);
		List<Reservation> data = new ArrayList<Reservation>();
		for( House house : houselist) {
			List<Reservation> reserve = hostMapper.getReservationListSQL(house.getId());
			data.addAll(reserve);
		}
		return data;
	}
	public List<House> getHouseListService(Long id){
		List<House> data = hostMapper.getHouseListSQL(id);
		return data;
	}
//	public String updateReservationStatusService(Long id,int state) {
//		String result="";
//		if(state==1) 
//		{
//			hostMapper.updateReservationStatusSQL(id,"COMPLETE");
//			result="success";
//		}else
//		{
//			hostMapper.updateReservationStatusSQL(id, "CANCEL");
//			result="sucess";
//		}
//		return result;
//	}
}
