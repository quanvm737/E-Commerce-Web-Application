<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Login</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <style>
            body{
                padding-top: 70px;
            }
        </style>
    </head>
    <%@include file="/navbar_header/Header.jspf" %>
    <body>
        <div class="container">
            <form class="form-horizontal" action="login" method="POST">
                <h1 style="font-family: Bahnschrift">LOGIN</h1>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="username" style="font-family: Bahnschrift">Username:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" id="username" placeholder="Enter username" name="accountName" value="" required="">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2" for="password" style="font-family: Bahnschrift">Password:</label>
                    <div class="col-sm-10">
                        <input type="password" class="form-control" id="password" placeholder="Enter password" name="accountPass" value="" required="">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-offset-2 col-sm-10">
                        <button type="submit" class="btn btn-default" style="font-family: Bahnschrift">LOGIN</button>
                    </div>
                </div>
                <c:if test="${not empty requestScope.err}">
                    <div class="container alert alert-danger">
                        <strong>${requestScope.err}</strong>
                    </div>
                </c:if>
            </form>
        </div>
    </body>
</html>
