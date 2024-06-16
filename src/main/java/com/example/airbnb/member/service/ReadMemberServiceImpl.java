package com.example.airbnb.member.service;

import com.example.airbnb.member.domain.Member;
import com.example.airbnb.member.mapper.MemberMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReadMemberServiceImpl implements ReadMemberServiceIF{
    private final MemberMapper memberMapper;

    public ReadMemberServiceImpl(MemberMapper memberMapper) {
        this.memberMapper = memberMapper;
    }

    @Override
    public Member getMemberById(Long id) {
        return memberMapper.getMemberById(id);
    }
}
