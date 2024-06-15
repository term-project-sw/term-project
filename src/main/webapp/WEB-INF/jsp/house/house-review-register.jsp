<%@ page language="java" contentType="text/html; charset=UTF-8"%>
<%@ include file="/WEB-INF/jsp/context/global-context.jspf" %>

<!DOCTYPE html>
<html lang="ko">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>리뷰등록</title>
    <link rel="stylesheet" href="${CSS}/join.css">

    <%-- Google Jquery Library --%>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

</head>
<body>

<div class="container">
    <h2>리뷰등록</h2>
    <form id="reviewForm">
        <div class="form-group">
            <label for="title">제목:</label>
            <input type="text" id="title" name="title" required>
        </div>
        <div class="form-group">
            <label for="rating">평점:</label>
            <input type="number" id="rating" name="rating" required min="1" max="5">
        </div>
        <div class="form-group">
            <label for="content">상세 후기:</label>
            <input type="text" id="content" name="content" required>
        </div>
        <div class="form-group">
            <input type="button" value="등록하기" id="btn_register">
        </div>
    </form>
</div>

<script>
    $(document).ready(function() {
        const houseId = "${houseId}"; // 서버에서 전달된 houseId
        const memberId = "<%= session.getAttribute("memberId") %>"; // 세션에서 가져온 memberId

        $('#btn_register').click(function() {
            var formSetting = {
                "action": "/house/" + houseId + "/review/register",
                "method": "post"
            };

            var formData = $('#reviewForm').serialize();

            // Ajax 호출
            $.ajax({
                type: formSetting.method,
                url: formSetting.action,
                data: formData,
                success: function(response) {
                    if (response.REPL_CD != "000000") {
                        alert(response.REPL_MSG);
                        // 리뷰 등록 성공 시, 페이지 이동
                        window.location.href = "/guest/" + memberId + "/reviews";
                    }
                },
                error: function(response) {
                    alert("리뷰 등록에 실패했습니다.");
                }
            });
        });
    });
</script>

</body>
</html>
