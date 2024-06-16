<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
    <title>Main Page</title>
    <style>
        body, html {
            margin: 0;
            padding: 0;
            height: 100%;
            overflow: auto;
            font-family: Arial, sans-serif;
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
            flex-direction: column;
            align-items: center;
            padding-top: 60px; /* 헤더 높이만큼 패딩을 추가합니다 */
            min-height: 100vh; /* 전체 화면 높이 */
            background-color: #f4f4f4;
        }
        .content {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 80%;
            max-width: 800px;
            margin-bottom: 20px;
        }
        .content h2 {
            text-align: center;
            color: #4CAF50;
        }
        form {
            display: flex;
            justify-content: center;
            margin-bottom: 20px;
        }
        form input[type="text"] {
            width: 200px;
            padding: 8px;
            border: 1px solid #ddd;
            border-radius: 5px;
            margin-right: 10px;
        }
        form button {
            padding: 8px 16px;
            border: none;
            background-color: #4CAF50;
            color: white;
            border-radius: 5px;
            cursor: pointer;
        }
        form button:hover {
            background-color: #45a049;
        }
        table {
            width: 100%;
            border-collapse: collapse;
            margin-bottom: 20px;
        }
        th, td {
            padding: 12px;
            border: 1px solid #ddd;
            text-align: left;
        }
        th {
            background-color: #f2f2f2;
        }
        .pagination {
            display: flex;
            justify-content: center;
            list-style: none;
            padding: 0;
        }
        .pagination li {
            margin: 0 5px;
        }
        .pagination a {
            text-decoration: none;
            color: black;
            padding: 8px 16px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .pagination a:hover {
            background-color: #ddd;
        }
        .active a {
            background-color: #4CAF50;
            color: white;
        }
    </style>
</head>
<body>
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

<div class="main-container">
    <div class="content">
        <h2>House List</h2>

        <form method="get" action="/house/houses">
            <input type="text" name="search" placeholder="Search by title" value="${search}">
            <button type="submit">Search</button>
        </form>

        <table>
            <thead>
            <tr>
                <th>Title</th>
                <th>Host Name</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach items="${housePage.content}" var="house">
                <tr>
                    <td><a href="/house/houses/${house.id}">${house.name}</a></td>
                    <td>${house.member.name}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>

        <div>
            <ul class="pagination">
                <c:if test="${housePage.hasPrevious()}">
                    <li><a href="?page=${housePage.number - 1}&size=${housePage.size}&search=${search}">Previous</a></li>
                </c:if>
                <c:forEach var="I" begin="0" end="${housePage.totalPages > 0 ? housePage.totalPages - 1 : 0}">
                    <li class="${I == housePage.number ? 'active' : ''}">
                        <a href="?page=${I}&size=${housePage.size}&search=${search}">${I + 1}</a>
                    </li>
                </c:forEach>
                <c:if test="${housePage.hasNext()}">
                    <li><a href="?page=${housePage.number + 1}&size=${housePage.size}&search=${search}">Next</a></li>
                </c:if>
            </ul>
        </div>
    </div>
</div>
</body>
</html>