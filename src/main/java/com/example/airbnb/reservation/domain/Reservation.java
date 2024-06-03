package com.example.airbnb.reservation.domain;

import com.example.airbnb.common.domain.BaseEntity;
import com.example.airbnb.house.domain.House;
import com.example.airbnb.member.domain.Member;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import java.time.LocalDate;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Reservation extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDate startRegisterDate;

    private LocalDate endRegisterDate;

    @Enumerated(EnumType.STRING)
    private Progress progress;

    @ManyToOne
    @JoinColumn
    private Member member;

    @ManyToOne
    @JoinColumn
    private House house;
}
