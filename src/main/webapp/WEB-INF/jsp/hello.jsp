<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello World: </title>
</head>
<body>
<h2>${message}</h2>
<form action="login" method="post">
    <p>
        <label for="username">Username</label>
        <input type="text" id="username">
        <br>
        <input type="submit" id="submit-button">
    </p>
</form>
<a href="register">Register</a>
</body>
</html>
