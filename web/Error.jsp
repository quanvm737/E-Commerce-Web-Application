<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error!</title>
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
            <h2 style="color: red; font-family: Bahnschrift">An unexpected error occurred! Please try again or get help from the Administrator</h2>
        </div>
    </body>
</html>
