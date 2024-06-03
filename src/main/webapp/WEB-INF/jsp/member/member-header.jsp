<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<div style="display: flex; justify-content: space-between; align-items: center; padding: 10px; background-color: #f8f9fa;">
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
            </c:otherwise>
        </c:choose>
    </div>
</div>
