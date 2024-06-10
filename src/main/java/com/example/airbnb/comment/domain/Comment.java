package com.example.airbnb.comment.domain;

import com.example.airbnb.common.domain.BaseEntity;
import com.example.airbnb.member.domain.Member;
import com.example.airbnb.review.domain.Review;
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
public class Comment extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String content;

    @ManyToOne
    private Review review;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;
}
