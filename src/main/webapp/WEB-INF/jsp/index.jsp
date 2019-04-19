<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <title>Alita Battle! </title>
    <link rel="stylesheet" href="https://use.fontawesome.com/releases/v5.8.1/css/all.css" integrity="sha384-50oBUHEmvpQ+1lW4y57PTFmhCaXp0ML5d60M1M7uH2+nqUivzIebhndOJK28anvf" crossorigin="anonymous">
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
    <%@include file="../styles/index.css" %>
</style>

<body>

<%@include file="_headerExtra.jsp" %>

<div class="container">

    <div class="headline mt-5">
        <h2 style="text-align:center">You are about to enter the game</h2>
    </div>

    <div class="text mt-5">
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad cumque, dolor eius harum illum magnam maxime
            nemo nobis obcaecati officia omnis recusandae sunt ullam ut veritatis! Fugiat fugit in quis.
        </p>
    </div>
    <p style="color: red">${AccessDenied}</p>
    <div class="buttons mt-5" style="text-align:center">
        <a href="" class="btn btn-primary mr-2" id="signIn" data-toggle="modal" data-target="#modalLoginForm"><span>Sign In</span></a>
        <a href="register" class="btn btn-secondary ml-2" id="signUp" data-target="#modalRegForm"><span>Sign Up</span></a>
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

                        <form role="form" action="AlitaBattle" method="post" class="form-signin text-left">

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

                            <div class="d-flex justify-content-start">
                            <button class="btn btn-lg btn-primary" id="popUpBtn" type="submit"><span>Sign in</span></button>
                            </div>

                        </form>
                    </div> <!-- /container -->

                </div>

            </div>

        </div>

    </div>

</div>

<footer>
    <img class="img-fluid" src="https://giffiles.alphacoders.com/203/2035.gif" alt="advgif" type="img/text">
</footer>

</body>
</html>
