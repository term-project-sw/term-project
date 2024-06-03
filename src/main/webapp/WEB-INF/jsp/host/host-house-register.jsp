<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Register House</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <script>
        $(document).ready(function() {
            $('#registerHouseForm').submit(function(event) {
                event.preventDefault();

                var formData = new FormData();
                formData.append("name", $('#name').val());
                formData.append("maxPeople", $('#maxPeople').val());
                formData.append("address", $('#address').val());
                formData.append("introduce", $('#introduce').val());
                formData.append("description", $('#description').val());
                formData.append("pricePerPerson", $('#pricePerPerson').val());
                $.each($('#images')[0].files, function(i, file) {
                    formData.append("images", file);
                });

                $.ajax({
                    url: '<c:url value="/house/register"/>',
                    type: 'POST',
                    data: formData,
                    processData: false,
                    contentType: false,
                    success: function(response) {
                        alert('House registered successfully!');
                    },
                    error: function(jqXHR, textStatus, errorThrown) {
                        alert('Error: ' + errorThrown);
                    }
                });
            });
        });
    </script>
</head>
<body>
<h1>Register House</h1>
<form id="registerHouseForm" enctype="multipart/form-data">
    <label for="name">Name:</label>
    <input type="text" id="name" name="name" required><br>

    <label for="maxPeople">Max People:</label>
    <input type="number" id="maxPeople" name="maxPeople" required><br>

    <label for="address">Address:</label>
    <input type="text" id="address" name="address" required><br>

    <label for="introduce">Introduce:</label>
    <textarea id="introduce" name="introduce" required></textarea><br>

    <label for="description">Description:</label>
    <textarea id="description" name="description" required></textarea><br>

    <label for="pricePerPerson">Price Per Person:</label>
    <input type="number" id="pricePerPerson" name="pricePerPerson" required><br>

    <label for="images">Images:</label>
    <input type="file" id="images" name="images" multiple required><br>

    <button type="submit">Register</button>
</form>
</body>
</html>
