<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Character Creation</title>
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
    <script src="/Alita_Battle_war_exploded/resources/js/script.js"></script>
</head>

<style>
    <%@include file="../styles/registration.css" %>
</style>

<body>
<header>

    <div class="container">
        <div class="jumbotron text-center">
            <h2 class="display-5" id="heading">
                Welcome to the Character Creation
            </h2>
            <p class="lead">Here you can choose a hero to fight your opponents.</p>
            <hr class="my-4">
            <p>Each hero has a role and gender to choose.</p>
        </div>
    </div>

</header>

<%--<img src="/Alita_Battle_war_exploded/resources/images/test.png" alt="">--%>

<div class="container">

    <%--<div id="valRace"></div>--%>
    <%--<div id="valGen"></div>--%>
    <%--<div id="valRole"></div>--%>


    <%--<div id="img">Selected CharacterDAL</div>--%>


    <form action="create" method="post" id="characterReg">

        <select name="gender" id="gender" onchange="my()">
            <option value="m">Male</option>
            <option value="f">Female</option>
        </select>


        <select name="role" id="role" onchange="my()">
            <option value="wizard">Wizard</option>
            <option value="fighter">Fighter</option>
            <option value="paladin">Paladin</option>
            <option value="rogue">Rogue</option>
        </select>

        <select name="race" id="race" onchange="my()">
            <option value="human">Human</option>
            <option value="elf">Elf</option>
            <option value="dwarf">Dwarf</option>
            <option value="orc">Orc</option>
            <option value="undead">Undead</option>
        </select>


        <%--<div class="name">--%>
        <%--<input type="text" placeholder="CharacterDAL Name" required name="name">--%>
        <%--</div>--%>

        <%--<button class="btn btn-success" type="submit">Create CharacterDAL</button>--%>


        <div class="name">
            <input style="width: 18rem;" type="text" placeholder="Character Name" required name="name">
        </div>
        <div class="card" style="width: 18rem;">
            <div class="card-img-top" id="img" alt="Card image cap"></div>
            <div class="card-body">
                <h5 class="card-title">
                    <div id="valRace"></div>
                    <div id="valRole"></div>
                    <div id="valGen"></div>
                </h5>
                <p class="card-text">Some quick example text to build on the card title and make up the bulk of the
                    card's
                    content.</p>
                <%--<a href="#" class="btn btn-primary">Go somewhere</a>--%>
                <button class="btn btn-success" type="submit">Create Character</button>
            </div>
        </div>

    </form>
</div>

</body>
</html>
