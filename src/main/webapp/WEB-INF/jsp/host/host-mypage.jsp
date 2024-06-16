<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/member/member-header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
        /* 왼쪽 버튼 그룹 */
    .vertical-buttons {
        flex: 0 0 200px; /* 고정 너비 */
        display: flex;
        flex-direction: column;
        gap: 10px; /* 버튼 사이의 간격 조절 */
        padding: 20px;
        background-color: #f0f0f0; /* 배경색 추가 */
        height: calc(100vh - 60px); /* 화면 높이에서 헤더 높이를 뺀 값으로 높이 설정 */
        box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1); /* 왼쪽 버튼 그룹에 그림자 추가 */
    }
	.content {
        flex: 1; /* 남은 공간 차지 */
        display: flex;
        flex-direction: column;
        padding: 20px;
        overflow-y: auto; /* 세로 스크롤 가능 */
    }
    /* 오른쪽 텍스트 */
    .host-page {
        flex: 1; /* 남은 공간 차지 */
        padding: 20px;
        margin: 0 auto;
        width: 100%;
    }
	.reservation-table {
        width: 100%;
        border-collapse: collapse;
    }
     .reservation-table th, .reservation-table td {
        border: 1px solid #ddd;
        padding: 8px;
    }
    .reservation-table th {
        background-color: #f2f2f2;
        text-align: left;
    }    
</style>
</head>
<body>
<div class="main-container">
	<div class="vertical-buttons">
	    <button onclick="location.href='/members/myinfo-edit/${sessionScope.memberId}'">회원 정보 수정</button>
	    <button onclick="location.href='/house/register'">숙소 등록</button>
	    <button onclick="location.href='/host/host-house-reservation-list'">예약 현황</button>
	    <button onclick="location.href='/houses/reviews'">리뷰 전체 목록</button>
	</div>
	<div class="content">
		<div class="host-page">
		<c:if test="${not empty sessionScope.memberId}">
	    <h1>환영합니다! 사용자넘버: ${sessionScope.memberId}</h1>
	    </c:if>
	    <table class="reservation-table">	    
                <thead>
                    <tr>
                        <th>숙소명</th>
                        <th>주소</th>
                        <th>최대인원</th>
                        <th>가격(일당)</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="list" items="${houseList}">
                        <tr>
                            <td>${list.name}
                                <button onclick="location.href='/house/houses/'+${list.id}">숙소상세</button>
                            </td>
                            <td>${list.address}</td>
                            <td>${list.maxPeople}</td>
                            <td>${list.pricePerDay}</td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
		</div>
	</div>
	
</div>
</body>
</html>
