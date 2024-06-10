package com.example.airbnb.member.service;

public interface UpdateMemberServiceIF {
    void updateMember(Long id, String email, String password, String phone, String name);
    void deleteMember(Long id);
}
