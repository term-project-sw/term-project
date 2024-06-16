<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/member/member-header.jsp" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Guest 마이페이지</title>
    <style>
        /* 왼쪽 버튼 그룹 */
        .vertical-buttons {
            position: fixed;
            left: 20px; /* 왼쪽 여백 조절 */
            top: 50%; /* 화면 상단으로부터의 위치 조절 */
            transform: translateY(-50%);
            display: flex;
            flex-direction: column;
            gap: 10px; /* 버튼 사이의 간격 조절 */
        }

        /* 오른쪽 텍스트 */
        .host-page {
            position: fixed;
            left: calc(20px + 120px * 4 + 10px * 3); /* 왼쪽 여백 + 버튼 너비와 간격의 합 */
            top: 50%; /* 화면 상단으로부터의 위치 조절 */
            transform: translateY(-50%);
        }
    </style>
</head>
<body>
<div class="vertical-buttons">
    <button onclick="location.href='/members/guest/mypage'">마이 페이지</button>
    <button onclick="location.href='/members/myinfo-edit/${member.id}'">회원 정보 수정</button>
    <button onclick="location.href='/member/${member.id}/reservations'">예약 내역</button>
    <button onclick="location.href='/guest/${member.id}/reviews'">마이 리뷰</button>
</div>

<div class="host-page">
    <h1>GuestPage 게스트페이지</h1>
    <c:if test="${not empty member}">
        <p>Member Name: ${member.name}</p>
    </c:if>
</div>

</body>
</html>
