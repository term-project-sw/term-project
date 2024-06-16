package com.example.airbnb.house.repository;


import com.example.airbnb.house.domain.House;
import com.example.airbnb.house.domain.Image;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {

    List<Image> findByHouse(House house);
}
