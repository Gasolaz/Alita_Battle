<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
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

                    <li class="nav-item d-flex">
                        <i class="nav-link fas fa-coins">GOLD</i>
                        <p class="ml-5 mt-1" id="coins">LOADING</p>
                    </li>

                    <li class="nav-item d-flex">
                        <i class="nav-link fas fa-spinner">EXP</i>
                        <p class="ml-auto mt-1" id="experience">LOADING</p>
                    </li>


                    <li class="nav-item d-flex">
                        <a class="nav-link" id="level" href=""><p class="text-center">LVL</p></a>
                        <p class="ml-auto mt-1" id="level1">LOADING</p>
                    </li>

                </ul>



                <ul class="navbar-nav">
                    <li class="nav-item mr-auto d-flex">
                        <i class="nav-link fas fa-gem d-flex align-items-center"></i>
                        <a class="nav-link" href="shop">Shop</a>
                    </li>

                    <li class="nav-item pt-auto d-flex">
                        <i class=" nav-link fab fa-galactic-senate d-flex align-items-center"></i>
                        <a class="nav-link" href="">Activity</a>
                    </li>

                    <li class="nav-item pt-auto d-flex">
                        <i class="nav-link fas fa-dragon d-flex align-items-center"></i>
                        <a class="nav-link" href="">Heroes</a>
                    </li>
                </ul>


    </nav>

    <nav class="navbar navbar-expand-lg navbar-dark bg-dark ">
        <div class="collapse navbar-collapse d-flex justify-content-around" id="navbarNav">
            <ul class="navbar-nav d-flex flex-row">
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

<%--    <div class="row">--%>
<%--        <div class="col-12">--%>
<%--            <h2>${message.char_name}</h2>--%>
<%--        </div>--%>
<%--    </div>--%>

    <%-- ******************HERO CARD IMAGE********************** --%>

<div class="row pt-5">
    <div class="col-lg-6 d-flex justify-content-around">
    <div class="card" style="width: 18rem;">
        <div class="card-img-top" id="img"  alt="Card image cap"><img src="${image}"></div>
        <div class="card-body">
            <h5 class="card-title">
                <div id="charName">${characterName}</div>
                <div id="valRace">  </div>
                <div id="valRole">  </div>
                <div id="valGen">  </div>
            </h5>
            <p class="card-text">Some quick example text to build on the card title and make up the bulk of the
                card's
                content.</p>
            <%--<a href="#" class="btn btn-primary">Go somewhere</a>--%>
<%--            <button class="btn btn-success" type="submit">Create Character</button>--%>
        </div>
    </div>
    </div>
    <div class="col-lg-6 text-center mt-sm-5">
        <h2>W</h2>
        <h2>S</h2>
    </div>
</div>

    <%-- ******************HERO STATS********************** --%>

    <div class="row pt-5">
        <div class="col-lg-6">
            <h2>Stats</h2>
            <span class="border border-success">

            </span>
        </div>
        <div class="col-lg-6">
            <h2>Illustrational stats</h2>
            <span class="border border-danger" ></span>
        </div>
    </div>


    <%-- ******************CHAT ROOM********************** --%>

<div class="row pt-5">
    <div class="col-lg-12">
    <div class="chat">
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
        <form method="post" action="messages">
            <input type="text" name="msg_text">
            <input type="submit" value="Post">
        </form>
            <hr>

    </div>
    </div>
</div>
</div>
</body>
</html>
