package com.example.airbnb.member.service;

import com.example.airbnb.member.domain.Member;

public interface ReadMemberServiceIF {
    public Member getMemberById(Long id);
}
