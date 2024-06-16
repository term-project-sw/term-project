<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <title>House Details</title>
</head>
<body>
<h2>House Details</h2>

<table>
    <tr>
        <th>Title:</th>
        <td>${house.name}</td>
    </tr>
    <tr>
        <th>Max People:</th>
        <td>${house.maxPeople}</td>
    </tr>
    <tr>
        <th>Address:</th>
        <td>${house.address}</td>
    </tr>
    <tr>
        <th>Introduce:</th>
        <td>${house.introduce}</td>
    </tr>
    <tr>
        <th>Description:</th>
        <td>${house.description}</td>
    </tr>
    <tr>
        <th>Price per Day:</th>
        <td>${house.pricePerDay}</td>
    </tr>
    <tr>
        <th>Host Name:</th>
        <td>${house.member.name}</td>
    </tr>
</table>


<h2>Rooms</h2>
<ul>
    <c:forEach var="room" items="${rooms}">
        <li>${room.type} - Furniture Count: ${room.furniutureCount}</li>
    </c:forEach>
</ul>

<h2>Images</h2>
<c:if test="${not empty images}">
    <c:forEach var="image" items="${images}">
        <img src="data:image/png;base64,${image.data}" alt="Image" style="width:300px; height:auto;"/>
    </c:forEach>
</c:if>

<a href="/houses">Back to House List</a>

</body>
</html>
