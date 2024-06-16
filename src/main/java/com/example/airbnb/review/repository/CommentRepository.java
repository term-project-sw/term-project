package com.example.airbnb.review.repository;

import com.example.airbnb.review.domain.Comment;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
