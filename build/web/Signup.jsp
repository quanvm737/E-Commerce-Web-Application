<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Sign up</title>
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
            <h1 style="font-family: Bahnschrift">SIGN UP</h1>
        <form class="form-horizontal" action="signup" method="POST">
            <div class="form-group">
                <label class="control-label col-sm-2" style="font-family: Bahnschrift">Account:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" placeholder="Enter account name" name="accountName" value="" required="">
                </div>
            </div>
            <c:if test="${not empty requestScope.err}">
                <div class="container alert alert-danger">
                    <strong>${requestScope.err}</strong>
                </div>
            </c:if>
            <div class="form-group">
                <label class="control-label col-sm-2" style="font-family: Bahnschrift">Password:</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" placeholder="Enter password" name="accountPass" value="" required="">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" style="font-family: Bahnschrift">First name</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" placeholder="Enter first name" name="accountFirstName" value="" required="">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" style="font-family: Bahnschrift">Last name:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" placeholder="Enter last name" name="accountLastName" value="" required="">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" style="font-family: Bahnschrift">Phone number:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" placeholder="Enter phone number" name="accountPhone" value="" required="">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2" style="font-family: Bahnschrift">Birthday:</label>
                <div class="col-sm-10">
                    <input type="date" class="form-control" name="accountBirthday" value="" required="">
                </div>
            </div>
            <div class="form-group">
                <div class="control-label col-sm-2">
                    <label style="font-family: Bahnschrift">Gender:</label>
                </div>
                <div class="col-sm-10">
                    <input type="radio" name="accountGender" value="true" id="male" checked>
                    <label for="male" style="font-family: Bahnschrift">Male</label>
                    <input type="radio" name="accountGender" value="false" id="female">
                    <label for="female" style="font-family: Bahnschrift">Female</label>
                </div>
            </div>
                <input type="hidden" name="accountRoleInSystem" value="3" />
                <input type="hidden" name="accountIsUse" value="true" />
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default" style="font-family: Bahnschrift">Submit</button>
                </div>
            </div>
        </form>
        </div>
    </body>
</html>
