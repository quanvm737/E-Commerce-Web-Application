<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Intro System</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <style>
            body{
                padding-top:70px;
            }
            .tools{
                display: flex;
                justify-content: space-between;
                align-items: center;
            }
            .table td,
            .table th {
                vertical-align: middle !important;
                text-align: center
            }
            .btn-cart{
                background-color: #e53935;
                color: white;
                border: none;
                padding: 14px 28px;
                font-size: 14px;
                font-weight: bold;
                text-transform: uppercase;
                font-family: Bahnschrift
            }

            .btn-cart:hover{
                background-color: #c62828;
                color: white;
            }
            .price{
                margin-bottom: 10px
            }
            .product-name {
                font-weight: bold;
                font-family: Bahnschrift;
                line-height: 1.4;
                min-height: 50px;
                margin-bottom: 12px;

                display: -webkit-box;
                overflow: hidden;
            }
        </style>
    </head>
    <%@include file="/navbar_header/Header.jspf" %>
    <body>
        <div class="jumbotron">
            <div class="container">
                <h1 style="font-family: Bahnschrift">Welcome to Product shopping site</h1>
                <p style="font-family: Bahnschrift">A place where you can buy anything with the cheapest price and the highest quality</p>
            </div>
        </div>
        <div class="container">
            <div class="tools">
                <div class="dropdown" style="margin:20px 0;">
                    <button class="btn btn-default dropdown-toggle" type="button" data-toggle="dropdown" style="font-family: Bahnschrift">
                        <c:choose>
                            <c:when test="${requestScope.nameListCategory != null}">${requestScope.nameListCategory}</c:when>
                            <c:otherwise>All</c:otherwise>
                        </c:choose>
                        <span class="caret"></span></button>
                    <ul class="dropdown-menu">
                        <c:forEach var="i" items="${requestScope.listCategory}">
                            <li style="font-family: Bahnschrift">
                                <a href="MainController?btnAction=productListByCategory&listByCategoryId=${i.typeId}">${i.categoryName}</a>
                            </li>
                        </c:forEach>
                        <li style="font-family: Bahnschrift">
                            <a href="MainController">All</a>
                        </li>
                    </ul>
                </div>
                <form action="MainController?btnAction=searchProduct" method="get" style="margin:20px 0;">
                    <div class="input-group" style="max-width:400px;">
                        <input 
                            type="text" 
                            name="searchValue" 
                            class="form-control" 
                            placeholder="Search product..."
                            value=""
                            >
                        <span class="input-group-btn">
                            <button class="btn btn-default" type="submit" name="btnAction" value="searchProduct" style="font-family: Bahnschrift">
                                Search
                            </button>
                        </span>
                    </div>
                </form>
            </div>

            <div style="padding-top: 30px"></div>
            <div class="col-md-2">

                <div class="panel panel-default">
                    <div class="panel-heading" style="font-weight:bold; font-family: Bahnschrift">
                        RECENTLY VIEWED
                    </div>
                    <div class="panel-body">
                        <c:choose>
                            <c:when test="${not empty requestScope.viewedProductList}">
                                <c:forEach var="p" items="${requestScope.viewedProductList}">
                                    <div style="margin-bottom:15px; border-bottom:1px solid #eee; padding-bottom:10px">
                                        <a href="MainController?btnAction=detailProduct&productId=${p.productId}">
                                            <img src=".${p.productImage}"
                                                 style="width:100%; height:120px; object-fit:cover">
                                        </a>
                                        <p style="font-weight:bold; margin-top:5px; font-family: Bahnschrift">
                                            ${p.productName}
                                        </p>
                                        <p style="color:red; font-family: Bahnschrift">
                                            <fmt:formatNumber value="${p.price}" type="number"/> VNĐ
                                        </p>
                                    </div>
                                </c:forEach>
                            </c:when>
                            <c:otherwise>
                                <p style="font-family: Bahnschrift">No viewed products</p>
                            </c:otherwise>
                        </c:choose>
                    </div>
                </div>
            </div>
            <div class="col-md-10">
                <c:choose>
                    <c:when test="${not empty requestScope.listProduct}">
                        <c:forEach var="i" items="${requestScope.listProduct}">
                            <div class="col-sm-6 col-md-4">
                                <div class="panel-group">
                                    <div class="panel panel-default">
                                        <div class="panel-heading" style="font-weight: bold; text-align: center"><a href="MainController?btnAction=detailProduct&productId=${i.productId}"><img src=".${i.productImage}" alt="${i.productName}" style="width: 100%; height: 100%; display: block;object-fit: contain;"/></a></div>
                                        <div class="panel-body">
                                            <p class="product-name">${i.productName.toUpperCase()}</p>
                                            <c:choose>
                                                <c:when test="${i.discount > 0}">
                                                    <p class="price">
                                                        <span style="text-decoration: line-through;color: gray;font-family: Bahnschrift"><fmt:formatNumber value="${i.price}" type="number"/> VNĐ</span>
                                                        <span style="color: red; font-weight: bold;font-family: Bahnschrift"><fmt:formatNumber value="${i.price*(1 - i.discount/100)}" type="number"/> VNĐ</span>
                                                    </p>
                                                </c:when>
                                                <c:otherwise>
                                                    <p class="price">
                                                        <span style="font-family: Bahnschrift"><fmt:formatNumber value="${i.price}" type="number"/> VNĐ</span>
                                                    </p>
                                                </c:otherwise>
                                            </c:choose>
                                            <form action="MainController" method="GET" style="display:inline;">
                                                <input type="hidden" name="btnAction" value="addToCart">
                                                <input type="hidden" name="productId" value="${i.productId}">
                                                <button type="submit" class="btn-cart">
                                                    ADD TO CART
                                                </button>
                                            </form>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </c:forEach>
                    </c:when>
                    <c:otherwise>
                        <h2 style="font-family: Bahnschrift; color: red">NO RESULT FOUND!!</h2>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>    
    </body>
</html>
