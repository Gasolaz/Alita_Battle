<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Alita Battle! </title>
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

<header>

    <nav class="navbar navbar-dark bg-dark">
        <a class="navbar-brand" href="#">
            <span class="glyphicon glyphicon-qrcode" width="30" height="30" aria-hidden="true"></span>
        </a>
        <ul class="nav justify-content-end">
            <li class="nav-item">
                <a class="nav-link active" href="/Alita_Battle_war_exploded">Alita Battle</a>
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
<div class="container">

    <div class="headline">
        <h2 style="text-align:center">You are about to enter the game</h2>
    </div>

    <div class="text">
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad cumque, dolor eius harum illum magnam maxime
            nemo nobis obcaecati officia omnis recusandae sunt ullam ut veritatis! Fugiat fugit in quis.<br><br><br><br><br>
        </p>
    </div>
    <p style="color: red">${AccessDenied}</p>
    <div class="buttons" style="text-align:center">
        <a href="" class="btn btn-primary" data-toggle="modal" data-target="#modalLoginForm"><span>Sign In</span></a>
        <a href="register" class="btn btn-secondary" data-target="#modalRegForm"><span>Sign Up</span></a>
    </div>


    <div class="modal fade" id="modalLoginForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel"
         aria-hidden="true">
        <div class="modal-dialog" role="document">
            <div class="modal-content">

                <div class="modal-header text-center">
                    <h4 class="modal-title w-100 font-weight-bold">Alita Battle Campus</h4>
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>

                <div class="container">

                    <div class="form-container off-canvas">

                        <form role="form" action="loggedIn" method="post" class="form-signin">
                            <h2>Please sign in...</h2>

                            <div class="form-group">
                                <label for="EmailAddress"><span>*</span> Username or Email Address</label>
                                <input type="text" class="form-control" name="username" id="EmailAddress"
                                       aria-required="true" aria-invalid="true" required>
                            </div>

                            <div class="form-group">
                                <label for="EmailAddress"><span>*</span> Password</label>
                                <input type="password" class="form-control" name="pass" id="password"
                                       aria-required="true" aria-invalid="true" required>
                            </div>

                            <div class="checkbox">
                                <label>
                                    <input type="checkbox" value="remember-me"> Remember me
                                </label>
                            </div>

                            <button class="btn btn-lg btn-primary btn-block" type="submit">Sign in</button>

                        </form>
                    </div> <!-- /container -->

                </div>

            </div>

        </div>

    </div>

</div>

<footer>
    <img class="image" src="https://giffiles.alphacoders.com/203/2035.gif" alt="advgif" type="img/text">
</footer>

</body>
</html>
