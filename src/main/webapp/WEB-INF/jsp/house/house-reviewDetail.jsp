<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/jsp/context/global-context.jspf" %>


<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>리뷰 상세 정보</title>
    <link rel="stylesheet" href="${CSS}/join.css">


    <%-- Google Jquery Library --%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
</head>
<body>

<div class="container">
    <h2>리뷰 상세 정보</h2>
    <div class="detail-group">
        <label>리뷰 제목:</label>
        <div class="detail-value">${housereviewDetail.title}</div>

    </div>
    <div class="detail-group">
        <label>작성자:</label>
        <div class="detail-value">${housereviewDetail.memberName}</div>
    </div>
    <div class="detail-group">
        <label>등록 날짜:</label>
        <div class="detail-value">${housereviewDetail.updatedAt}</div>
    </div>
    <div class="detail-group">
        <label>평점:</label>
        <div class="detail-value">${housereviewDetail.rating}</div>
    </div>
    <div class="detail-group">
        <label>상세 후기:</label>
        <div class="detail-value">${housereviewDetail.content}</div>
    </div>


    <br>
    <br>
    <hr>

    <div class="detail-group">
        <label>사장님 답변:</label>
        <div class="detail-value">
            <c:choose>
                <c:when test="${not empty housereviewDetail.comment}">
                    ${housereviewDetail.comment}
                </c:when>
                <c:otherwise>
                    답변 대기중
                </c:otherwise>
            </c:choose>
        </div>
    </div>


<%--    <div class="button-group">--%>
<%--        <input type="button" value="수정하기" id="btn_modify">--%>
<%--        <input type="button" value="삭제하기" id="btn_remove">--%>
<%--    </div>--%>
</div>

<script>
    $(document).ready(function(){
        $('#btn_modify').click(function(){
            var formSetting = {
                "action" : "/review/modify",
                "method" : "post"
            };
            var formData = {
                "reviewId": "${reviewDetail.id}",
                "title": $("#title").val(),
                "content": $("#content").val(),
                "rating": $("#rating").val()
            };
            // Ajax 호출
            $.ajax({
                type : formSetting.method,
                url : formSetting.action,
                data : formData,
                success : function(response){

                    alert("리뷰 정보 수정이 완료되었습니다.");
                    location.href="/review/detail/${reviewDetail.id}";

                },
                error : function(response){
                    alert("수정 실패");
                }
            });
        });

        $('#btn_remove').click(function(){
            var formSetting = {
                "action" : "/review/remove",
                "method" : "post"
            };
            var formData = {
                "reviewId": "${reviewDetail.id}"
            };
            // Ajax 호출
            $.ajax({
                type : formSetting.method,
                url : formSetting.action,
                data : formData,
                success : function(response){
                    alert("리뷰 정보가 삭제되었습니다.");
                    location.href="/guest/${reviewDetail.member}/reviews";
                    // history.back();
                },
                error : function(response){
                    alert("삭제 실패");
                }
            });
        });

    });
</script>

</body>
</html>
