package com.example.airbnb.house.repository;

import com.example.airbnb.house.domain.Room;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {
}
