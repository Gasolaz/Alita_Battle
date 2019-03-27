<%@ page import="models.Character" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Fight</title>
</head>
<body>
<p>Fighters</p>
<table>
    <%--<% List<Character> characters = %>${list}<%;--%>
        <%--for (Character character : characters) {--%>
    <%--%>--%>
    <%--<tr>--%>
        <%--<td><% character.getCharacter_name();%></td>--%>
        <%--<td><% character.getRace();%></td>--%>
        <%--<td><% character.getRole();%></td>--%>
    <%--</tr>--%>
    <%--<%}%>--%>

        <c:forEach items="${list}" var="character">
            <tr>
                <td>${character.name}</td>
                <td>${character.race}</td>
                <td>${character.role}</td>
            </tr>
        </c:forEach>
</table>
</body>
</html>
