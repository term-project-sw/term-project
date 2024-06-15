package com.example.airbnb.reservation.controller;

import com.example.airbnb.reservation.dto.ReservationDetailDTO;

import com.example.airbnb.reservation.service.ReservationService;
import com.example.airbnb.reservation.service.ReservationServiceIF;
import jakarta.servlet.http.HttpSession;
import java.time.LocalDate;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.HashMap;
import java.util.Map;

@Controller
public class ReservaitonController {
    // log4j 객체
    private static final Logger log = (Logger) LogManager.getLogger(ReservaitonController.class);

    // 예약 서비스 DI 객체
    private ReservationServiceIF service;

    private final ReservationService reservationService;


    /**
     * ReservaitonController 생성자
     *
     * @author 승기
     * @param service
     */

    public ReservaitonController(final ReservationServiceIF service, final ReservationService reservationService) {
        this.service = service;
        this.reservationService = reservationService;
    }

    /**
     * ㅇ
     * 예약 정보 리스트 조회 Controller
     *
     * @author 승기
     * @return
     */
    @RequestMapping("/member/{memberId}/reservations")
    public ModelAndView getReservationList(@RequestParam Map<String, Object> allParams, @PathVariable int memberId) {

        ModelAndView mav = new ModelAndView();

        allParams.put("memberId", memberId);

        log.info("Request parameters: {}", allParams);  // 요청 파라미터 로그 출력
        Map<String, Object> result = service.getReservationListService(allParams);

        log.info("Service result: {}", result);  // 서비스 결과 로그 출력

        mav.addObject("allParams", allParams);
        mav.addObject("reservationList", result.get("reservationList"));
        mav.addObject("pagingHtml", result.get("pagingHtml"));

        mav.setViewName("member/guest-reservationList");

        return mav;

    }

    /**
     * 예약 상세 정보 조회 Controller
     *
     * @author 승기
     * @return
     */
    @RequestMapping("/reservation/detail/{reservationId}")
    public ModelAndView getReservationDetail(@PathVariable int reservationId) {

        ModelAndView mav = new ModelAndView();

        Map<String, Object> allParams = new HashMap<>();
        allParams.put("reservationId", reservationId);

        log.info("Fetching reservation detail for reservationId: {}", reservationId);

        // 서비스에서 컨트롤러를 호출해서 1개의 예약 데이터를 받아와서 출력
        ReservationDetailDTO reservationDetail = service.getReservationDetailService(allParams);

        log.info("Service result: {}", reservationDetail.toString());

        mav.addObject("reservationDetail", reservationDetail);
        mav.setViewName("member/guest-reservationDetail");

        return mav;

    }



    /**
     * 예약 수정 Ajax
     *
     * @param allParams
     * @return
     */
    @ResponseBody
    @PostMapping("/reservation/modify")
    public Map<String, Object> modify(@RequestParam Map<String, String> allParams) {

        Map<String, Object> result = new HashMap<>();

        result = service.modifyReservationService(allParams);

        return result;
    }



    /**
     * 예약 삭제 Ajax
     *
     * @param allParams
     * @return
     */
    @ResponseBody
    @PostMapping("/reservation/remove")
    public Map<String, Object> remove(@RequestParam Map<String, String> allParams) {

        Map<String, Object> result = new HashMap<>();

        result = service.removeReservationService(allParams);

        return result;
    }

    @PostMapping("/reserve")
    public ModelAndView makeReservation(@RequestParam("houseId") Long houseId,
                                        @RequestParam("startRegisterDate") LocalDate startRegisterDate,
                                        @RequestParam("endRegisterDate") LocalDate endRegisterDate,
                                        HttpSession session) {
        Long memberId = (Long) session.getAttribute("memberId");
        reservationService.makeReservation(houseId, memberId, startRegisterDate, endRegisterDate);

        ModelAndView modelAndView = new ModelAndView("redirect:/calendar");
        modelAndView.addObject("houseId", houseId);
        modelAndView.addObject("year", startRegisterDate.getYear());
        modelAndView.addObject("month", startRegisterDate.getMonthValue());
        return modelAndView;
    }

}
