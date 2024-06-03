package com.example.airbnb.house.repository;

import com.example.airbnb.house.domain.House;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Long> {
}
