package com.example.airbnb.house.dto;

import com.example.airbnb.house.domain.RoomType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class RoomCreateRequest {

    private Integer furniutureCount;
    private RoomType roomType;
}
