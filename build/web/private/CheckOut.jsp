<%-- 
    Document   : CheckOut
    Created on : 14-Mar-2026, 14:50:33
    Author     : wuann_
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.CartShop"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Checkout</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>

        <style>
            body{
                padding-top: 70px;
                background: #f7f7f7;
                font-family: Bahnschrift, Arial, sans-serif;
            }
            .checkout-wrapper{
                background: #fff;
                padding: 30px;
                margin-top: 30px;
                box-shadow: 0 4px 14px rgba(0,0,0,0.08);
            }
            .checkout-title{
                font-size: 30px;
                font-weight: bold;
                margin-bottom: 25px;
            }
            .summary-box{
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
            .btn-order{
                background: #e53935;
                color: #fff;
                border: none;
                padding: 12px 20px;
                font-weight: bold;
                text-transform: uppercase;
            }
            .btn-order:hover{
                background: #c62828;
                color: #fff;
            }
            .btn-continue:hover{
                background: #757575;
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
    <body>
        <%@include file="/navbar_header/Header.jspf" %>

        <div class="container">
            <div class="checkout-wrapper">
                <div class="checkout-title">CHECKOUT</div>

                <c:if test="${empty sessionScope.cart.items}">
                    <div class="empty-cart">
                        <h3>Your cart is empty</h3>
                        <p>Please add some products to continue shopping.</p>
                        <form action="MainController" method="post" style="margin-top:20px;">
                            <button type="submit" name="btnAction" value="index" class="btn-continue">
                                Continue Shopping
                            </button>
                        </form>
                    </div>
                </c:if>

                <c:if test="${not empty sessionScope.cart.items}">
                    <c:set var="total" value="${0}" />
                    <c:forEach var="i" items="${sessionScope.cart.items.values()}">
                        <c:set var="unitPrice"
                               value="${i.discount > 0 ? i.price * (1 - i.discount / 100.0) : i.price}" />
                        <c:set var="subtotal" value="${unitPrice * i.quantity}" />
                        <c:set var="total" value="${total + subtotal}" />
                    </c:forEach>

                    <div class="row">
                        <div class="col-md-7">
                            <form action="checkOut" method="POST">
                                <div class="form-group">
                                    <label>Full Name</label>
                                    <input type="text" name="fullName" class="form-control" required>
                                </div>

                                <div class="form-group">
                                    <label>Phone Number</label>
                                    <input type="text" name="phone" class="form-control" required>
                                </div>

                                <div class="form-group">
                                    <label>Delivery Address</label>
                                    <textarea name="address" class="form-control" rows="4" required></textarea>
                                </div>

                                <div class="form-group">
                                    <label>Payment Method</label>
                                    <div class="radio">
                                        <label>
                                            <input type="radio" name="paymentMethod" value="COD" checked>
                                            COD SHIPPING
                                        </label>
                                    </div>
                                    <c:if test="${not empty requestScope.error}">
                                        <div class="container alert alert-danger">
                                            <strong style="font-style: Bahnschrift">${requestScope.error}</strong>
                                        </div>
                                    </c:if>
                                </div>
                                <button type="submit" class="btn-order">
                                    CONFIRM ORDER
                                </button>
                            </form>
                        </div>
                        <div class="col-md-5">
                            <div class="summary-box">
                                <div class="summary-title">Order Summary</div>

                                <c:forEach var="i" items="${sessionScope.cart.items.values()}">
                                    <c:set var="unitPrice"
                                           value="${i.discount > 0 ? i.price * (1 - i.discount / 100.0) : i.price}" />
                                    <div class="summary-line">
                                        <span style="max-width: 50%">${i.products.productName} </span>
                                        <span style="font-weight: bold">x${i.quantity}</span>
                                        <span><fmt:formatNumber value="${unitPrice * i.quantity}" type="number"/> VNĐ</span>
                                    </div>
                                </c:forEach>

                                <div class="summary-line summary-total">
                                    <span>Total</span>
                                    <span><fmt:formatNumber value="${total}" type="number"/> VNĐ</span>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:if>
            </div>
        </div>
    </body>
</html>
