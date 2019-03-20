<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <p style="color:red">${error}</p>
    <form method="post" action="register">
        <label class="username_label">
            Username:
            <input class="username" type="text" name="username">
        </label>
        <label class="email_label">
            Email:
            <input class="email" type="text" name="email">
        </label>
        <label class="password_label">
            Password:
            <input class="password" type="password" name="pass">
        </label>
        <button type="submit">REGISTER</button>
    </form>
</head>
<body>

</body>
</html>
