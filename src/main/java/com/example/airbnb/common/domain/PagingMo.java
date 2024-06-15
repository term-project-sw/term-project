package com.example.airbnb.common.domain;

import org.springframework.stereotype.Component;

@Component
public class PagingMo {

	/**
	 * 페이징 모듈 메소드
	 * 
	 * @param totalCnt     : 전체 개수
	 * @param num          : 현재 페이지 번호
	 * @param itemsPerPage : 출력 개수
	 * @param jsFunction   : 클릭시 이동할 자바스크립트 함수명
	 * @return
	 */
	public String getPagingHTML(int totalCnt, int num, int itemsPerPage, String jsFunction) {

		StringBuffer sb = new StringBuffer(); // 페이징에 출력할 html을 담자

		try {

			// [1] 필요한 변수 설정
			int startPage = 0;
			int endPage = 0;
			int totalPage = 0;
			int beforePage = 0;
			int nextPage = 0;

			// [2] 제약 조건
			// [2-1] 가로 출력 숫자의 시작과 종료 지점을 설정
			if (num % 10 == 0) {
				startPage = ((num / 10) - 1) * 10 + 1;
				endPage = (num / 10) * 10;
			} else {
				startPage = (num / 10) * 10 + 1;
				endPage = ((num / 10) + 1) * 10;
			}

			// [2-2] 전체 개수 대비 출력할 row의 개수를 고려하여 제약 사항
			if (totalCnt % itemsPerPage == 0) {
				totalPage = (int) (totalCnt / itemsPerPage);
			} else {
				totalPage = (int) (totalCnt / itemsPerPage + 1);
			}
			if (totalPage == 0) {
				totalPage = 1;
			}
			if (totalPage < endPage) {
				endPage = totalPage;
			}

			// [2-3] 이전, 다음 페이지 번호 셋팅
			if (num <= 1) {
				beforePage = num;
			} else {
				beforePage = num - 1;
			}

			if (num == totalPage) {
				nextPage = totalPage;
			} else {
				nextPage = num + 1;
			}

			// 임시 출력
			System.out.println("startPage = " + startPage);
			System.out.println("endPage = " + endPage);
			System.out.println("totalPage = " + totalPage);
			System.out.println("beforePage = " + beforePage);
			System.out.println("nextPage = " + nextPage);

			// [3] 페이지 순서 출력
			sb.append("<div class='pagination'>");

			// [3-1] 이전 보기 출력
			if (beforePage < num) {
				sb.append("<a href='javascript:pagingJS(" + beforePage + ")' class='page-link'>Prev</a>");
			}

			// [3-2] 본 페이지 출력
			for (int i = startPage; i < endPage + 1; i++) {
				sb.append("<a href='javascript:" + jsFunction + "(" + i + ")' class='page-link'>");

				if (i == num) {
					sb.append("<b>");
					sb.append(i);
					sb.append("</b>");
				} else {
					sb.append(i);
				}
				sb.append("</a>");
			}

			// [3-3] 다음 페이지 출력
			if (nextPage > num) {
				sb.append("<a href='javascript:pagingJS(" + nextPage + ")' class='page-link'>Next</a>");
			}
			sb.append("</div>");

		} catch (Exception ex) {
			ex.printStackTrace();
		}

		return sb.toString();

	}

}
