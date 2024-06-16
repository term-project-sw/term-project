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
    Long houseId = Long.parseLong(request.getParameter("houseId"));
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>예약 날짜 선택 및 하이라이트 달력</title>
    <style>
        .calendar-container {
            text-align: center;
        }
        .calendar {
            display: grid;
            grid-template-columns: repeat(7, 1fr);
            gap: 5px;
            margin: 20px auto;
            max-width: 400px;
        }
        .day {
            padding: 10px;
            text-align: center;
            border: 1px solid #ddd;
        }
        .highlight {
            background-color: yellow;
        }
        .form-container {
            max-width: 400px;
            margin: 20px auto;
            padding: 20px;
            border: 1px solid #ddd;
            border-radius: 10px;
        }
        .form-group {
            margin-bottom: 15px;
        }
        .form-group label {
            display: block;
            margin-bottom: 5px;
        }
        .form-group input {
            width: 100%;
            padding: 8px;
            box-sizing: border-box;
        }
        .btn {
            display: block;
            width: 100%;
            padding: 10px;
            background-color: #007BFF;
            color: white;
            border: none;
            border-radius: 5px;
            cursor: pointer;
        }
        .btn:hover {
            background-color: #0056b3;
        }
        .nav-btn {
            cursor: pointer;
            padding: 10px;
            margin: 5px;
            border: 1px solid #ddd;
            border-radius: 5px;
            background-color: #f0f0f0;
        }
        .nav-btn:hover {
            background-color: #e0e0e0;
        }
    </style>
</head>
<body>
<h1 style="text-align: center;">예약 날짜 선택 및 하이라이트 달력</h1>

<div class="calendar-container">
    <div>
        <span class="nav-btn" onclick="changeMonth(-1)">&#8592; 이전 달</span>
        <span id="month-year"></span>
        <span class="nav-btn" onclick="changeMonth(1)">다음 달 &#8594;</span>
    </div>
    <div id="calendar" class="calendar"></div>
</div>

<script>
    const highlightedDates = <%= jsonDates %>;
    const urlParams = new URLSearchParams(window.location.search);
    const houseId = urlParams.get('houseId') || 1;
    let currentYear = parseInt(urlParams.get('year')) || <%= year %>;
    let currentMonth = parseInt(urlParams.get('month')) || <%= month %>;

    function generateCalendar(year, month) {
        const calendar = document.getElementById('calendar');
        const monthYearLabel = document.getElementById('month-year');
        calendar.innerHTML = '';

        // 현재 년도와 월을 설정
        currentYear = year;
        currentMonth = month;

        // 월과 년도 표시
        const monthNames = ["1월", "2월", "3월", "4월", "5월", "6월", "7월", "8월", "9월", "10월", "11월", "12월"];
        monthYearLabel.textContent = `${monthNames[month - 1]} ${year}`;

        const startDate = new Date(Date.UTC(year, month - 1, 1));
        const endDate = new Date(Date.UTC(year, month, 0));
        const startDay = startDate.getUTCDay();
        const totalDays = endDate.getUTCDate();

        // 빈 칸 채우기
        for (let i = 0; i < startDay; i++) {
            const emptyCell = document.createElement('div');
            emptyCell.classList.add('day');
            calendar.appendChild(emptyCell);
        }

        // 날짜 채우기
        for (let day = 1; day <= totalDays; day++) {
            const date = new Date(Date.UTC(year, month - 1, day));
            const dateString = date.toISOString().split('T')[0]; // "YYYY-MM-DD" 형식
            const dayCell = document.createElement('div');
            dayCell.classList.add('day');
            dayCell.textContent = day;
            if (highlightedDates.includes(dateString)) {
                dayCell.classList.add('highlight');
            }
            calendar.appendChild(dayCell);
        }
    }

    function changeMonth(offset) {
        const newDate = new Date(Date.UTC(currentYear, currentMonth - 1 + offset, 1));
        const newUrl = new URL(window.location.href);
        newUrl.searchParams.set('year', newDate.getUTCFullYear());
        newUrl.searchParams.set('month', newDate.getUTCMonth() + 1);
        newUrl.searchParams.set('houseId', houseId);
        window.location.href = newUrl.toString();
    }

    // 전달받은 연도와 월을 사용하여 달력 생성
    generateCalendar(currentYear, currentMonth);
</script>

<div class="form-container">
    <form action="<c:url value='/reserve' />" method="post">
        <input type="hidden" name="houseId" value="<%= houseId %>">
        <div class="form-group">
            <label for="startRegisterDate">시작 날짜:</label>
            <input type="date" id="startRegisterDate" name="startRegisterDate" required>
        </div>
        <div class="form-group">
            <label for="endRegisterDate">종료 날짜:</label>
            <input type="date" id="endRegisterDate" name="endRegisterDate" required>
        </div>
        <button type="submit" class="btn">예약</button>
    </form>
</div>
</body>
</html>