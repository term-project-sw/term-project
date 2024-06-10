package com.example.airbnb.member.service;

import com.example.airbnb.member.domain.Member;
import com.example.airbnb.member.mapper.MemberEditMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UpdateMemberServiceImpl implements UpdateMemberServiceIF{
    private final MemberEditMapper memberEditMapper;


    public UpdateMemberServiceImpl(MemberEditMapper memberEditMapper) {
        this.memberEditMapper = memberEditMapper;
    }

    @Override
    public void updateMember(Long id, String email, String password, String phone, String name) {
        memberEditMapper.updateMember(id, email, password, phone, name);
    }

    @Override
    public void deleteMember(Long id) { // 추가
        System.out.println(id);
        memberEditMapper.deleteMember(id);
    }

}
