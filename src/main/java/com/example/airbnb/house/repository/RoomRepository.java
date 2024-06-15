package com.example.airbnb.house.repository;


import com.example.airbnb.house.domain.House;
import com.example.airbnb.house.domain.Room;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoomRepository extends JpaRepository<Room, Long> {

    List<Room> findByHouse(House house);

}
