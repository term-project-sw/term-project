package com.example.airbnb.member.controller;

import com.example.airbnb.member.domain.Member;
import com.example.airbnb.member.service.ReadMemberServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping("/members")
public class ReadMemberController {
    private final ReadMemberServiceIF readMemberService;

    public ReadMemberController(ReadMemberServiceIF readMemberService) {
        this.readMemberService = readMemberService;
    }

    // 멤버 조회
    @GetMapping("/{id}")
    public String getMember(@PathVariable Long id, Model model) {
        Member member = readMemberService.getMemberById(id);
        model.addAttribute("member", member);
        return "memberInfo";
    }

    // 멤버 정보 수정
    @GetMapping("/myinfo-edit/{id}")
    public ModelAndView showMyInfoEditForm(@PathVariable Long id) {
        Member member = readMemberService.getMemberById(id);
        ModelAndView mav = new ModelAndView("member/myinfo-edit"); // JSP 파일의 이름
        mav.addObject("member", member);
        return mav;
    }
}
