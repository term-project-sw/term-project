package com.example.airbnb.reservation.service;

import com.example.airbnb.common.domain.PagingMo;
import com.example.airbnb.member.mapper.MemberMapper;
import com.example.airbnb.reservation.domain.Reservation;
import com.example.airbnb.reservation.dto.ReservationDetailDTO;
import com.example.airbnb.reservation.mapper.ReservationMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReservationServiceImpl implements ReservationServiceIF{
    // log4j 객체
    private static final Logger log = (Logger) LogManager.getLogger(ReservationServiceImpl.class);

    // 회원 Mapper DI 객체
    private ReservationMapper reservationMapper;

    // 페이징 DI 객체
    private PagingMo pagingMo;

    /**
     * ReservationServiceImpl 생성자
     *
     * @author 승기
     * @param reservationMapper
     */
    public ReservationServiceImpl(ReservationMapper reservationMapper, PagingMo pagingMo) {
        this.reservationMapper = reservationMapper;
        this.pagingMo = pagingMo;
    }

    /**

     * 예약 정보 조회 서비스 메소드
     *
     * @author 승기
     * @since 2024.05.30
     */
    @Override
    public Map<String, Object> getReservationListService(Map<String, Object> allParams) {

        Map<String, Object> result = new HashMap<String, Object>();

        // [1] 초기 출력 개수 값(5로 설정)
        if (allParams.get("itemsPerPage") == null) {
            allParams.put("itemsPerPage", 5);
        } else {
            allParams.put("itemsPerPage", Integer.parseInt(allParams.get("itemsPerPage").toString()));
        }
        log.info("[1] 초기 출력 개수 :" + allParams.get("itemsPerPage"));

        // [2] 초기 페이지 번호가 없는 경우 1페이지 설정
        if (allParams.get("num") == null) {
            allParams.put("num", 1);
        } else {
            allParams.put("num", Integer.parseInt(allParams.get("num").toString()));
        }
        log.info("[2] 초기 페이지 번호 :" + allParams.get("num"));

        // [3] 페이징 시작번호 설정 (SQL에서 사용함)
        int pageStartNum = ((int) allParams.get("num") - 1) * (int) allParams.get("itemsPerPage");
        allParams.put("pageStartNum", pageStartNum);
        log.info("[3] 페이징 시작번호 설정 :" + pageStartNum);

        // [4] 페이지 출력 대상 전체 개수 구하기
        int totalCnt = reservationMapper.getReservationListCountSQL(allParams);
        log.info("[4] 전체 조회 개수 :" + totalCnt);

        // [5] 실제 데이터 조회
        List<Reservation> reservationList = reservationMapper.getReservationListSQL(allParams);
        log.info("[5] 실제 데이터 개수 :" + reservationList.size());

        // [6] Paging 모듈 호출
        String pagingHtml = pagingMo.getPagingHTML(totalCnt, (int) allParams.get("num"),
                (int) allParams.get("itemsPerPage"), "pagingJS");
        log.info("[6] pagingHTML = " + pagingHtml);

        // [7] Controller로 넘겨줄 파라미터 셋팅
        result.put("reservationList", reservationList);
        result.put("pagingHtml", pagingHtml);

        return result;
    }


    //예약 상세 정보 서비스
    @Override
    public ReservationDetailDTO getReservationDetailService(Map<String, Object> allParams) {
        ReservationDetailDTO reservationDetail = null;
        try {
            log.info("(1) 예약 시퀀스 정보 : "+ allParams.get("reservationId"));
            // DB에서 id를 가지고 1개의 예약정보를 가져오자
            reservationDetail = reservationMapper.getReservationDetailSQL(allParams);

            log.info("(2) 예약 정보 db 조회결과  : " + reservationDetail.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return reservationDetail;
    }




    // 예약 정보 수정 서비스

    @Override
    public Map<String, Object> modifyReservationService(Map<String, String> allParams){
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("REOL_CD", "000000");
        result.put("REOL_MSG", "SUCCESS");

        try {
            reservationMapper.updateReservationDetailSQL(allParams);

        } catch(Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }



    //예약 정보 삭제 서비스
    @Override
    public Map<String, Object> removeReservationService(Map<String, String> allParams){

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("REOL_CD", "000000");
        result.put("REOL_MSG", "SUCCESS");

        try {
            reservationMapper.removeReservationDetailSQL(allParams);

        } catch(Exception ex) {
            ex.printStackTrace();
        }

        return null;

    }
}
