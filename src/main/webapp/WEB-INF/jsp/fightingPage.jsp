<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>FightingPage</title>
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
<body>

<style>
    <%@include file="../styles/main.css" %>
</style>

<div class="row justify-content-center">
    <p>Welcome to Arena</p>
</div>

<div class="container">
    <div class="row d-flex justify-content-end">
        <div class="col-lg5">
            <h2>Your panel</h2>
            <div>
                <img class="" id="yourHero" src="${yourImage}">
            </div>
            <div class="player1">
                <p>${yourModel.level}</p>

                <p>${yourModel.hp}</p>
                <p>${yourModel.mana}</p>

                <p>${yourModel.name}</p>
                <p>${yourModel.role}</p>
                <p>${yourModel.race}</p>

                <p>${yourModel.strength}</p>
                <p>${yourModel.agility}</p>
                <p>${yourModel.armor}</p>
                <p>${yourModel.intelligence}</p>
            </div>
        </div>

        <div class="col-lg-2">
            <form class="d-flex align-items-center flex-column" action="fightResult" method="post">
                <label for="attack">Attack</label>
                <select name="attack" id="attack">
                    <option value="head">Head</option>
                    <option value="body">Body</option>
                    <option value="legs">Legs</option>
                    <option value="arms">Arms</option>
                </select>

                <label for="defence">Defend</label>
                <select name="defence" id="defence">Defend
                    <option value="head">Head</option>
                    <option value="body">Body</option>
                    <option value="legs">Legs</option>
                    <option value="arms">Arms</option>
                </select>
                <input name="enemyName" type="hidden" value="${enemyModel.name}">
                <div class="btn btn-primary">
                    <button type="submit">Submit</button>
                </div>

            </form>
        </div>

    <div class="col-lg-5">
        <h2>Enemy panel</h2>
        <div>
            <img class="" id="enemyHero" src="${enemyImage}">
        </div>
        <p>${enemyModel.level}</p>

        <p>${enemyModel.hp}</p>
        <p>${enemyModel.mana}</p>

        <p>${enemyModel.name}</p>
        <p>${enemyModel.role}</p>
        <p>${enemyModel.race}</p>

        <p>${enemyModel.strength}</p>
        <p>${enemyModel.agility}</p>
        <p>${enemyModel.armor}</p>
        <p>${enemyModel.intelligence}</p>
    </div>
    </div>
</div>

<%--<form class="d-flex align-items-center flex-column" action="fightResult" method="post">--%>
<%--    <label for="attack">Attack</label>--%>
<%--    <select name="attack" id="attack">--%>
<%--        <option value="head">Head</option>--%>
<%--        <option value="body">Body</option>--%>
<%--        <option value="legs">Legs</option>--%>
<%--        <option value="arms">Arms</option>--%>
<%--    </select>--%>

<%--    <label for="defence">Defend</label>--%>
<%--    <select name="defence" id="defence">Defend--%>
<%--        <option value="head">Head</option>--%>
<%--        <option value="body">Body</option>--%>
<%--        <option value="legs">Legs</option>--%>
<%--        <option value="arms">Arms</option>--%>
<%--    </select>--%>
<%--    <input name="enemyName" type="hidden" value="${enemyModel.name}">--%>
<%--    <div class="btn btn-primary">--%>
<%--        <button type="submit">Submit</button>--%>
<%--    </div>--%>

<%--</form>--%>

</body>
</html>
