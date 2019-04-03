<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<p>Welcome to Arena</p>

<h2>Your panel</h2>

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

<h2>Enemy panel</h2>

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

<form action="fightResult" method="post">
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

</body>
</html>
