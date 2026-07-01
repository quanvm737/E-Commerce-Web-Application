<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Product Details</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <style>
            body{
                padding-top: 70px;
                background-color: #f8f8f8;
                font-family: Bahnschrift, Arial, sans-serif;
            }

            .product-detail-box{
                background: #fff;
                padding: 30px;
                box-shadow: 0 4px 15px rgba(0,0,0,0.08);
                margin-top: 30px;
            }

            .product-image{
                width: 100%;
                height: 420px;
                object-fit: cover;
                object-position: center;
                border: 1px solid #eee;
            }

            .product-title{
                font-size: 30px;
                font-weight: bold;
                margin-bottom: 15px;
                color: #222;
            }

            .product-brief{
                font-size: 16px;
                color: #666;
                line-height: 1.8;
                margin-bottom: 25px;
            }

            .price-box{
                margin-bottom: 25px;
            }

            .old-price{
                text-decoration: line-through;
                color: #999;
                font-size: 20px;
                margin-right: 12px;
            }

            .new-price{
                color: #e53935;
                font-size: 30px;
                font-weight: bold;
            }

            .normal-price{
                color: #222;
                font-size: 30px;
                font-weight: bold;
            }

            .btn-cart{
                background-color: #e53935;
                color: white;
                border: none;
                padding: 14px 28px;
                font-size: 16px;
                font-weight: bold;
                text-transform: uppercase;
            }

            .btn-cart:hover{
                background-color: #c62828;
                color: white;
            }
            
            .btn-back{
                background-color: #bdbdbd;
                color: white;
                border: none;
                padding: 10px 22px;
                font-size: 16px;
                font-weight: bold;
                text-transform: uppercase;
            }

            .btn-back:hover{
                background-color: #757575;
                color: white;
            }
            
            .meta-info{
                margin-top: 20px;
                color: #777;
                font-size: 14px;
            }
        </style>
    </head>
    <%@include file="/navbar_header/Header.jspf" %>
    <body>
        <div class="container">
            <form action="MainController" method="post">
                <button type="submit" name="btnAction" value="index" class="btn-back">
                    &lt; back
                </button>
            </form>
            <div class="product-detail-box">
                <div class="row">
                    <!-- LEFT: IMAGE -->
                    <div class="col-md-5">
                        <img src=".${requestScope.product.productImage}" 
                             alt="${requestScope.product.productName}" 
                             class="product-image"/>
                    </div>

                    <!-- RIGHT: INFO -->
                    <div class="col-md-7">
                        <div class="product-title">
                            ${requestScope.product.productName}
                        </div>

                        <div class="product-brief">
                            ${requestScope.product.brief}
                        </div>

                        <div class="price-box">
                            <c:choose>
                                <c:when test="${requestScope.product.discount > 0}">
                                    <span class="old-price">
                                        <fmt:formatNumber value="${requestScope.product.price}" type="number"/> VNĐ
                                    </span>
                                    <span class="new-price">
                                        <fmt:formatNumber value="${requestScope.product.price - (requestScope.product.price * requestScope.product.discount / 100)}" type="number"/> VNĐ
                                    </span>
                                </c:when>
                                <c:otherwise>
                                    <span class="normal-price">
                                        <fmt:formatNumber value="${requestScope.product.price}" type="number"/> VNĐ
                                    </span>
                                </c:otherwise>
                            </c:choose>
                        </div>

                        <form action="MainController" method="GET">
                            <input type="hidden" name="productId" value="${requestScope.product.productId}" />
                            <input type="hidden" name="btnAction" value="addToCart" />
                            <button type="submit" class="btn-cart">
                                ADD TO CART
                            </button>
                        </form>

                        <div class="meta-info">
                            <p><strong>Unit:</strong> ${requestScope.product.unit}</p>
                            <p><strong>Category:</strong> ${requestScope.product.typeId.categoryName}</p>
                            <p><strong>Posted Date:</strong> <fmt:formatDate value='${requestScope.product.postedDate}' pattern='dd-MM-yyyy'/></p>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </body>
</html>
