package com.example.airbnb.house.domain;

import com.example.airbnb.common.domain.BaseEntity;
import com.example.airbnb.member.domain.Member;
import jakarta.persistence.Entity;
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
public class House extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer maxPeople;

    private String address;

    private String introduce;

    private String description;

    private Integer pricePerPerson;

    @ManyToOne
    @JoinColumn
    private Member member;



    public House(final String name, final Integer maxPeople, final String address,
                 final String introduce, final String description,
                 final Integer pricePerPerson, final Member member) {
        this.name = name;
        this.maxPeople = maxPeople;
        this.address = address;
        this.introduce = introduce;
        this.description = description;
        this.pricePerPerson = pricePerPerson;
        this.member = member;
    }
}
