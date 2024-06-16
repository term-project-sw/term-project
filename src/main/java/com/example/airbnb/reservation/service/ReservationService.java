package com.example.airbnb.reservation.service;

import com.example.airbnb.house.domain.House;
import com.example.airbnb.house.repository.HouseRepository;
import com.example.airbnb.member.domain.Member;
import com.example.airbnb.member.repository.MemberRepository;
import com.example.airbnb.reservation.domain.Progress;
import com.example.airbnb.reservation.domain.Reservation;
import com.example.airbnb.reservation.mapper.ReservationRepository;
import java.time.LocalDate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class ReservationService {

    private final ReservationRepository reservationRepository;
    private final HouseRepository houseRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void makeReservation(Long houseId, Long memberId, LocalDate startRegisterDate, LocalDate endRegisterDate) {
        House house = houseRepository.findById(houseId)
                                     .orElseThrow(() -> new IllegalArgumentException("Invalid house ID"));
        Member member = memberRepository.findById(memberId)
                                        .orElseThrow(() -> new IllegalArgumentException("Invalid member ID"));
        Reservation reservation = Reservation.builder()
                                             .startRegisterDate(startRegisterDate)
                                             .endRegisterDate(endRegisterDate)
                                             .progress(Progress.IN_PROGRESS)
                                             .house(house)
                                             .member(member)
                                             .build();

        reservationRepository.save(reservation);
    }
}
