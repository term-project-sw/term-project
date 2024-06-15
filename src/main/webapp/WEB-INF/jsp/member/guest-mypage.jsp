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
    <button onclick="location.href='/path/to/first-page'">First Button</button>
    <button onclick="location.href='/path/to/second-page'">Second Button</button>
    <button onclick="location.href='/path/to/third-page'">Third Button</button>
    <button onclick="location.href='/path/to/fourth-page'">Fourth Button</button>
</div>

<div class="host-page">
    <h1>GuestPage 게스트페이지</h1>
    <c:if test="${not empty sessionScope.memberId}">
        <p>MemberId: ${sessionScope.memberId}</p>
    </c:if>
</div>

</body>
</html>
