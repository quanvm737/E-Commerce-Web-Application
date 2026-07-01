<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
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
            <h1>Update Category</h1>
            <form class="form-horizontal" action="CategoryUpdate" method="POST">
                <div class="form-group">
                    <label class="control-label col-sm-2">Category name:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" placeholder="New type of category" name="categoryName" value="${requestScope.updateInfo.categoryName}" required="">
                    </div>
                </div>
                <div class="form-group">
                    <label class="control-label col-sm-2">Memo:</label>
                    <div class="col-sm-10">
                        <input type="text" class="form-control" placeholder="Category for what...?" name="categoryMemo" value="${requestScope.updateInfo.memo}">
                    </div>
                </div>
                <input type="hidden" name="typeid" value="${requestScope.updateInfo.typeId}" />
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">SAVE</button>
                </div>
            </form>
        </div>
    </body>
</html>
