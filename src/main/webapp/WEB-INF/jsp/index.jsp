<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Hello World: </title>
    <%--<link href="main.css" rel="stylesheet" type="text/css">--%>
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css" integrity="sha384-BVYiiSIFeK1dGmJRAkycuHAHRg32OmUcww7on3RYdg4Va+PmSTsz/K68vbdEjh4u" crossorigin="anonymous">
</head>
<style><%@include file="../styles/main.css"%></style>
<body>
<header>
    <nav class="navbar navbar-dark bg-dark">
        <a class="navbar-brand" href="#">
        <span class="glyphicon glyphicon-qrcode" width="30" height="30" aria-hidden="true"></span>
        </a>
    <ul class="nav justify-content-end">
        <li class="nav-item">
            <a class="nav-link active" href="#">Alita Battle</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">About</a>
        </li>
        <li class="nav-item">
            <a class="nav-link" href="#">Contact</a>
        </li>
    </ul>
    </nav>
</header>

<div class="container">

    <div class="headline">
        <h2>You are about to enter the game</h2>
    </div>

    <div class="text">
        <p>Lorem ipsum dolor sit amet, consectetur adipisicing elit. Ad cumque, dolor eius harum illum magnam maxime
            nemo nobis obcaecati officia omnis recusandae sunt ullam ut veritatis! Fugiat fugit in quis.</p>
    </div>

    <div class="buttons">
        <div class="btn btn-primary"><span>Register</span></div>

        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span aria-hidden="true">×</span></button>
                    <h4 class="modal-title">Registration</h4>
                </div>
                <div class="modal-body">
                    <form>
                        <div class="row">
                            <div class="col-xs-12 col-sm-6">
                                <div class="form-group">
                                    <input class="form-control" type="text" name="name" value="" placeholder="Name">
                                </div>
                                <div class="form-group">
                                    <input class="form-control" type="text" name="surname" value="" placeholder="Second Name">
                                </div>
                                <div class="form-group">
                                    <input class="form-control" type="phone" name="phone" value="" placeholder="Contact Number">
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-6">
                                <div class="form-group">
                                    <input class="form-control" type="email" name="email" value="" placeholder="Email-address">
                                </div>
                            </div>
                        </div>
                    </form>
                    <div class="saved-message text-center">
                        <h2>Registration form has been successfully submitted!</h2>
                    </div>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default" data-dismiss="modal">Close</button>
                    <button type="button" class="btn btn-success btn-save-contact">Continue</button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>


        <div class="btn btn-secondary"><span>Log In</span></div>
    </div>

</div>

<footer>

    <img src="https://giffiles.alphacoders.com/203/2035.gif" type="video/mp4">

</footer>

<%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/1.12.4/jquery.min.js"></script>--%>
</body>
</html>
