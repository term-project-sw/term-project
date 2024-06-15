<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>하우스 및 리뷰</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 80%;
            margin: auto;
            overflow: hidden;
        }
        h1, h2, h3 {
            color: #333;
        }
        .house {
            background: #fff;
            margin: 20px 0;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        .reviews {
            margin-top: 10px;
        }
        .review {
            background: #f9f9f9;
            margin: 10px 0;
            padding: 15px;
            border-left: 5px solid #007bff;
            border-radius: 5px;
        }
        .review h4 {
            margin: 0;
            padding-bottom: 10px;
            color: #007bff;
        }
        .review p {
            margin: 5px 0;
        }
        .btn {
            display: inline-block;
            padding: 10px 20px;
            font-size: 14px;
            cursor: pointer;
            text-align: center;
            text-decoration: none;
            outline: none;
            color: #fff;
            background-color: #007bff;
            border: none;
            border-radius: 5px;
            box-shadow: 0 5px #0056b3;
        }
        .btn:hover {
            background-color: #0056b3;
        }
        .btn:active {
            background-color: #0056b3;
            box-shadow: 0 3px #666;
            transform: translateY(2px);
        }
    </style>
</head>
<body>
<div class="container">
    <h1>하우스 및 리뷰</h1>
    <c:forEach var="entry" items="${houseReviewsMap}">
        <div class="house">
            <h2>하우스: ${entry.key.name}</h2>
            <p>주소: ${entry.key.address}</p>
            <p>소개: ${entry.key.introduce}</p>
            <p>설명: ${entry.key.description}</p>
            <p>일일 요금: ${entry.key.pricePerDay}원</p>
            <h3>리뷰:</h3>
            <div class="reviews">
                <c:forEach var="review" items="${entry.value}">
                    <div class="review">
                        <h4>제목: ${review.title}</h4>
                        <p>평점: ${review.rating}</p>
                        <p>${review.content}</p>
                        <button class="btn" onclick="location.href='/comments/new?reviewId=${review.id}'">댓글 달기</button>
                    </div>
                </c:forEach>
            </div>
        </div>
    </c:forEach>
</div>
</body>
</html>
