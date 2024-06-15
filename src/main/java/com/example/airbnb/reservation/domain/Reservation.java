package com.example.airbnb.reservation.domain;

import java.time.LocalDate;

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
import lombok.Builder;
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

    @Builder
    public Reservation(final LocalDate startRegisterDate, final LocalDate endRegisterDate, final Progress progress,
                       final Member member,
                       final House house) {
        this.startRegisterDate = startRegisterDate;
        this.endRegisterDate = endRegisterDate;
        this.progress = progress;
        this.member = member;
        this.house = house;
    }
}
