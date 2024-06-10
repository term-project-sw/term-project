package com.example.airbnb.house.repository;

import com.example.airbnb.house.domain.House;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HouseRepository extends JpaRepository<House, Long> {

    Page<House> findAll(Pageable pageable);

    Page<House> findByNameContainingIgnoreCase(String name, Pageable pageable);

}
