<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>House List</title>
    <style>
        .pagination {
            display: flex;
            justify-content: center;
            list-style: none;
            padding: 0;
        }
        .pagination li {
            margin: 0 5px;
        }
        .pagination a {
            text-decoration: none;
            color: black;
            padding: 8px 16px;
            border: 1px solid #ddd;
            border-radius: 5px;
        }
        .pagination a:hover {
            background-color: #ddd;
        }
        .active a {
            background-color: #4CAF50;
            color: white;
        }
    </style>
</head>
<body>
<h2>House List</h2>

<form method="get" action="/house/houses">
    <input type="text" name="search" placeholder="Search by title" value="${search}">
    <button type="submit">Search</button>
</form>

<table>
    <thead>
    <tr>
        <th>Title</th>
        <th>Host Name</th>
    </tr>
    </thead>
    <tbody>
    <c:forEach items="${housePage.content}" var="house">
        <tr>
            <td><a href="/house/houses/${house.id}">${house.name}</a></td>
            <td>${house.member.name}</td>
        </tr>
    </c:forEach>
    </tbody>
</table>

<div>
    <ul class="pagination">
        <c:if test="${housePage.hasPrevious()}">
            <li><a href="?page=${housePage.number - 1}&size=${housePage.size}&search=${search}">Previous</a></li>
        </c:if>
        <c:forEach var="i" begin="0" end="${housePage.totalPages > 0 ? housePage.totalPages - 1 : 0}">
            <li class="${i == housePage.number ? 'active' : ''}">
                <a href="?page=${i}&size=${housePage.size}&search=${search}">${i + 1}</a>
            </li>
        </c:forEach>
        <c:if test="${housePage.hasNext()}">
            <li><a href="?page=${housePage.number + 1}&size=${housePage.size}&search=${search}">Next</a></li>
        </c:if>
    </ul>
</div>

</body>
</html>
