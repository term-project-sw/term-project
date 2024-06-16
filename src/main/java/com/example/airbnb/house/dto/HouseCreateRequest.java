package com.example.airbnb.house.dto;

import java.util.ArrayList;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class HouseCreateRequest {

    private String name;
    private int maxPeople;
    private String address;
    private String introduce;
    private String description;
    private int pricePerDay;
    private List<MultipartFile> images = new ArrayList<>();
}
