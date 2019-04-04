<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<p>Fighter Arena</p>

<c:forEach items="${list}" var="character">
    <form method="post" action="arena">
        You are challenged by: <input type="text" value="${character.name}" name="name" readonly>
        <input type="text" value="${character.race}" name="race" readonly>
        <input type="text" value="${character.role}" name="role" readonly>
        <input type="submit" value="Fight!">
    </form>
</c:forEach>

</body>
</html>
