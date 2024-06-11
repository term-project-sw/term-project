<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style>
    body {
        margin: 0;
        padding: 0;
    }
    .header {
        display: flex;
        justify-content: space-between;
        align-items: center;
        padding: 10px;
        background-color: #f8f9fa;
        position: fixed;
        width: 100%;
        top: 0;
        left: 0;
        z-index: 1000; /* 헤더를 항상 최상단에 위치시킵니다. */
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1); /* 헤더에 약간의 그림자를 추가하여 구분을 명확히 합니다. */
    }
    .content {
        padding-top: 60px; /* 헤더 높이만큼 패딩을 추가하여 본문 내용이 헤더 아래로 시작하게 합니다. */
    }
</style>
<div class="header">
    <div>
        <button onclick="location.href='/host/mypage'">Main Page</button>
    </div>
    <div>
        <c:choose>
            <c:when test="${empty sessionScope.memberId}">
                <button onclick="location.href='/member/login'">Login</button>
                <button onclick="location.href='/member/register'">Sign Up</button>
            </c:when>
            <c:otherwise>
                <form action="/member/logout" method="post" style="display: inline;">
                    <button type="submit">Logout</button>
                </form>
                <c:if test="${sessionScope.role == 'HOST'}">
                    <button onclick="location.href='/host/mypage'">Mypage</button>
                </c:if>
                <c:if test="${sessionScope.role == 'GUEST'}">
                    <button onclick="location.href='/guest/mypage'">Mypage</button>
                </c:if>
            </c:otherwise>
        </c:choose>
    </div>
</div>
