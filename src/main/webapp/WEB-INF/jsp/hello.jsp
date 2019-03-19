<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello World: </title>
</head>
<body>
<h2>${message}</h2>
<form action="login" method="post">
    <label class="username_label">
        Username or email:
        <input class="username" type="text" name="username">
    </label>
    <label class="password_label">
        Password:
        <input class="password" type="password" name="pass">
    </label>
    <button type="submit">LOGIN</button>
</form>

<a href="register">Don't have an account? Register then!</a>

</body>
</html>
