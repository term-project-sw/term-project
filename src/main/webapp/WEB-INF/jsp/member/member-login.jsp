<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<h2>Login</h2>
<form id="loginForm">
    <div>
        <label for="loginEmail">Email:</label>
        <input id="loginEmail" name="email" type="email" required/>
    </div>
    <div>
        <label for="loginPassword">Password:</label>
        <input id="loginPassword" name="password" type="password" required/>
    </div>
    <div>
        <button type="button" id="loginButton">Login</button>
    </div>
</form>

<script>
    $(document).ready(function() {
        $('#loginButton').click(function() {
            var loginData = {
                email: $('#loginEmail').val(),
                password: $('#loginPassword').val()
            };

            $.ajax({
                type: 'POST',
                url: '/member/login',
                contentType: 'application/json',
                data: JSON.stringify(loginData),
                success: function(response) {
                    // handle success
                    alert(response);
                },
                error: function(error) {
                    // handle error
                    alert('Login failed: ' + error.responseText);
                }
            });
        });
    });
</script>
</body>
</html>
