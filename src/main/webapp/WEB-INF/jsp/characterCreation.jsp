<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Character Creation</title>
    <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+8abtTE1Pi6jizo" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.7/umd/popper.min.js" integrity="sha384-UO2eT0CpHqdSJQ6hJty5KVphtPhzWj9WO1clHTMGa3JDZwrnQq4sF86dIHNDz0W1" crossorigin="anonymous"></script>
    <script src="https://cdnjs.cloudflare.com/ajax/libs/jquery-validate/1.19.0/jquery.validate.js"></script>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
</head>
<body>
    <header>
        <div class="container">
            <h2 class="title">
                Welcome to the Character Creation!! <br><br><br>
            </h2>
        </div>
    </header>
    <div class="container">
        <div class="dropdown">
            <form action="create" method="post">

                <select name="race" id="race">
                    <option value="human">Human</option>
                    <option value="elf">Elf</option>
                    <option value="dwarf">Dwarf</option>
                    <option value="orc">Orc</option>
                    <option value="undead">Undead</option>
                </select>

                <select name="role" id="role">
                    <option value="wizard">Wizard</option>
                    <option value="fighter">Fighter</option>
                    <option value="paladin">Paladin</option>
                    <option value="rogue">Rogue</option>
                </select>

                <select name="gender" id="gender">
                    <option value="m">Male</option>
                    <option value="f">Female</option>
                </select>

                <div class="name">
                    <input type="text" placeholder="Character Name" required name="name">
                </div>

                <button type="submit">Create Character</button>
            </form>
        </div>
    </div>
</body>
</html>
