package com.example.airbnb.member.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class MemberLoginResponse {

    private Long id;
    private String role;
}
