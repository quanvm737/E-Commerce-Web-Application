<%-- 
    Document   : ViewCart
    Created on : 13-Mar-2026, 14:27:00
    Author     : wuann_
--%>
<%@page import="java.util.Map"%>
<%@page import="model.CartShop"%>
<%@page import="model.OrderDetails"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Your Cart</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

        <style>
            body{
                padding-top: 70px;
                background-color: #f7f7f7;
                font-family: Bahnschrift, Arial, sans-serif;
            }

            .cart-wrapper{
                background: #fff;
                padding: 30px;
                box-shadow: 0 4px 14px rgba(0,0,0,0.08);
                margin-top: 30px;
            }

            .cart-title{
                font-size: 32px;
                font-weight: bold;
                margin-bottom: 25px;
                color: #222;
            }

            .table > thead > tr > th,
            .table > tbody > tr > td{
                vertical-align: middle !important;
                text-align: center;
            }

            .product-img{
                width: 90px;
                height: 90px;
                object-fit: cover;
                object-position: center;
                border: 1px solid #eee;
            }

            .product-name{
                font-weight: bold;
                color: #333;
            }

            .old-price{
                text-decoration: line-through;
                color: #999;
                display: block;
            }

            .new-price{
                color: #e53935;
                font-weight: bold;
            }

            .normal-price{
                color: #222;
                font-weight: bold;
            }

            .qty-box{
                display: flex;
                justify-content: center;
                align-items: center;
                gap: 8px;
            }

            .qty-btn{
                border: none;
                background: #eee;
                width: 32px;
                height: 32px;
                font-weight: bold;
            }

            .qty-btn:hover{
                background: #ddd;
            }

            .qty-number{
                min-width: 30px;
                display: inline-block;
                font-weight: bold;
            }

            .btn-remove{
                background: #d9534f;
                color: white;
                border: none;
                padding: 8px 14px;
            }

            .btn-remove:hover{
                background: #c9302c;
                color: white;
            }

            .summary-box{
                margin-top: 25px;
                background: #fafafa;
                border: 1px solid #eee;
                padding: 20px;
            }

            .summary-title{
                font-size: 22px;
                font-weight: bold;
                margin-bottom: 15px;
            }

            .summary-line{
                display: flex;
                justify-content: space-between;
                margin-bottom: 10px;
                font-size: 16px;
            }

            .summary-total{
                font-size: 22px;
                font-weight: bold;
                color: #e53935;
                border-top: 1px solid #ddd;
                padding-top: 12px;
                margin-top: 12px;
            }

            .btn-continue{
                background: #9e9e9e;
                color: white;
                border: none;
                padding: 12px 22px;
                font-weight: bold;
                text-transform: uppercase;
            }

            .btn-continue:hover{
                background: #757575;
                color: white;
            }

            .btn-checkout{
                background: #e53935;
                color: white;
                border: none;
                padding: 12px 22px;
                font-weight: bold;
                text-transform: uppercase;
            }

            .btn-checkout:hover{
                background: #c62828;
                color: white;
            }

            .empty-cart{
                text-align: center;
                padding: 50px 20px;
                color: #777;
            }

            .empty-cart h3{
                font-weight: bold;
                margin-bottom: 15px;
            }
        </style>
    </head>

    <%@include file="/navbar_header/Header.jspf" %>

    <body>
        <%@include file="/navbar_header/Header.jspf" %>

        <div class="container">
            <div class="cart-wrapper">
                <div class="cart-title">YOUR CART</div>

                <c:choose>
                    <c:when test="${empty sessionScope.cart.items}">
                        <div class="empty-cart">
                            <h3>Your cart is empty</h3>
                            <p>Please add some products to continue shopping.</p>
                            <form action="MainController" method="post" style="margin-top:20px;">
                                <button type="submit" name="btnAction" value="index" class="btn-continue">
                                    Continue Shopping
                                </button>
                            </form>
                        </div>
                    </c:when>
                    <c:otherwise>
                        <c:set var="total" value="${0}" />
                        <div class="table-responsive">
                            <table class="table table-bordered table-hover">
                                <thead style="background:#f0f0f0;">
                                    <tr>
                                        <th>Image</th>
                                        <th>Product</th>
                                        <th>Price</th>
                                        <th>Quantity</th>
                                        <th>Subtotal</th>
                                        <th>Action</th>
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach var="i" items="${sessionScope.cart.items.values()}">
                                        <c:set var="unitPrice"
                                               value="${i.discount > 0 ? i.price * (1 - i.discount / 100.0) : i.price}" />
                                        <c:set var="subtotal" value="${unitPrice * i.quantity}" />
                                        <c:set var="total" value="${total + subtotal}" />
                                        <tr>
                                            <td>
                                                <a href="MainController?btnAction=detailProduct&productId=${i.products.productId}">
                                                    <img src=".${i.products.productImage}"
                                                         alt="${i.products.productName}"
                                                         class="product-img"/>
                                                </a>
                                            </td>

                                            <td class="product-name">
                                                <a href="MainController?btnAction=detailProduct&productId=${i.products.productId}">
                                                    ${i.products.productName}
                                                </a>
                                            </td>

                                            <td>
                                                <c:choose>
                                                    <c:when test="${i.discount > 0}">
                                                        <span class="old-price">
                                                            <fmt:formatNumber value="${i.price}" type="number"/> VNĐ
                                                        </span>
                                                        <span class="new-price">
                                                            <fmt:formatNumber value="${unitPrice}" type="number"/> VNĐ
                                                        </span>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <span class="normal-price">
                                                            <fmt:formatNumber value="${i.price}" type="number"/> VNĐ
                                                        </span>
                                                    </c:otherwise>
                                                </c:choose>
                                            </td>

                                            <td>
                                                <div class="qty-box">
                                                    <form action="decreaseQuantity" method="post" style="display:inline;">
                                                        <input type="hidden" name="productId" value="${i.products.productId}"/>
                                                        <button type="submit" class="qty-btn">-</button>
                                                    </form>

                                                    <span class="qty-number">${i.quantity}</span>

                                                    <form action="increaseQuantity" method="post" style="display:inline;">
                                                        <input type="hidden" name="productId" value="${i.products.productId}"/>
                                                        <button type="submit" class="qty-btn">+</button>
                                                    </form>
                                                </div>
                                            </td>

                                            <td>
                                                <span class="new-price">
                                                    <fmt:formatNumber value="${subtotal}" type="number"/> VNĐ
                                                </span>
                                            </td>

                                            <td>
                                                <form action="removeFromCart" method="post" style="display:inline;">
                                                    <input type="hidden" name="productId" value="${i.products.productId}"/>
                                                    <button type="submit" class="btn-remove">
                                                        Remove
                                                    </button>
                                                </form>
                                            </td>
                                        </tr>
                                    </c:forEach>
                                </tbody>
                            </table>
                        </div>

                        <div class="row">
                            <div class="col-md-6">
                                <form action="MainController" method="GET">
                                    <button type="submit" name="btnAction" value="index" class="btn-continue">
                                        Continue Shopping
                                    </button>
                                </form>
                            </div>

                            <div class="col-md-6">
                                <div class="summary-box">
                                    <div class="summary-title">Cart Summary</div>

                                    <div class="summary-line">
                                        <span>Total Items</span>
                                        <span>${sessionScope.cart.items.size()}</span>
                                    </div>

                                    <div class="summary-line summary-total">
                                        <span>Total Price</span>
                                        <span>
                                            <fmt:formatNumber value="${total}" type="number"/> VNĐ
                                        </span>
                                    </div>
                                    <form action="checkOut" method="get">
                                        <button type="submit" class="btn-checkout btn-block">
                                            Proceed to Checkout
                                        </button>
                                    </form>

                                </div>
                            </div>
                        </div>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </body>
</html>
