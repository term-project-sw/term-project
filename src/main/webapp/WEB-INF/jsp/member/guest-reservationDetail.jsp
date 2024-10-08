<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ include file="/WEB-INF/jsp/member/member-header.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>예약 상세 정보</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            max-width: 800px;
            margin: 0 auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        h2 {
            text-align: center;
            color: #333;
        }
        .detail-group {
            margin-bottom: 20px;
        }
        label {
            display: block;
            margin-bottom: 5px;
            color: #666;
        }
        .detail-value {
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 4px;
            background-color: #f9f9f9;
        }
        .button-group {
            display: flex;
            justify-content: space-between;
        }
        input[type="button"] {
            padding: 10px;
            width: 48%;
            background-color: #007bff;
            color: #fff;
            border: none;
            border-radius: 4px;
            cursor: pointer;
        }
        input[type="button"]:hover {
            background-color: #0056b3;
        }
    </style>

    <%-- Google Jquery Library --%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

<div class="container">
    <h2>예약 상세 정보</h2>
    <div class="detail-group">
        <label>숙소 이름:</label>
        <div class="detail-value">${reservationDetail.houseName}</div>
    </div>
    <div class="detail-group">
        <label>예약한 날짜:</label>
        <div class="detail-value">${reservationDetail.updatedAt}</div>
    </div>
    <div class="detail-group">
        <label>주소:</label>
        <div class="detail-value">${reservationDetail.address}</div>
    </div>
    <div class="detail-group">
        <label>예약자 이름:</label>
        <div class="detail-value">${reservationDetail.guestName}</div>
    </div>
    <div class="detail-group">
        <label>숙박 시작 날짜:</label>
        <div class="detail-value">
            <c:choose>
                <c:when test="${reservationDetail.progress eq 'IN_PROGRESS'}">
                    <input type="date" id="start_date" value="${reservationDetail.startDate}">
                </c:when>
                <c:otherwise>
                    ${reservationDetail.startDate}
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <div class="detail-group">
        <label>숙박 종료 날짜:</label>
        <div class="detail-value">
            <c:choose>
                <c:when test="${reservationDetail.progress eq 'IN_PROGRESS'}">
                    <input type="date" id="end_date" value="${reservationDetail.endDate}">
                </c:when>
                <c:otherwise>
                    ${reservationDetail.endDate}
                </c:otherwise>
            </c:choose>
        </div>
    </div>
    <div class="detail-group">
        <label>총 결제 금액:</label>
        <div class="detail-value">${reservationDetail.totalPrice} 원</div>
    </div>
    <div class="detail-group">
        <label>최대 가능 인원:</label>
        <div class="detail-value">${reservationDetail.maxPeople} 명</div>
    </div>
    <div class="detail-group">
        <label>예약 상태:</label>
        <div class="detail-value">${reservationDetail.progress}</div>
    </div>
    <div class="detail-group">
        <label>판매자 이름:</label>
        <div class="detail-value">${reservationDetail.hostName}</div>
    </div>
    <div class="detail-group">
        <label>판매자 전화번호:</label>
        <div class="detail-value">${reservationDetail.hostPhone}</div>
    </div>
    <div class="button-group">
        <c:choose>
            <c:when test="${sessionScope.role eq 'GUEST'}">
                <c:choose>
                    <c:when test="${reservationDetail.progress eq 'COMPLETE'}">
                        <input type="button" value="리뷰 등록하기" id="btn_review">
                    </c:when>
                    <c:otherwise>
                        <input type="button" value="수정하기" id="btn_modify">
                    </c:otherwise>
                </c:choose>
            </c:when>
        </c:choose>
        <input type="button" value="삭제하기" id="btn_remove">
    </div>
    <div class="vertical-buttons">
        <button onclick="location.href='/members/myinfo-edit/${reservationDetail.memberId}'">회원 정보 수정</button>
        <button onclick="location.href='/member/${reservationDetail.memberId}/reservations'">예약 내역</button>
        <button onclick="location.href='/guest/${reservationDetail.memberId}/reviews'">마이 리뷰</button>
    </div>
</div>

<script>
    $(document).ready(function(){
        $('#btn_modify').click(function(){
            var formSetting = {
                "action" : "/reservation/modify",
                "method" : "post"
            };
            var formData = {
                "reservationId": "${reservationDetail.id}",
                "startRegisterDate": $("#start_date").val(),
                "endRegisterDate": $("#end_date").val()
            };
            // Ajax 호출
            $.ajax({
                type : formSetting.method,
                url : formSetting.action,
                data : formData,
                success : function(response){
                    alert("예약 정보 수정이 완료되었습니다.");
                    location.href="/reservation/detail/${reservationDetail.id}";
                },
                error : function(response){
                    alert("수정 실패");
                }
            });
        });

        $('#btn_remove').click(function(){
            var formSetting = {
                "action" : "/reservation/remove",
                "method" : "post"
            };
            var formData = {
                "reservationId": "${reservationDetail.id}"
            };
            // Ajax 호출
            $.ajax({
                type : formSetting.method,
                url : formSetting.action,
                data : formData,
                success : function(response){
                    alert("예약 정보가 삭제되었습니다.");
                    location.href="/member/${reservationDetail.memberId}/reservations";
                    // history.back();
                },
                error : function(response){
                    alert("삭제 실패");
                }
            });
        });

        $('#btn_review').click(function(){
            var houseId = "${reservationDetail.houseId}";
            location.href = "/house/" + houseId + "/review/registerForm";
        });
    });
</script>

</body>
</html>
