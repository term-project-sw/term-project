package com.example.airbnb.member.dto;

import com.example.airbnb.member.domain.Role;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;


@NoArgsConstructor
@AllArgsConstructor
@Getter
public class MemberCreateRequest {

    private String email;
    private String password;
    private Role role;

}
