<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core_rt" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>예약 리스트</title>

    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }

        .container {
            max-width: 800px;
            margin: 20px auto;
            padding: 20px;
            background-color: #fff;
            border-radius: 8px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }

        h2 {
            text-align: center;
            color: #333;
        }

        .member-table {
            width: 100%;
            border-collapse: collapse;
            margin-top: 20px;
        }

        .member-table th, .member-table td {
            padding: 10px;
            border: 1px solid #ccc;
            text-align: left;
        }

        .member-table th {
            background-color: #f2f2f2;
        }

        .pagination {
            text-align: center;
            margin-top: 20px;
        }

        .page-link {
            display: inline-block;
            padding: 5px 10px;
            margin: 0 2px;
            background-color: #ccc;
            color: #333;
            text-decoration: none;
            border-radius: 3px;
        }

        .page-link:hover {
            background-color: #999;
        }

    </style>

    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</head>
<body>
<form action="<c:url value='/member/${allParams.memberId}/reservations'/>" method="get">
    <input type="hidden" name="num" id="num" value="${allParams.num}" />

    <div class="container">
        <h2>예약 리스트</h2>
        <div class="search-bar">
            <select id="search-type" name="searchType">
                <option value="houseName" <c:if test="${allParams.searchType eq 'houseName'}">selected</c:if>>숙소 이름</option>
                <option value="address" <c:if test="${allParams.searchType eq 'address'}">selected</c:if>>주소</option>
            </select>
            <input type="text" id="search-keyword" placeholder="검색어 입력" name="searchKeyword" value="${allParams.searchKeyword}">
            <button id="btn_search" type="button">검색</button>
            <span class="page-size-label">페이지 당 표시 개수:</span>
            <select id="items-per-page" name="itemsPerPage">
                <option value="5" <c:if test="${allParams.itemsPerPage eq '5'}">selected</c:if>>5</option>
                <option value="10" <c:if test="${allParams.itemsPerPage eq '10'}">selected</c:if>>10</option>
                <option value="20" <c:if test="${allParams.itemsPerPage eq '20'}">selected</c:if>>20</option>
            </select>
            <button id="btn-main-form" type="button">메인 페이지</button>
            <button id="btn-my-form" type="button">마이 페이지</button>
            <button id="btn-edit-form" type="button">회원정보 수정</button>
            <button id="btn-review-form" type="button">마이 리뷰</button>
        </div>
        <table class="member-table">
            <thead>
            <tr>
                <th>숙소 이름</th>
                <th>주소</th>
                <th>예약일</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="reservation" items="${reservationList}">
                <tr>
                    <td><a href="${HOME}/reservation/detail/${reservation.id}">${reservation.house}</a></td>
                    <td>${reservation.address}</td>
                    <td>${reservation.createdAt}</td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
        ${pagingHtml}
    </div>
</form>
<script>
    $(document).ready(function(e){
        $('#btn-main-form').click(function(e){
            location.href = "/main";
        });
        $('#btn-my-form').click(function(e){
            // location.href = "/member/guest-mypage";
            window.location.href = "${pageContext.request.contextPath}/guest-mypage";
        });
        $('#btn-edit-form').click(function(e){
            <%--location.href = "members/myinfo-edit/${allParams.memberId}";--%>
            window.location.href = "${pageContext.request.contextPath}/members/myinfo-edit/${allParams.memberId}";
        });
        $('#btn-review-form').click(function(e){
            location.href = "/guest/${allParams.memberId}/reviews";

        });

        $('#items-per-page').change(function(e){
            $('#btn_search').trigger('click');
        });

        $('#btn_search').click(function(e){
            var formSettings = {
                "action" : "/member/${allParams.memberId}/reservations",
                "method" : "GET"
            };

            $('form').attr('action', formSettings.action);
            $('form').attr('method', formSettings.method);

            $('form').submit();
        });
    });

    pagingJS = function(num){
        $('#num').val(num);

        var formSettings = {
            "action" : "/member/${allParams.memberId}/reservations",
            "method" : "GET"
        };

        $('form').attr('action', formSettings.action);
        $('form').attr('method', formSettings.method);

        $('form').submit();
    };
</script>
</body>
</html>
