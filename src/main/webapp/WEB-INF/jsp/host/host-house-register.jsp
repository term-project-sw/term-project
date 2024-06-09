<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>집 등록</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#addRoomsButton').click(function() {
                var roomCount = $('#roomCount').val();
                $('#roomsContainer').empty();
                for (var i = 0; i < roomCount; i++) {
                    $('#roomsContainer').append(
                        '<div class="room">' +
                        '<h3>방 ' + (i + 1) + '</h3>' +
                        '<label for="furnitureCount' + i + '">가구 개수:</label>' +
                        '<input type="number" id="furnitureCount' + i + '" name="furnitureCount' + i + '" required><br>' +
                        '<label for="roomType' + i + '">방 유형:</label>' +
                        '<select id="roomType' + i + '" name="roomType' + i + '" required>' +
                        '<option value="BEDROOM">침실</option>' +
                        '<option value="BATHROOM">욕실</option>' +
                        '</select><br>' +
                        '</div>'
                    );
                }
            });

            $('#registerHouseForm').submit(function(event) {
                event.preventDefault();

                var formData = new FormData(this);

                var rooms = [];
                var roomCount = $('#roomCount').val();
                for (var i = 0; i < roomCount; i++) {
                    var room = {
                        furniutureCount: $('#furnitureCount' + i).val(),
                        roomType: $('#roomType' + i).val()
                    };
                    rooms.push(room);
                }

                formData.append("rooms", JSON.stringify(rooms));

                $.ajax({
                    url: '<c:url value="/house/register"/>',
                    type: 'POST',
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function(response) {
                        alert('집이 성공적으로 등록되었습니다!');
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        alert('오류: ' + errorThrown);
                    }
                });
            });
        });
    </script>
</head>
<body>
<h1>집 등록</h1>
<form id="registerHouseForm" enctype="multipart/form-data" method="post">
    <label for="name">이름:</label>
    <input type="text" id="name" name="name" required><br>

    <label for="maxPeople">최대 인원:</label>
    <input type="number" id="maxPeople" name="maxPeople" required><br>

    <label for="address">주소:</label>
    <input type="text" id="address" name="address" required><br>

    <label for="introduce">소개:</label>
    <textarea id="introduce" name="introduce" required></textarea><br>

    <label for="description">설명:</label>
    <textarea id="description" name="description" required></textarea><br>

    <label for="pricePerDay">1인당 가격:</label>
    <input type="number" id="pricePerDay" name="pricePerDay" required><br>

    <label for="images">이미지:</label>
    <input type="file" id="images" name="images" multiple required><br>

    <label for="roomCount">방 개수:</label>
    <input type="number" id="roomCount" name="roomCount" min="1" required><br>
    <button type="button" id="addRoomsButton">방 추가</button><br><br>

    <div id="roomsContainer"></div>

    <button type="submit">등록</button>
</form>
</body>
</html>
