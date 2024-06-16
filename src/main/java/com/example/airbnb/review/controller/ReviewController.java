package com.example.airbnb.review.controller;

import com.example.airbnb.house.domain.House;
import com.example.airbnb.reservation.controller.ReservaitonController;
import com.example.airbnb.review.domain.Review;
import com.example.airbnb.review.dto.HouseReviewDetailDTO;
import com.example.airbnb.review.dto.ReviewDetailDTO;
import com.example.airbnb.review.service.CommentService;
import com.example.airbnb.review.service.ReviewServiceIF;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@Slf4j
public class ReviewController {
    // log4j 객체
    private static final Logger log = (Logger) LogManager.getLogger(ReservaitonController.class);

    // 예약 서비스 DI 객체
    private ReviewServiceIF service;
    private CommentService commentService;

    /**
     * ReviewController 생성자
     *
     * @param service
     * @author 승기
     */
    public ReviewController(final ReviewServiceIF service, final CommentService commentService) {
        this.service = service;
        this.commentService = commentService;
    }

    /**
     * ㅇ 마이 리뷰 정보 리스트 조회 Controller
     *
     * @return
     * @author 승기
     */
    @RequestMapping("/guest/{memberId}/reviews")
    public ModelAndView getReviewList(@RequestParam Map<String, Object> allParams, @PathVariable int memberId) {

        ModelAndView mav = new ModelAndView();

        allParams.put("memberId", memberId);

        log.info("Request parameters: {}", allParams);// 요청 파라미터 로그 출력

        Map<String, Object> result = service.getReviewListService(allParams);

        log.info("Service result: {}", result);  // 서비스 결과 로그 출력

        mav.addObject("allParams", allParams);
        mav.addObject("reviewList", result.get("reviewList"));
        mav.addObject("pagingHtml", result.get("pagingHtml"));

        mav.setViewName("member/guest-reviewList");

        return mav;

    }

    /**
     * 마이 리뷰 상세 정보 조회 Controller
     *
     * @return
     * @author 승기
     */
    @RequestMapping("/review/detail/{reviewId}")
    public ModelAndView getReviewDetail(@PathVariable int reviewId) {

        ModelAndView mav = new ModelAndView();

        Map<String, Object> allParams = new HashMap<>();
        allParams.put("reviewId", reviewId);

        log.info("Fetching review detail for reviewId: {}", reviewId);

        // 서비스에서 컨트롤러를 호출해서 1개의 리뷰 데이터를 받아와서 출력
        ReviewDetailDTO reviewDetail = service.getReviewDetailService(allParams);

        log.info("Service result: {}", reviewDetail.toString());

        mav.addObject("reviewDetail", reviewDetail);
        mav.setViewName("member/guest-reviewDetail");

        return mav;

    }


    /**
     * 마이 리뷰 수정 Ajax
     *
     * @param allParams
     * @return
     */
    @ResponseBody
    @PostMapping("/review/modify")
    public Map<String, Object> modify(@RequestParam Map<String, String> allParams) {

        Map<String, Object> result = new HashMap<>();

        result = service.modifyReviewService(allParams);

        return result;
    }


    /**
     * 마이 리뷰 삭제 Ajax
     *
     * @param allParams
     * @return
     */
    @ResponseBody
    @PostMapping("/review/remove")
    public Map<String, Object> remove(@RequestParam Map<String, String> allParams) {

        Map<String, Object> result = new HashMap<>();

        result = service.removeReviewService(allParams);

        return result;
    }


    /**
     * ㅇ 숙소 리뷰 정보 리스트 조회 Controller
     *
     * @return
     * @author 승기
     */
    @RequestMapping("/house/{houseId}/reviews")
    public ModelAndView getHouseReviewList(@RequestParam Map<String, Object> allParams, @PathVariable int houseId) {

        ModelAndView mav = new ModelAndView();

        allParams.put("houseId", houseId);

        log.info("Request parameters: {}", allParams);// 요청 파라미터 로그 출력

        Map<String, Object> result = service.getHouseReviewListService(allParams);

        log.info("Service result: {}", result);  // 서비스 결과 로그 출력

        mav.addObject("allParams", allParams);
        mav.addObject("houseReviewList", result.get("houseReviewList"));
        mav.addObject("pagingHtml", result.get("pagingHtml"));

        mav.setViewName("house/house-reviewList");

        return mav;

    }


    /**
     * 숙소 리뷰 상세 정보 조회 Controller
     *
     * @return
     * @author 승기
     */
    @RequestMapping("/house/review/detail/{reviewId}")
    public ModelAndView getHouseReviewDetail(@PathVariable int reviewId) {

        ModelAndView mav = new ModelAndView();

        Map<String, Object> allParams = new HashMap<>();
        allParams.put("reviewId", reviewId);

        log.info("Fetching review detail for reviewId: {}", reviewId);

        // 서비스에서 컨트롤러를 호출해서 1개의 리뷰 데이터를 받아와서 출력
        HouseReviewDetailDTO housereviewDetail = service.getHouseReviewDetailService(allParams);

        log.info("Service result: {}", housereviewDetail.toString());

        mav.addObject("housereviewDetail", housereviewDetail);
        mav.setViewName("house/house-reviewDetail");

        return mav;

    }


    /**
     * 리뷰등록 폼 출력 Controller
     *
     * @param houseId
     * @return
     */
    @GetMapping("/house/{houseId}/review/registerForm")
    public ModelAndView getReviewRegisterForm(@PathVariable int houseId) {
        ModelAndView mav = new ModelAndView();
        mav.setViewName("house/house-review-register");
        mav.addObject("houseId", houseId);
        return mav;
    }

    /**
     * 리뷰 등록 처리 Controller
     *
     * @param allParams
     * @param houseId
     * @return
     */
    @PostMapping("/house/{houseId}/review/register")
    @ResponseBody
    public Map<String, Object> registerReview(@RequestParam Map<String, String> allParams, @PathVariable int houseId,
                                              HttpSession session, HttpServletResponse response) throws IOException {
        Long memberId = (Long) session.getAttribute("memberId");
        if (memberId == null) {
            response.sendError(HttpServletResponse.SC_UNAUTHORIZED, "로그인 후 이용해주세요."); // 세션이 없을 경우 에러 응답
        }
        allParams.put("memberId", String.valueOf(memberId));
        allParams.put("houseId", String.valueOf(houseId));
        Map<String, Object> result = service.addReviewService(allParams);
        return result;
    }

    @GetMapping("/houses/reviews")
    public ModelAndView getHousesWithReviews(HttpSession session) {
        Long memberId = (Long) session.getAttribute("memberId");

        List<House> houses = commentService.findHousesByMember(memberId);
        Map<House, List<Review>> houseReviewsMap = new HashMap<>();

        for (House house : houses) {
            List<Review> reviews = commentService.findReviewByHouse(house);
            houseReviewsMap.put(house, reviews);
        }

        ModelAndView modelAndView = new ModelAndView("host/host-review-list");
        modelAndView.addObject("houseReviewsMap", houseReviewsMap);
        return modelAndView;
    }
}
