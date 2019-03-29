<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<header>

    <nav class="navbar navbar-dark bg-dark">
        <a class="navbar-brand" href="#">
            <span class="glyphicon glyphicon-qrcode" width="30" height="30" aria-hidden="true"></span>
        </a>
        <ul class="nav justify-content-end">
            <li class="nav-item">
                <a class="nav-link" href="logout">Logout</a>
            </li>
            <li class="nav-item">
                <a class="nav-link active" href="fighterselection">Fight</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="/Alita_Battle_war_exploded">Alita Battle</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="about">About</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="community">News&Community</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="contact">Contact</a>
            </li>
        </ul>
    </nav>
</header>
<p>Challengers</p>

<c:forEach items="${list}" var="character">
    <form method="post" action="acceptChallenge">
        You are challenged by: <input type="text" value="${character.name}" readonly>
        <input type="text" value="${character.race}" readonly>
        <input type="text" value="${character.role}" readonly>
        <input type="submit" value="Accept Challenge">
    </form>
</c:forEach>
</body>
</html>
