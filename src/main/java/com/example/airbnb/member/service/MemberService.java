package com.example.airbnb.member.service;

import com.example.airbnb.member.domain.Member;
import com.example.airbnb.member.dto.MemberCreateRequest;
import com.example.airbnb.member.dto.MemberLoginRequest;
import com.example.airbnb.member.dto.MemberLoginResponse;
import com.example.airbnb.member.repository.MemberRepository;
import org.springframework.stereotype.Controller;

@Controller
public class MemberService {

    private final MemberRepository memberRepository;

    public MemberService(final MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    public void save(final MemberCreateRequest request) {
        final Member member = new Member(
                request.getEmail(),
                request.getPassword(),
                request.getRole()
        );
        memberRepository.save(member);
    }

    public MemberLoginResponse login(MemberLoginRequest reqeust) {
        final Member member = memberRepository.findByEmail(reqeust.getEmail())
                                              .orElseThrow(() -> new IllegalArgumentException("등록되지 않은 메일입니다."));
        if (!member.getPassword().equals(reqeust.getPassword())) {
            throw new IllegalArgumentException("잘못된 비밀번호 입니다.");
        }

        return new MemberLoginResponse(member.getId(), member.getRole().toString());
    }
}
