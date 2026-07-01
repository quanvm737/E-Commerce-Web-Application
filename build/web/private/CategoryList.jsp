<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Category List</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <style>
            body{
                padding-top: 70px;
            }
            .table td,
            .table th {
                vertical-align: middle !important;
                text-align: center;
                
            }
        </style>
    </head>
    <%@include file="/navbar_header/Header.jspf" %>
    <body>
        <div class="container">
        <h1>List of all categories in the system</h1>
        <br/>
        <table class="table table-striped">
            <tr style="background-color: cadetblue;font-weight: bold">
                <td>TYPE ID</td>
                <td>CATEGORY NAME</td>
                <td>MEMO</td>
                <td>ACTION</td>
            </tr>
            <c:forEach var="i" items="${requestScope.listCategory}">
                <tr>
                    <td>${i.typeId}</td>
                    <td>${i.categoryName}</td>
                    <td>${i.memo}</td>
                    <td>
                        <a href="listCategory?btnCategoryAction=Update&typeid=${i.typeId}" class="btn btn-primary">UPDATE</a>
                        <a href="listCategory?btnCategoryAction=Delete&typeid=${i.typeId}" class="btn btn-danger"
                           onclick="return confirm('Are you sure do you want to delete this category? All the products belong to this will be deleted')">DELETE</a>
                        <a href="listCategory?btnCategoryAction=ProductList&typeid=${i.typeId}" class="btn btn-primary">ALL PRODUCTS</a>   
                    </td>
                </tr>
            </c:forEach>
        </table>
        </div>
    </body>
</html>
