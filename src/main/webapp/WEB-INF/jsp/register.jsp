<%--
  Created by IntelliJ IDEA.
  User: sarunas
  Date: 19.3.18
  Time: 15.15
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <script>
      <%@include file="../resources/js/main.js"%>
    </script>
</head>
<body>


<p style="color:red">${error}</p>

<form onsubmit="Register(event)">
    <label class="username_label">
        Username:
        <input type="text" onkeyup="changeState(event, 'username')">
    </label>
    <label class="email_label">
        Email:
        <input type="text" onkeyup="changeState(event, 'email')">
    </label>
    <label class="password_label">
        Password:
        <input type="password" onkeyup="changeState(event, 'password')">
    </label>
    <button type="submit">REGISTER</button>
</form>

</body>
</html>
