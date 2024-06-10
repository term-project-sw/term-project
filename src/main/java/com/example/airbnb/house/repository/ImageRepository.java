package com.example.airbnb.house.repository;

import com.example.airbnb.house.domain.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Long> {
}
