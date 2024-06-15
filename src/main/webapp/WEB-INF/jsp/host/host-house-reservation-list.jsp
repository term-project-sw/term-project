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
            margin: 0;
            padding: 0;
        }
        .main-container {
            display: flex;
            padding-top: 60px; /* 헤더 높이만큼 패딩을 추가합니다 */
            height: 100vh; /* 전체 화면 높이 */
        }
        /* 왼쪽 버튼 그룹 */
        .vertical-buttons {
            flex: 0 0 200px; /* 고정 너비 */
            display: flex;
            flex-direction: column;
            gap: 10px; /* 버튼 사이의 간격 조절 */
            padding: 20px;
            background-color: #f0f0f0; /* 배경색 추가 */
            height: calc(100vh - 60px); /* 화면 높이에서 헤더 높이를 뺀 값으로 높이 설정 */
            box-shadow: 2px 0 5px rgba(0, 0, 0, 0.1); /* 왼쪽 버튼 그룹에 그림자 추가 */
        }
        /* 오른쪽 컨텐츠 */
        .content {
            flex: 1; /* 남은 공간 차지 */
            display: flex;
            flex-direction: column;
            padding: 20px;
            overflow-y: auto; /* 세로 스크롤 가능 */
        }
        .container {
            flex: 1; /* 남은 공간 차지 */
            padding: 20px;
            margin: 0 auto;
            width: 100%;
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
<div class="main-container">
    <div class="vertical-buttons">
        <div class="vertical-buttons">
	    <button onclick="location.href='/members/myinfo-edit/${sessionScope.memberId}'">회원 정보 수정</button>
	    <button onclick="location.href='/house/register'">숙소 등록</button>
	    <button onclick="location.href='/host/host-house-reservation-list'">예약 현황</button>
	</div>
    </div>
    <div class="content">
        <div class="container">
            <h2>예약현황</h2>
            <div class="search-bar">
                <select id="sort-type" name="sortType">
                    <option value="1" <c:if test="${allParams.sortType eq '1'}">selected</c:if>>내림차순</option>
                    <option value="2" <c:if test="${allParams.sortType eq '2'}">selected</c:if>>오름차순</option>
                </select>
                <button id="btn_search">검색</button>
                <span class="page-size-label">페이지 당 표시 개수:</span>
                <select id="items-per-page" name="itemsPerPage">
                    <option value="5" <c:if test="${allParams.itemsPerPage eq '5'}">selected</c:if>>5</option>
                    <option value="10" <c:if test="${allParams.itemsPerPage eq '10'}">selected</c:if>>10</option>
                    <option value="20" <c:if test="${allParams.itemsPerPage eq '20'}">selected</c:if>>20</option>
                </select>
            </div>
            <table class="reservation-table">
      		<input type="hidden" id="member-id" name="memberId" value="${SessionScope.memberId}" />
      		<input type="hidden" id="member-role" name="memberRole" value="${SessionScope.memberRole}" />
                <thead>
                    <tr>
                        <th>예약 번호</th>
                        <th>상태</th>
                        <th>체크인</th>
                        <th>체크아웃</th>
                        <th>상태변경</th>

                    </tr>
                </thead>
                <tbody>
                    <c:forEach var="list" items="${reservationList}">
                        <tr>
                            <td>${list.id}</td>
                            <td>${list.progress}</td>
                            <td>${list.startRegisterDate}</td>
                            <td>${list.endRegisterDate}</td>
                            <td>
	                            <button onClick="location.href='/host/update_reservation/${list.id}/1'">승인</button>
	                            <button onClick="location.href='/host/update_reservation/${list.id}/0'">거부</button>
                            </td>
                        </tr>
                    </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>

<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script>
$(document).ready(function(){
    $('#btn_search').click(function(e){
        e.preventDefault(); // 폼의 기본 제출 동작을 막음
        var formSettings = {
            "action": "/host/host-house-reservation-list",
            "method": "POST"
        };
        $('form').attr('action', formSettings.action);
        $('form').attr('method', formSettings.method);
        $('form').submit();
    });
});
</script>
