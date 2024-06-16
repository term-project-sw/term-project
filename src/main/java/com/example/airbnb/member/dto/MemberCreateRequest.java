package com.example.airbnb.member.dto;

import com.example.airbnb.member.domain.Role;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MemberCreateRequest {

    private String name;
    private String email;
    private String password;
    private String phone;
    private Role role;

}
