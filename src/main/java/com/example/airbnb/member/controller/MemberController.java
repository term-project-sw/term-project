package com.example.airbnb.member.controller;

import ch.qos.logback.core.model.Model;
import com.example.airbnb.member.domain.Member;
import com.example.airbnb.member.dto.MemberCreateRequest;
import com.example.airbnb.member.dto.MemberLoginRequest;
import com.example.airbnb.member.dto.MemberLoginResponse;
import com.example.airbnb.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import com.example.airbnb.member.dto.MemberCreateRequest;
import com.example.airbnb.member.dto.MemberLoginRequest;
import com.example.airbnb.member.dto.MemberLoginResponse;
import com.example.airbnb.member.service.MemberService;

import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Controller
@Slf4j
public class MemberController {

    private final MemberService memberService;

    public MemberController(final MemberService memberService) {
        this.memberService = memberService;
    }

    @GetMapping("/member/register")
    public ModelAndView showRegistrationForm() {
        ModelAndView modelAndView = new ModelAndView("member/member-register");
        modelAndView.addObject("memberCreateRequest", new MemberCreateRequest());
        return modelAndView;
    }

    @PostMapping("/member/register")
    public String registerMember(@RequestBody MemberCreateRequest request) {
        memberService.save(request);
        return "redirect:/member/register";
    }

    @GetMapping("/member/login")
    public ModelAndView showLoginForm() {
        ModelAndView modelAndView = new ModelAndView("/member/member-login");
        modelAndView.addObject("memberLoginRequest", new MemberLoginRequest());
        return modelAndView;
    }

    @PostMapping("/member/login")
    @ResponseBody
    public String login(@RequestBody MemberLoginRequest request, HttpSession session) {
        MemberLoginResponse response = memberService.login(request);
        session.setAttribute("memberId", response.getId());
        session.setAttribute("role", response.getRole());
        return "Login successful";
    }
    @PostMapping("/member/logout")
    public String logout(HttpSession session) {
    	session.invalidate();
    	return "redirect:/main-page.jsp";
    }


    @GetMapping("/guest/mypage")
    public ModelAndView showMyPage() {
        ModelAndView mav = new ModelAndView("member/guest-mypage");
        return mav;
    }
}
