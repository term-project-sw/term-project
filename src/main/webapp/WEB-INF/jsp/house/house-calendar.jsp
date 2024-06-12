<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page import="java.util.List" %>
<%@ page import="com.fasterxml.jackson.databind.ObjectMapper" %>

<%
    // JSON으로 변환
    ObjectMapper objectMapper = new ObjectMapper();
    String jsonDates = objectMapper.writeValueAsString((List<String>) request.getAttribute("reservedDates"));
    int year = (Integer) request.getAttribute("year");
    int month = (Integer) request.getAttribute("month");
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>예약된 날짜 하이라이트 달력</title>
    <style>
        .calendar {
            display: grid;
            grid-template-columns: repeat(7, 1fr);
            gap: 5px;
            margin: 20px;
        }
        .day {
            padding: 10px;
            text-align: center;
            border: 1px solid #ddd;
        }
        .highlight {
            background-color: yellow;
        }
    </style>
</head>
<body>
<h1>예약가능한 날짜 하이라이트 달력</h1>
<div id="calendar" class="calendar"></div>

<script>
    const highlightedDates = <%= jsonDates %>;

    function generateCalendar(year, month) {
        const calendar = document.getElementById('calendar');
        calendar.innerHTML = '';

        const startDate = new Date(year, month - 1, 1);
        const endDate = new Date(year, month, 0);
        const startDay = startDate.getDay();
        const totalDays = endDate.getDate();

        // 빈 칸 채우기
        for (let i = 0; i < startDay; i++) {
            const emptyCell = document.createElement('div');
            emptyCell.classList.add('day');
            calendar.appendChild(emptyCell);
        }

        // 날짜 채우기
        for (let day = 1; day <= totalDays; day++) {
            const date = new Date(year, month - 1, day);
            const dateString = date.toISOString().split('T')[0];
            const dayCell = document.createElement('div');
            dayCell.classList.add('day');
            dayCell.textContent = day;
            if (highlightedDates.includes(dateString)) {
                dayCell.classList.add('highlight');
            }
            calendar.appendChild(dayCell);
        }
    }

    // 전달받은 연도와 월을 사용하여 달력 생성
    generateCalendar(<%= year %>, <%= month %>);
</script>
</body>
</html>