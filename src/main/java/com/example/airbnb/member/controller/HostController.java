package com.example.airbnb.member.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.servlet.ModelAndView;

import com.example.airbnb.member.service.HostService;

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
}
