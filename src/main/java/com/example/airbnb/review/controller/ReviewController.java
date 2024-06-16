package com.example.airbnb.review.controller;

import com.example.airbnb.reservation.controller.ReservaitonController;
import com.example.airbnb.reservation.dto.ReservationDetailDTO;
import com.example.airbnb.review.dto.HouseReviewDetailDTO;
import com.example.airbnb.review.dto.ReviewDetailDTO;
import com.example.airbnb.review.service.ReviewServiceIF;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ReviewController {
    // log4j 객체
    private static final Logger log = (Logger) LogManager.getLogger(ReservaitonController.class);

    // 예약 서비스 DI 객체
    private ReviewServiceIF service;

    /**
     * ReviewController 생성자
     *
     * @author 승기
     * @param service
     */
    public ReviewController(ReviewServiceIF service) {
        this.service = service;
    }

    /**
     * ㅇ
     * 마이 리뷰 정보 리스트 조회 Controller
     *
     * @author 승기
     * @return
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
     * @author 승기
     * @return
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
     * ㅇ
     * 숙소 리뷰 정보 리스트 조회 Controller
     *
     * @author 승기
     * @return
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
     * @author 승기
     * @return
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
}
