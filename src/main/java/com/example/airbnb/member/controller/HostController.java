package com.example.airbnb.member.controller;

import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import com.example.airbnb.member.service.HostService;
import com.example.airbnb.reservation.domain.Reservation;

import jakarta.servlet.http.HttpSession;
@Controller
public class HostController {

	private final HostService hostService;
	
	public HostController(HostService hostService) {
		this.hostService= hostService;
	}
	
	@GetMapping("/host/mypage")
	public ModelAndView showMyPage() {
		ModelAndView mav = new ModelAndView("host/host-mypage");
		return mav;
	}
	
	@RequestMapping("/host/host-house-reservation-list")
	public ModelAndView getReservationList(@RequestParam Map<String,String> allParams,HttpSession session) {
		ModelAndView mav= new ModelAndView();
		
		List<Reservation> reservationListObject = hostService.getHostResevationListService(allParams);
		
		mav.addObject("allParams",allParams);
		mav.addObject("reservationList",reservationListObject);
		mav.setViewName("host/host-house-reservation-list");
		
		return mav;
		
	}
	
}
