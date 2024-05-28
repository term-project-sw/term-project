package com.example.airbnb.purchase.domain;

import com.example.airbnb.common.domain.BaseEntity;
import com.example.airbnb.house.domain.House;
import com.example.airbnb.member.domain.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Purchase extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startStayDate;

    private LocalDate endStayDate;

    @ManyToOne
    private Member member;

    @ManyToOne
    private House house;
}

