package com.example.airbnb.house.dto;

import com.example.airbnb.house.domain.House;
import com.example.airbnb.house.domain.Image;
import com.example.airbnb.house.domain.Room;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Getter
public class HouseDetailResponse {

    private House house;
    private List<Room> rooms;
    private List<Image> images;

    public static HouseDetailResponse of(House house, List<Room> rooms, List<Image> images) {
        return new HouseDetailResponse(house, rooms, images);
    }
}
