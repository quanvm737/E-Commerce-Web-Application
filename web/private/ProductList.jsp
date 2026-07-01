<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

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
            .table td,
            .table th {
                vertical-align: middle !important;
                text-align: center
            }
        </style>
    </head>

    <%@include file="/navbar_header/Header.jspf" %>
    <body>
        <div class="container">
            <h1>List all product in the system</h1>
            <br/>
            <table class="table table-striped">

                <tr style="background-color: cadetblue; font-weight: bold">
                    <td>PRODUCT ID</td>
                    <td>PRODUCT IMAGE</td>
                    <td>PRODUCT NAME</td>
                    <td>CATEGORY</td>
                    <td>POSTED DATE</td>
                    <td>PRICE (VND)</td>

                    <td>ACTION</td>
                </tr>
                <c:forEach var="i" items="${requestScope.listProduct}">
                    <tr>
                        <td>${i.productId}</td>
                        <td><img src=".${i.productImage}" alt="${i.productName}" style="height:90px; width:90px; object-fit: cover; object-position: center"/></td>
                        <td>${i.typeId.categoryName}</td>
                        <td style="font-weight: bold">${i.productName}</td>
                        <td><fmt:formatDate value='${i.postedDate}' pattern='dd-MM-yyyy'/></td>
                        <td>${i.price}</td>
                        <td>
                            <a href="listProduct?btnProductAction=Update&productId=${i.productId}" class="btn btn-primary">UPDATE</a>
                            <a href="listProduct?btnProductAction=Delete&productId=${i.productId}" class="btn btn-danger">DELETE</a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
