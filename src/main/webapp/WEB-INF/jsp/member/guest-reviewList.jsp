<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/context/global-context.jspf" %>
<%@ include file="/WEB-INF/jsp/member/member-header.jsp" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>마이 리뷰 리스트</title>
    <link rel="stylesheet" href="${CSS}/member-list.css">


    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</head>
<body>
<form action="<c:url value='/guest/${allParams.memberId}/reviews'/>" method="get">
    <input type="hidden" name="num" id="num" value="${allParams.num}" />

    <div class="container">
        <h2>리뷰 리스트</h2>
        <div class="search-bar">
            <select id="search-type" name="searchType">
                <option value="houseName" <c:if test="${allParams.searchType eq 'houseName'}">selected</c:if>>숙소 이름</option>
                <option value="title" <c:if test="${allParams.searchType eq 'title'}">selected</c:if>>리뷰제목</option>
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
            <button id="btn-reserv-form" type="button">예약 리스트</button>

        </div>
        <table class="member-table">
            <thead>
            <tr>
                <th>숙소 이름</th>
                <th>제목</th>
                <th>등록일</th>
                <th>평점</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="review" items="${reviewList}">
                <tr>
                    <td><a href="${HOME}/review/detail/${review.id}">${review.houseName}</a></td>
                    <td>${review.title}</td>
                    <td>${review.updateAt}</td>
                    <td>${review.rating}</td>
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
            location.href = "/house/houses";
        });
        $('#btn-my-form').click(function(e){
            // location.href = "/member/guest-mypage";
            window.location.href = "${pageContext.request.contextPath}/members/guest/mypage";
        });
        $('#btn-edit-form').click(function(e){
            <%--location.href = "members/myinfo-edit/${allParams.memberId}";--%>
            window.location.href = "${pageContext.request.contextPath}/members/myinfo-edit/${allParams.memberId}";
        });
        $('#btn-reserv-form').click(function(e){
            // location.href = "/member/guest-myreview";
            window.location.href = "/member/${allParams.memberId}/reservations";
        });

        $('#items-per-page').change(function(e){
            $('#btn_search').trigger('click');
        });

        $('#btn_search').click(function(e){
            var formSettings = {
                "action" : "/guest/${allParams.memberId}/reviews",
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
            "action" : "/guest/${allParams.memberId}/reviews",
            "method" : "GET"
        };

        $('form').attr('action', formSettings.action);
        $('form').attr('method', formSettings.method);

        $('form').submit();
    };
</script>
</body>
</html>
