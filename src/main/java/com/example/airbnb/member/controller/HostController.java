package com.example.airbnb.member.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import com.example.airbnb.house.domain.House;
import com.example.airbnb.member.service.HostService;
import com.example.airbnb.reservation.domain.Reservation;

import jakarta.servlet.http.HttpSession;

@RestController
@RequestMapping("/host")
public class HostController {

	private final HostService hostService;
	
	public HostController(HostService hostService) {
		this.hostService= hostService;
	}
	
	@GetMapping("/mypage")
	public ModelAndView showMyPage(HttpSession session) {
		ModelAndView mav = new ModelAndView();
		if(session.getAttribute("memberId")!=null) {
			List<House> houseListObject = hostService.getHouseListService((Long) session.getAttribute("memberId"));
			mav.addObject("houseList",houseListObject);
		}
		mav.setViewName("host/host-mypage");
		return mav;
	}
//	@GetMapping("/update_reservation/{id}/{state}")
//	public String updateReservationStatus(@PathVariable Long id,@PathVariable int state) {
//		
//		return "done";
//	}
	@RequestMapping("/host-house-reservation-list")
	public ModelAndView getReservationList(@RequestParam Map<String,String> allParams,HttpSession session) {
		ModelAndView mav= new ModelAndView();
		if(session.getAttribute("memberId")!=null) {
	        List<Reservation> reservationListObject = hostService.getHostResevationListService((Long) session.getAttribute("memberId"));
			mav.addObject("reservationList",reservationListObject);
		}
		
		mav.addObject("allParams",allParams);
		mav.setViewName("host/host-house-reservation-list");
		
		return mav;
		
	}
}
