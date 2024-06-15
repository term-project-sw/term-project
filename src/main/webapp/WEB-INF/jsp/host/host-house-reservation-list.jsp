<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/member/member-header.jsp" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Host 예약현황</title>
<style>
    body {
        display: flex;
    }
    /* 왼쪽 버튼 그룹 */
    .vertical-buttons {
        flex: 0 0 200px; /* 고정 너비 */
        display: flex;
        flex-direction: column;
        gap: 10px; /* 버튼 사이의 간격 조절 */
        padding: 20px;
    }
    /* 오른쪽 컨텐츠 */
    .content {
        flex: 1; /* 남은 공간 차지 */
        padding: 20px;
    }
    .container {
        max-width: 800px;
        margin: 0 auto;
    }
    .search-bar {
        margin-bottom: 20px;
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
<div class="vertical-buttons">
    <button onclick="location.href='/path/to/first-page'">First Button</button>
    <button onclick="location.href='/path/to/second-page'">Second Button</button>
    <button onclick="location.href='/path/to/third-page'">Third Button</button>
    <button onclick="location.href='/host/host-house-reservation-list'">예약 현황</button>
</div>
<div class="content">
    <div class="container">
        <h2>예약현황</h2>
        <div class="search-bar">
            <select id="sort-type" name="sortType">
                <option value="1" <c:if test="${allParams.sortType eq '1'}">selected</c:if> > 내림차순</option>
                <option value="2" <c:if test="${allParams.sortType eq '2'}">selected</c:if> > 오름차순</option>
            </select>
            <button id="btn_search">검색</button>
            <span class="page-size-label">페이지 당 표시 개수:</span>
            <select id="items-per-page" name="itemsPerPage">
                <option value="5" <c:if test="${allParams.itemsPerPage eq '5'}">selected</c:if> >5</option>
                <option value="10" <c:if test="${allParams.itemsPerPage eq '10'}">selected</c:if> >10</option>
                <option value="20" <c:if test="${allParams.itemsPerPage eq '20'}">selected</c:if> >20</option>
            </select>
        </div>
        <table class="reservation-table">
            <thead>
                <tr>
                    <th>숙소 번호</th>
                    <th>예약자 번호</th>
                    <th>체크인</th>
                    <th>체크아웃</th>
                </tr>
            </thead>
            <tbody>
                <%-- 회원 리스트 출력 시작 --%>
                <c:forEach var="list" items="${reservationList}">
                    <tr>
                        <td>${list.houseId}</td>
                        <td>${list.memberId}</td>
                        <td>${list.startRegisterDate}</td>
                        <td>${list.endRegisterDate}</td>
                    </tr>
                </c:forEach>
                <%-- 회원 리스트 출력 종료 --%>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>

<script>
$(document).ready(function(){
	
}
		)

</script>
