package com.example.airbnb.member.dto;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;

@AllArgsConstructor
@Getter
@Data
public class MemberLoginResponse {

    private Long id;
    private String role;
}
