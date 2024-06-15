package com.example.airbnb.review.controller;

import com.example.airbnb.review.service.CommentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public void saveComment(
            @RequestParam String content,
            @RequestParam Long reviewId,
            HttpSession session
    ) {
        Long memberId = (Long) session.getAttribute("memberId");
        commentService.saveComment(content, reviewId, memberId);
    }
}
