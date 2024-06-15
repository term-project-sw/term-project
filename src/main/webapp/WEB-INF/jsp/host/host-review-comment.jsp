<%@ page contentType="text/html; charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>댓글 작성</title>
    <style>
        body {
            font-family: Arial, sans-serif;
            background-color: #f4f4f4;
            margin: 0;
            padding: 0;
        }
        .container {
            width: 50%;
            margin: auto;
            overflow: hidden;
        }
        form {
            background: #fff;
            margin: 20px 0;
            padding: 20px;
            border-radius: 5px;
            box-shadow: 0 0 10px rgba(0, 0, 0, 0.1);
        }
        label {
            display: block;
            margin-bottom: 10px;
            font-weight: bold;
        }
        textarea {
            width: 100%;
            height: 100px;
            margin-bottom: 20px;
            padding: 10px;
            border: 1px solid #ccc;
            border-radius: 5px;
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
    <h1>댓글 작성</h1>
    <form action="/comments" method="post">
        <input type="hidden" name="reviewId" value="${reviewId}">
        <label for="content">댓글 내용:</label>
        <textarea id="content" name="content" required></textarea>
        <button type="submit" class="btn">댓글 달기</button>
    </form>
</div>
</body>
</html>
