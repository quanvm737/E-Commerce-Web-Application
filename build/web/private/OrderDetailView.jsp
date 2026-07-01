<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Detail - #${requestScope.orderDetail.orderId}</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
        <style>
            body {
                background: #f5f5f5;
                font-family: Bahnschrift, Arial, sans-serif;
                padding-top: 70px;
                color: #333;
            }
            .detail-wrapper {
                background: #fff;
                margin: 30px auto;
                padding: 35px 30px 40px;
                max-width: 1100px;
                box-shadow: 0 2px 12px rgba(0,0,0,0.06);
            }
            .page-title {
                font-size: 24px;
                font-weight: 800;
                text-transform: uppercase;
                margin-bottom: 25px;
                color: #222;
            }
            .info-section {
                border: 1px solid #e5e5e5;
                padding: 25px 20px;
                margin-bottom: 25px;
            }
            .info-section h3 {
                font-size: 18px;
                font-weight: 800;
                margin-bottom: 18px;
                color: #222;
                border-bottom: 2px solid #ef3b33;
                padding-bottom: 8px;
            }
            .info-line {
                font-size: 15px;
                margin-bottom: 10px;
            }
            .info-line strong {
                display: inline-block;
                min-width: 150px;
                color: #222;
            }
            .table td,
            .table th {
                vertical-align: middle !important;
                text-align: center;
            }
            .total-row {
                font-size: 18px;
                font-weight: 800;
                color: #ef3b33;
            }
            .btn-back {
                background: #9e9e9e;
                border: none;
                color: white;
                font-weight: 700;
                text-transform: uppercase;
                padding: 10px 24px;
            }
            .btn-back:hover,
            .btn-back:focus {
                background: #8a8a8a;
                color: white;
            }
            .product-img {
                width: 60px;
                height: 60px;
                object-fit: cover;
            }
        </style>
    </head>
    <body>
        <%@include file="/navbar_header/Header.jspf" %>
        <div class="container">
            <div class="detail-wrapper">
                <div class="page-title">
                    <i class="fa fa-file-text-o"></i> Order Detail #${requestScope.orderDetail.orderId}
                </div>

                <div class="row">
                    <div class="col-md-6">
                        <div class="info-section">
                            <h3><i class="fa fa-user"></i> Customer Information</h3>
                            <div class="info-line">
                                <strong>Customer Name:</strong> ${requestScope.orderDetail.custName}
                            </div>
                            <div class="info-line">
                                <strong>Phone:</strong> ${requestScope.orderDetail.custPhone}
                            </div>
                            <div class="info-line">
                                <strong>Address:</strong> ${requestScope.orderDetail.custAddr}
                            </div>
                        </div>
                    </div>
                    <div class="col-md-6">
                        <div class="info-section">
                            <h3><i class="fa fa-shopping-bag"></i> Order Information</h3>
                            <div class="info-line">
                                <strong>Order ID:</strong> ${requestScope.orderDetail.orderId}
                            </div>
                            <div class="info-line">
                                <strong>Order Date:</strong>
                                <fmt:formatDate value="${requestScope.orderDetail.deliveredDate}" pattern="dd/MM/yyyy HH:mm"/>
                            </div>
                            <div class="info-line">
                                <strong>Payment Method:</strong> ${requestScope.orderDetail.paymentMethods}
                            </div>
                            <div class="info-line">
                                <strong>Status:</strong>
                                <c:choose>
                                    <c:when test="${requestScope.orderDetail.orderState == 'PENDING'}">
                                        <span class="label label-danger">PENDING</span>
                                    </c:when>
                                    <c:when test="${requestScope.orderDetail.orderState == 'CONFIRMED'}">
                                        <span class="label label-warning">CONFIRMED</span>
                                    </c:when>
                                    <c:when test="${requestScope.orderDetail.orderState == 'SHIPPING'}">
                                        <span class="label label-info">SHIPPING</span>
                                    </c:when>
                                    <c:when test="${requestScope.orderDetail.orderState == 'DELIVERED'}">
                                        <span class="label label-success">DELIVERED</span>
                                    </c:when>
                                    <c:when test="${requestScope.orderDetail.orderState == 'CANCELLED'}">
                                        <span class="label label-default">CANCELLED</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="label label-default">${requestScope.orderDetail.orderState}</span>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </div>
                    </div>
                </div>

                <div class="info-section">
                    <h3><i class="fa fa-list"></i> Order Items</h3>
                    <table class="table table-striped table-bordered">
                        <thead>
                            <tr style="background-color: cadetblue">
                                <th style="width:5%">#</th>
                                <th style="width:10%">Image</th>
                                <th style="width:15%">Product ID</th>
                                <th style="width:25%">Product Name</th>
                                <th style="width:10%">Price</th>
                                <th style="width:10%">Discount</th>
                                <th style="width:10%">Quantity</th>
                                <th style="width:15%">Subtotal</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:set var="index" value="0"/>
                            <c:forEach var="item" items="${requestScope.orderDetail.orderDetailsList}">
                                <c:set var="index" value="${index + 1}"/>
                                <tr>
                                    <td>${index}</td>
                                    <td>
                                        <c:if test="${not empty item.products.productImage}">
                                            <img src="${item.products.productImage}" alt="${item.products.productName}" class="product-img"/>
                                        </c:if>
                                        <c:if test="${empty item.products.productImage}">
                                            <span class="glyphicon glyphicon-picture" style="font-size:30px;color:#ccc"></span>
                                        </c:if>
                                    </td>
                                    <td>${item.orderDetailsPK.productId}</td>
                                    <td style="text-align:left">${item.products.productName}</td>
                                    <td><fmt:formatNumber value="${item.price}" type="number"/> VNĐ</td>
                                    <td>${item.discount}%</td>
                                    <td>${item.quantity}</td>
                                    <td>
                                        <strong><fmt:formatNumber value="${item.price * item.quantity}" type="number"/> VNĐ</strong>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                        <tfoot>
                            <tr>
                                <td colspan="7" style="text-align:right">
                                    <span class="total-row">TOTAL:</span>
                                </td>
                                <td>
                                    <span class="total-row">
                                        <fmt:formatNumber value="${requestScope.orderDetail.totalValue}" type="number"/> VNĐ
                                    </span>
                                </td>
                            </tr>
                        </tfoot>
                    </table>
                </div>

                <div style="margin-top: 20px;">
                    <a href="listOrder" class="btn btn-back">
                        <i class="fa fa-arrow-left"></i> Back to Order List
                    </a>
                    <a href="listOrder?btnOrderAction=UpdateStatus&orderId=${requestScope.orderDetail.orderId}" class="btn btn-primary">
                        <i class="fa fa-edit"></i> Edit Order
                    </a>
                </div>
            </div>
        </div>
    </body>
</html>
