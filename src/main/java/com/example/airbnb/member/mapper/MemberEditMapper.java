package com.example.airbnb.member.mapper;

import com.example.airbnb.member.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.Map;

@Mapper
public interface MemberEditMapper {
    void updateMember(@Param("id") Long id, @Param("email") String email, @Param("password") String password, @Param("phone") String phone, @Param("name") String name);
    void deleteMember(@Param("id") Long id);
    void deleteMemberReservations(@Param("id") Long id);
    void deleteMemberReview(@Param("id") Long id);
}
