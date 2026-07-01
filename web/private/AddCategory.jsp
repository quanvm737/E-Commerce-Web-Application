<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>New category</title>
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
            <h1>New Category</h1>
            <form class="form-horizontal" action="addCategory" method="POST">
                <div class="form-group">
                    <label class="control-label col-sm-2">Category name:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" placeholder="New type of category" name="categoryName" value="" required="">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2">Memo:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" placeholder="Category for what...?" name="categoryMemo" value="">
                    </div>
                </div>
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">SAVE</button>
                </div>
            </form>
        </div>
    </body>
</html>
