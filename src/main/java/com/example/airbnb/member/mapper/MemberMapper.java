package com.example.airbnb.member.mapper;

import com.example.airbnb.member.domain.Member;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

@Mapper
public interface MemberMapper {
    Member getMemberById(@Param("id") Long id);
}
