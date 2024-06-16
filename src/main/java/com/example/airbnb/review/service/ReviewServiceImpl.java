package com.example.airbnb.review.service;

import com.example.airbnb.common.domain.PagingMo;
import com.example.airbnb.review.domain.Review;
import com.example.airbnb.review.dto.HouseReviewDetailDTO;
import com.example.airbnb.review.dto.ReviewDetailDTO;
import com.example.airbnb.review.mapper.ReviewMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class ReviewServiceImpl implements ReviewServiceIF{
    // log4j 객체
    private static final Logger log = (Logger) LogManager.getLogger(ReviewServiceImpl.class);

    // 회원 Mapper DI 객체
    private ReviewMapper reviewMapper;

    // 페이징 DI 객체
    private PagingMo pagingMo;

    /**
     * MemberServiceImpl 생성자
     *
     * @author 승기
     * @param reviewMapper
     */
    public ReviewServiceImpl(ReviewMapper reviewMapper, PagingMo pagingMo) {
        this.reviewMapper = reviewMapper;
        this.pagingMo = pagingMo;
    }

    /**
     * 마이 리뷰 정보 조회 서비스 메소드
     *
     * @author 승기
     * @since 2024.04.01
     */
    @Override
    public Map<String, Object> getReviewListService(Map<String, Object> allParams) {

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
        int totalCnt = reviewMapper.getReviewListCountSQL(allParams);
        log.info("[4] 전체 조회 개수 :" + totalCnt);

        // [5] 실제 데이터 조회
        List<Review> reviewList = reviewMapper.getReviewListSQL(allParams);
        log.info("[5] 실제 데이터 개수 :" + reviewList.size());

        // [6] Paging 모듈 호출
        String pagingHtml = pagingMo.getPagingHTML(totalCnt, (int) allParams.get("num"),
                (int) allParams.get("itemsPerPage"), "pagingJS");
        log.info("[6] pagingHTML = " + pagingHtml);

        // [7] Controller로 넘겨줄 파라미터 셋팅
        result.put("reviewList", reviewList);
        result.put("pagingHtml", pagingHtml);

        return result;
    }


    //마이 리뷰 상세 정보 서비스
    @Override
    public ReviewDetailDTO getReviewDetailService(Map<String, Object> allParams) {
        ReviewDetailDTO reviewDetail = null;

        try {
            log.info("(1) 예약 시퀀스 정보 : "+ allParams.get("reviewId"));
            // DB에서 id를 가지고 1개의 예약정보를 가져오자
            reviewDetail = reviewMapper.getReviewDetailSQL(allParams);

            log.info("(2) 예약 정보 db 조회결과  : " + reviewDetail.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return reviewDetail;
    }


    //마이 리뷰 정보 수정 서비스
    @Override
    public Map<String, Object> modifyReviewService(Map<String, String> allParams){
        Map<String, Object> result = new HashMap<String, Object>();
        result.put("REOL_CD", "000000");
        result.put("REOL_MSG", "SUCCESS");

        try {
            reviewMapper.updateReviewDetailSQL(allParams);

        } catch(Exception ex) {
            ex.printStackTrace();
        }

        return null;
    }


    //마이 리뷰 정보 삭제 서비
    @Override
    public Map<String, Object> removeReviewService(Map<String, String> allParams){

        Map<String, Object> result = new HashMap<String, Object>();
        result.put("REOL_CD", "000000");
        result.put("REOL_MSG", "SUCCESS");

        try {
            reviewMapper.removeReviewDetailSQL(allParams);

        } catch(Exception ex) {
            ex.printStackTrace();
        }

        return null;

    }



    /**
     * 해당 숙소 리뷰 전체 조회 서비스 메소드
     *
     * @author 승기
     * @since 2024.04.01
     */
    @Override
    public Map<String, Object> getHouseReviewListService(Map<String, Object> allParams) {

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
        int totalCnt = reviewMapper.getHouseReviewListCountSQL(allParams);
        log.info("[4] 전체 조회 개수 :" + totalCnt);

        // [5] 실제 데이터 조회
        List<Review> houseReviewList = reviewMapper.getHouseReviewListSQL(allParams);
        log.info("[5] 실제 데이터 개수 :" + houseReviewList.size());

        // [6] Paging 모듈 호출
        String pagingHtml = pagingMo.getPagingHTML(totalCnt, (int) allParams.get("num"),
                (int) allParams.get("itemsPerPage"), "pagingJS");
        log.info("[6] pagingHTML = " + pagingHtml);

        // [7] Controller로 넘겨줄 파라미터 셋팅
        result.put("houseReviewList", houseReviewList);
        result.put("pagingHtml", pagingHtml);

        return result;
    }


    //숙소 리뷰 상세 정보 서비스
    @Override
    public HouseReviewDetailDTO getHouseReviewDetailService(Map<String, Object> allParams) {
        HouseReviewDetailDTO reviewDetail = null;

        try {
            log.info("(1) 예약 시퀀스 정보 : "+ allParams.get("reviewId"));
            // DB에서 id를 가지고 1개의 예약정보를 가져오자
            reviewDetail = reviewMapper.getHouseReviewDetailSQL(allParams);

            log.info("(2) 예약 정보 db 조회결과  : " + reviewDetail.toString());
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return reviewDetail;
    }
}
