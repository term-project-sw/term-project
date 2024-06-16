package com.example.airbnb.member.controller;

import com.example.airbnb.member.dto.MemberCreateRequest;
import com.example.airbnb.member.dto.MemberLoginRequest;
import com.example.airbnb.member.dto.MemberLoginResponse;
import com.example.airbnb.member.service.MemberService;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

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
    public ModelAndView login(@RequestBody MemberLoginRequest request, HttpSession session) {
        MemberLoginResponse response = memberService.login(request);
        session.setAttribute("memberId", response.getId());
        session.setAttribute("role", response.getRole());

        ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/house/houses");
        return modelAndView;
    }

    @PostMapping("/member/logout")
    public ModelAndView logout(HttpSession session) {
        session.invalidate();
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/house/houses");
        return modelAndView;
    }


//    @GetMapping("/guest/mypage")
//    public ModelAndView showMyPage() {
//        ModelAndView mav = new ModelAndView("member/guest-mypage");
//        return mav;
//    }
}
