package com.example.airbnb.member.controller;

import com.example.airbnb.member.domain.Member;
import com.example.airbnb.member.service.UpdateMemberServiceIF;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@RestController
public class UpdateMemberCotroller {

    private final UpdateMemberServiceIF updateMember;


    public UpdateMemberCotroller(UpdateMemberServiceIF updateMember) {
        this.updateMember = updateMember;
    }

    //멤버 정보 수정
    @PutMapping("/members/{id}")
    public ResponseEntity<Void> updateMember(@PathVariable Long id,
                                             @RequestParam String email,
                                             @RequestParam String password,
                                             @RequestParam String phone,
                                             @RequestParam String name) {
        try {
            updateMember.updateMember(id, email, password, phone, name);
            return ResponseEntity.ok().build();
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }

    // 회훤 탈퇴
    @DeleteMapping("/members/{id}")
    public ResponseEntity<Void> deleteMember(@PathVariable Long id) {
        try {
            updateMember.deleteMember(id);
            return ResponseEntity.ok().build();

        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        }
    }


    @GetMapping("/main")
    public ModelAndView redirectToMain() {
        return new ModelAndView("main/main");
    }

}
