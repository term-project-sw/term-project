package com.example.airbnb.house.repository;

import com.example.airbnb.house.domain.House;
import com.example.airbnb.reservation.domain.Reservation;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.MonthDay;
import java.util.Date;
import java.util.List;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

public interface HouseRepository extends JpaRepository<House, Long> {

    Page<House> findAll(Pageable pageable);

    Page<House> findByNameContainingIgnoreCase(String name, Pageable pageable);

    @Query("""
            SELECT r FROM Reservation r WHERE r.house.id = :houseId 
            AND (
            (YEAR(r.startRegisterDate) = :year AND MONTH(r.startRegisterDate) = :month) 
            OR (YEAR(r.endRegisterDate) = :year AND MONTH(r.endRegisterDate) = :month) 
            OR (YEAR(r.startRegisterDate) = :year AND MONTH(r.startRegisterDate) <= :month AND YEAR(r.endRegisterDate) = :year AND MONTH(r.endRegisterDate) >= :month)) 
            AND r.progress IN ('COMPLETE', 'IN_PROGRESS')
            """)
    List<Reservation> findReservationsByHouseIdAndMonth(@Param("houseId") Long houseId, @Param("year") int year, @Param("month") int month);
}
