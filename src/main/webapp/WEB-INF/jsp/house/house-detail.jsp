<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page import="java.time.LocalDate" %>
<%
    LocalDate now = LocalDate.now();
    int currentYear = now.getYear();
    int currentMonth = now.getMonthValue();
    pageContext.setAttribute("currentYear", currentYear);
    pageContext.setAttribute("currentMonth", currentMonth);
%>
<!DOCTYPE html>
<html>
<head>
    <title>House Details</title>
    <style>
        body, html {
            font-family: Arial, sans-serif;
            margin: 0;
            padding: 0;
            background-color: #f4f4f4;
            height: 100%;
            overflow: auto;
        }
        .container {
            background-color: white;
            padding: 20px;
            border-radius: 10px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
            width: 80%;
            max-width: 800px;
            margin: 20px auto;
        }
        h2 {
            text-align: center;
            color: #4CAF50;
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
            word-break: break-word;
        }
        th {
            background-color: #f2f2f2;
        }
        ul {
            list-style-type: none;
            padding: 0;
        }
        ul li {
            padding: 8px;
            background-color: #f9f9f9;
            border: 1px solid #ddd;
            border-radius: 5px;
            margin-bottom: 10px;
        }
        img {
            display: block;
            margin: 10px auto;
            border-radius: 10px;
        }
        a {
            display: inline-block;
            padding: 10px 20px;
            color: white;
            background-color: #4CAF50;
            border-radius: 5px;
            text-decoration: none;
            text-align: center;
            margin-top: 20px;
        }
        a:hover {
            background-color: #45a049;
        }
    </style>
</head>
<body>
<div class="container">
    <h2>House Details</h2>

    <table>
        <tr>
            <th>Title:</th>
            <td>${house.name}</td>
        </tr>
        <tr>
            <th>Max People:</th>
            <td>${house.maxPeople}</td>
        </tr>
        <tr>
            <th>Address:</th>
            <td>${house.address}</td>
        </tr>
        <tr>
            <th>Introduce:</th>
            <td>${house.introduce}</td>
        </tr>
        <tr>
            <th>Description:</th>
            <td>${house.description}</td>
        </tr>
        <tr>
            <th>Price per Day:</th>
            <td>${house.pricePerDay}</td>
        </tr>
        <tr>
            <th>Host Name:</th>
            <td>${house.member.name}</td>
        </tr>
    </table>

    <h2>Rooms</h2>
    <ul>
        <c:forEach var="room" items="${rooms}">
            <li>${room.type} - Furniture Count: ${room.furniutureCount}</li>
        </c:forEach>
    </ul>

    <h2>Images</h2>
    <c:if test="${not empty images}">
        <c:forEach var="image" items="${images}">
            <img src="data:image/png;base64,${image.data}" alt="Image" style="width:300px; height:auto;"/>
        </c:forEach>
    </c:if>

    <a href="/house/houses">Back to House List</a>
    <a href="/house/calendar?houseId=${house.id}&year=${currentYear}&month=${currentMonth}">숙소예약하기 </a>
    <a href="/house/${house.id}/reviews"> 리뷰 보러가기 </a>
</div>
</body>
</html>