<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello World: </title>
    <script>
      <%@include file="../resources/js/main.js"%>
    </script>
</head>
<body>
<h2>${message}</h2>

<form onsubmit="Login(event)">
    <label class="username_label">
        Username:
        <input type="text" onkeyup="changeState(event, 'username')">
    </label>
    <label class="password_label">
        Password:
        <input type="password" onkeyup="changeState(event, 'password')">
    </label>
    <button type="submit">LOGIN</button>
</form>

<a href="register">Don't have an account? Register then!</a>

</body>
</html>
