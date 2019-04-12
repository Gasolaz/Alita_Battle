<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <title>Alita Battle</title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css"
          integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
    <link href="https://fonts.googleapis.com/css?family=VT323" rel="stylesheet">
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
<style>
    <%@include file="../styles/main.css" %>
</style>
<body>
<%@include file="_headerMain.jsp" %>

<div class="container">

    <nav class="navbar navbar-dark bg-dark ">

                <ul class="navbar-nav">

                    <li class="nav-item">
                        <i class="nav-link fas fa-coins">GOLD</i>
                        <span>LOADING</span>
                    </li>

                    <li class="nav-item">
                        <i class="nav-link fas fa-spinner">EXP</i>
                    </li>


                    <li class="nav-item">
                        <a class="nav-link" id="level" href=""><p class="text-center">LVL</p></a>
                    </li>

                </ul>



                <ul class="navbar-nav">
                    <li class="nav-item">
                        <a class="nav-link" href="shop">Shop</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="">Activity</a>
                    </li>

                    <li class="nav-item">
                        <a class="nav-link" href="">Heroes</a>
                    </li>
                </ul>


    </nav>

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark ">
        <div class="collapse navbar-collapse d-flex justify-content-around" id="navbarNav">
            <ul class="navbar-nav">
                <li class="nav-item mr-5">
                    <a class="nav-link" href="arena">Arena</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="challenge">Challenges</a>
                </li>
                <li class="nav-item ml-5">
                    <a class="nav-link" href="fighterselection">Fight</a>
                </li>
            </ul>
        </div>
    </nav>


    <div>
        <img src="${image}">
    </div>

    <div class="chat">
        <div class="container">
            <div class="table">
                <table>
                    <c:forEach items="${messages}" var="message">
                        <tr>
                            <td>${message.char_name} &emsp;</td>
                            <td>${message.msg_time} &emsp;</td>
                            <td>${message.msg_text} &emsp;</td>
                        </tr>
                    </c:forEach>
                </table>
            </div>
            <hr>
            <form method="post" action="messages">
                <input type="text" name="msg_text">
                <input type="submit" value="Post">
            </form>
        </div>
    </div>
</div>
</body>
</html>
