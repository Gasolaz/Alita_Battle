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
        <%--<label class="password_label">--%>
            <%--Repeat password:--%>
            <%--<input class="password" type="password" name="pass">--%>
        <%--</label>--%>
        <button type="submit">REGISTER</button>
    </form>
</head>
<body>

</body>
</html>
