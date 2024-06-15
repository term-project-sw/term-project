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
        z-index: 1000;
        box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
    }
    .main-container {
        display: flex;
        padding-top: 60px; /* 헤더 높이만큼 패딩을 추가합니다 */
        height: 100vh; /* 전체 화면 높이 */
    }
</style>
<div class="header">
    <div>
        <button onclick="location.href='/main'">Main Page</button>
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
