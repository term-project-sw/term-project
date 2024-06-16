package com.example.airbnb.reservation.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class ReservationListDTO {
    private int id;
    private String address;
    private String house;
    private LocalDateTime createdAt;
}
