<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Register</title>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
</head>
<body>
<h2>Register</h2>
<form id="registerForm">
    <div>
        <label for="email">Email:</label>
        <input id="email" name="email" type="email" required/>
    </div>
    <div>
        <label for="password">Password:</label>
        <input id="password" name="password" type="password" required/>
    </div>
    <div>
        <label for="role">Role:</label>
        <select id="role" name="role" required>
            <option value="GUEST">Guest</option>
            <option value="HOST">Host</option>
        </select>
    </div>
    <div>
        <button type="button" id="registerButton">Register</button>
    </div>
</form>

<script>
    $(document).ready(function() {
        $('#registerButton').click(function() {
            var formData = {
                email: $('#email').val(),
                password: $('#password').val(),
                role: $('#role').val()
            };

            $.ajax({
                type: 'POST',
                url: '/member/register',
                contentType: 'application/json',
                data: JSON.stringify(formData),
                success: function(response) {
                    // handle success
                    alert('Registration successful!');
                },
                error: function(error) {
                    // handle error
                    alert('Registration failed: ' + error.responseText);
                }
            });
        });
    });
</script>
</body>
</html>