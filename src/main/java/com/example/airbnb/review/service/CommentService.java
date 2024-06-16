package com.example.airbnb.review.service;

import com.example.airbnb.member.domain.Member;
import com.example.airbnb.member.repository.MemberRepository;
import com.example.airbnb.review.domain.Comment;
import com.example.airbnb.review.domain.Review;
import com.example.airbnb.review.repository.CommentRepository;
import com.example.airbnb.review.repository.ReviewRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class CommentService {

    private final ReviewRepository reviewRepository;
    private final CommentRepository commentRepository;
    private final MemberRepository memberRepository;

    @Transactional
    public void saveComment(final String content, final Long reviewId, final Long memberId) {
        Review review = reviewRepository.findById(reviewId)
                                        .orElseThrow(() -> new IllegalArgumentException("id에 해당하는 review가 없습니다."));
        Member member = memberRepository.findById(memberId)
                                        .orElseThrow(() -> new IllegalArgumentException("id에 해당하는 member가 없습니다."));
        Comment comment = Comment.builder()
                                 .content(content)
                                 .member(member)
                                 .review(review)
                                 .build();

        commentRepository.save(comment);
    }
}
