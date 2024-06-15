package com.example.airbnb.house.domain;

import com.example.airbnb.common.domain.BaseEntity;
import com.example.airbnb.house.domain.House;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
@Entity
public class Image extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(columnDefinition = "LONGTEXT")
    private String data;

    @ManyToOne
    @JoinColumn
    private House house;

    public Image(final String data, final House house) {
        this.data = data;
        this.house = house;
    }
}
