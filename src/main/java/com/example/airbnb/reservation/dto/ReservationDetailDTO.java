package com.example.airbnb.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Date;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReservationDetailDTO {
    private int id;
    private String houseName;
    private String address;
    private String guestName;
    private LocalDate startDate;
    private LocalDate endDate;
    private Integer totalPrice;
    private Integer maxPeople;
    private LocalDateTime updatedAt;
    private String hostName;
    private String hostPhone;
    private String progress;
    private int memberId;
    private int houseId;
}
