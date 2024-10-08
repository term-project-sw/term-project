package com.example.airbnb.house.domain;

import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Room {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Integer furniutureCount; //침대 개수 or 욕조 개수

    @Enumerated(EnumType.STRING)
    private RoomType type;

    @ManyToOne
    @JoinColumn
    private House house;

    public Room(final Integer furniutureCount, final RoomType type, final House house) {
        this.furniutureCount = furniutureCount;
        this.type = type;
        this.house = house;
    }
}
