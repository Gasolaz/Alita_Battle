<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Sklep</title>
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js"
                integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo"
                crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js"
                integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1"
                crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.js"></script>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js"
                integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM"
                crossorigin="anonymous"></script>
    </head>
</head>

<style>
    <%@include file="../styles/main.css" %>
</style>

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
                <a class="nav-link active" href="shop">Sklep</a>
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


    <h3>Weapons left hand</h3>
    <table cellpadding="2" cellspacing="2" border="1">
        <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Strength</th>
            <th>Agility</th>
            <th>Intelligence</th>
            <th>Defense</th>
            <th>Hp</th>
        </tr>
         <c:forEach var="item" items="${left_hand_items}">
                <form action="shop" method="post">
                    <input type="hidden" value="left_hand_items" name="tablename">
                    <input type="hidden" value="${item.id}" name="id">
                    <tr>
                        <td> <input type="text" name="name" value="${item.name}" readonly> </td>
                        <td> <input type="text" name="price" value="${item.price}" readonly> </td>
                        <td> <input type="text" name="price" value="${item.str}"readonly> </td>
                        <td> <input type="text" name="price" value="${item.agi}"readonly> </td>
                        <td> <input type="text" name="price" value="${item.intel}"readonly> </td>
                        <td> <input type="text" name="price" value="${item.def}"readonly> </td>
                        <td> <input type="text" name="price" value="${item.hp}"readonly> </td>
                        <td> <button class="btn btn-success" type="submit">Buy</button> </td>
                    </tr>
                        <%--                <br>--%>
                </form>
         </c:forEach>
    </table>
    <h3>Weapons right hand</h3>
    <table cellpadding="2" cellspacing="2" border="1">
        <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Strength</th>
            <th>Agility</th>
            <th>Intelligence</th>
            <th>Defense</th>
            <th>Hp</th>
        </tr>
        <c:forEach var="item" items="${right_hand_items}">
            <form action="shop" method="post">
                <input type="hidden" value="right_hand_items" name="tablename">
                <input type="hidden" value="${item.id}" name="id">
                <tr>
                    <td> <input type="text" name="name" value="${item.name}" readonly> </td>
                    <td> <input type="text" name="price" value="${item.price}" readonly> </td>
                    <td> <input type="text" name="price" value="${item.str}"readonly> </td>
                    <td> <input type="text" name="price" value="${item.agi}"readonly> </td>
                    <td> <input type="text" name="price" value="${item.intel}"readonly> </td>
                    <td> <input type="text" name="price" value="${item.def}"readonly> </td>
                    <td> <input type="text" name="price" value="${item.hp}"readonly> </td>
                    <td> <button class="btn btn-success" type="submit">Buy</button> </td>
                </tr>
<%--                <br>--%>
            </form>
        </c:forEach>
    </table>
    <h3>Torso</h3>
    <table cellpadding="2" cellspacing="2" border="1">
        <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Strength</th>
            <th>Agility</th>
            <th>Intelligence</th>
            <th>Defense</th>
            <th>Hp</th>
        </tr>
        <c:forEach var="item" items="${torso_items}">
            <form action="shop" method="post">
                <input type="hidden" value="torso_items" name="tablename">
                <input type="hidden" value="${item.id}" name="id">
                <tr>
                    <td> <input type="text" name="name" value="${item.name}" readonly> </td>
                    <td> <input type="text" name="price" value="${item.price}" readonly> </td>
                    <td> <input type="text" name="price" value="${item.str}"readonly> </td>
                    <td> <input type="text" name="price" value="${item.agi}"readonly> </td>
                    <td> <input type="text" name="price" value="${item.intel}"readonly> </td>
                    <td> <input type="text" name="price" value="${item.def}"readonly> </td>
                    <td> <input type="text" name="price" value="${item.hp}"readonly> </td>
                    <td> <button class="btn btn-success" type="submit">Buy</button> </td>
                </tr>
<%--                <br>--%>
            </form>
        </c:forEach>
    </table>
    <h3>Legs</h3>
    <table cellpadding="2" cellspacing="2" border="1">
        <tr>
            <th>Name</th>
            <th>Price</th>
            <th>Strength</th>
            <th>Agility</th>
            <th>Intelligence</th>
            <th>Defense</th>
            <th>Hp</th>
        </tr>
        <c:forEach var="item" items="${leg_items}">
            <form action="shop" method="post">
                <input type="hidden" value="leg_items" name="tablename">
                <input type="hidden" value="${item.id}" name="id">
                <tr>
                    <td> <input type="text" name="name" value="${item.name}" readonly> </td>
                    <td> <input type="text" name="price" value="${item.price}" readonly> </td>
                    <td> <input type="text" name="price" value="${item.str}"readonly> </td>
                    <td> <input type="text" name="price" value="${item.agi}"readonly> </td>
                    <td> <input type="text" name="price" value="${item.intel}"readonly> </td>
                    <td> <input type="text" name="price" value="${item.def}"readonly> </td>
                    <td> <input type="text" name="price" value="${item.hp}"readonly> </td>
                    <td> <button class="btn btn-success" type="submit">Buy</button> </td>
                </tr>
<%--                <br>--%>
            </form>
        </c:forEach>
    </table>
</body>
</html>
