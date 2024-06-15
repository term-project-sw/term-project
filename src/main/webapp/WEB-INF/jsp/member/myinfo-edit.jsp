<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <title>Update Member</title>
    <script>
        function showAlertAndReload() {
            alert('수정되었습니다.');
            window.location.reload();
        }

        function submitForm(event) {
            event.preventDefault(); // 기본 폼 제출을 막음
            const form = event.target;
            const formData = new FormData(form);

            fetch(form.action, {
                method: 'POST',
                body: formData,
                headers: {
                    'X-HTTP-Method-Override': 'PUT' // HTTP PUT 메서드 사용
                }
            }).then(response => {
                if (response.ok) {
                    showAlertAndReload();
                } else {
                    alert('수정에 실패했습니다.');
                }
            }).catch(error => {
                alert('오류가 발생했습니다.');
                console.error('Error:', error);
            });
        }

        function deleteMember() {
            if (!confirm('정말로 탈퇴하시겠습니까?')) {
                return;
            }

            const memberId = ${member.id};
            fetch(`${pageContext.request.contextPath}/members/${member.id}`, {
                method: 'DELETE' // HTTP DELETE 메서드 사용
            }).then(response => {
                if (response.ok) {
                    alert('회원 탈퇴가 완료되었습니다.');
                    //window.location.reload();
                    window.location.href = "${pageContext.request.contextPath}/main"; // 메인 페이지로 이동
                } else {
                    alert('회원 탈퇴에 실패했습니다.');
                }
            }).catch(error => {
                alert('오류가 발생했습니다.');
                console.error('Error:', error);
            });
        }

    </script>
</head>
<body>
<h1>Update Member Information</h1>
<form action="${pageContext.request.contextPath}/members/${member.id}" method="post" onsubmit="submitForm(event)">
    <input type="hidden" name="_method" value="put" />

    <label for="email">Email:</label>
    <input type="email" id="email" name="email" value="${member.email}" required /><br><br>

    <label for="password">Password:</label>
    <input type="password" id="password" name="password" value="${member.password}" required /><br><br>

    <label for="phone">Phone:</label>
    <input type="text" id="phone" name="phone" value="${member.phone}" required /><br><br>

    <label for="name">Name:</label>
    <input type="text" id="name" name="name" value="${member.name}" required /><br><br>

    <button type="submit">수정</button>
</form>
<br>

<button type="button" onclick="deleteMember()">회원 탈퇴</button>


</body>
</html>
