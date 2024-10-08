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
        <button onclick="location.href='/members/myinfo-edit/${sessionScope.memberId}'">회원 정보 수정</button>
        <button onclick="location.href='/house/register'">숙소 등록</button>
        <button onclick="location.href='/host/host-house-reservation-list'">예약 현황</button>
        <button onclick="location.href='/houses/reviews'">리뷰 전체 목록</button>
    </div>
    <div class="content">
        <div class="container">
            <h2>예약현황</h2>
            <table class="reservation-table">
                <input type="hidden" id="member-id" name="memberId" value="${SessionScope.memberId}" />
                <thead>
                <tr>
                    <th>예약 번호</th>
                    <th>상태</th>
                    <th>체크인</th>
                    <th>체크아웃</th>
                </tr>
                </thead>
                <tbody>
                <c:forEach var="list" items="${reservationList}">
                    <tr>
                        <td>${list.id}
                            <button onclick="location.href='/reservation/detail/'+${list.id}">예약상세</button>
                        </td>
                        <td>${list.progress}
                            <c:if test="${list.progress == 'IN_PROGRESS'}">
                                <button class="update-status-btn" data-id="${list.id}" data-status="COMPLETE">승인</button>
                                <button class="update-status-btn" data-id="${list.id}" data-status="CANCEL">취소</button>
                            </c:if>
                        </td>
                        <td>${list.startRegisterDate}</td>
                        <td>${list.endRegisterDate}</td>
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

    function updateReservationStatus(id, status) {
        $.ajax({
            type: 'POST',
            url: '/host/update_reservation/' + id + '/' + status, // 올바르게 URL 구성
            success: function(response) {
                fetchReservationList(); // 예약 목록을 다시 불러오는 함수 호출
            },
            error: function(error) {
                alert('Update failed: ' + error.responseText);
            }
        });
    }

    function fetchReservationList() {
        // 예약 목록을 다시 불러오는 Ajax 요청
        $.ajax({
            type: 'GET',
            url: '/host/host-house-reservation-list',
            success: function(response) {
                // 예약 목록을 다시 로딩합니다.
                location.reload(); // 예약 목록이 포함된 HTML을 새로 갱신합니다.
            },
            error: function(error) {
                alert('Failed to fetch reservation list: ' + error.responseText);
            }
        });
    }

    $(document).on('click', '.update-status-btn', function() {
        var id = $(this).data('id'); // data-id 속성을 통해 id 값을 가져옵니다
        var status = $(this).data('status'); // data-status 속성을 통해 status 값을 가져옵니다
        updateReservationStatus(id, status);
    });
</script>
