package com.example.airbnb.review.repository;

import com.example.airbnb.house.domain.House;
import com.example.airbnb.review.domain.Review;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepository extends JpaRepository<Review, Long> {


    List<Review> findByHouse(House house);

}
