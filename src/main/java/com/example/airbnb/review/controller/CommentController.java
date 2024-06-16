package com.example.airbnb.review.controller;

import com.example.airbnb.review.service.CommentService;
import jakarta.servlet.http.HttpSession;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequiredArgsConstructor
@RequestMapping("/comments")
public class CommentController {

    private final CommentService commentService;

    @PostMapping
    public ModelAndView saveComment(
            @RequestParam String content,
            @RequestParam Long reviewId,
            HttpSession session
    ) {
        Long memberId = (Long) session.getAttribute("memberId");
        commentService.saveComment(content, reviewId, memberId);
        final ModelAndView modelAndView = new ModelAndView();
        modelAndView.setViewName("redirect:/houses/reviews");
        return modelAndView;
    }

    @GetMapping("/new")
    public ModelAndView showCommentForm(@RequestParam Long reviewId) {
        ModelAndView mav = new ModelAndView("host/host-review-comment");
        mav.addObject("reviewId", reviewId);
        return mav;
    }
}
