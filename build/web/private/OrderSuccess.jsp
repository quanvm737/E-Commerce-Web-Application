<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Success</title>

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

            .success-wrapper {
                background: #fff;
                margin: 45px auto;
                padding: 35px 30px 40px;
                max-width: 1100px;
                box-shadow: 0 2px 12px rgba(0,0,0,0.06);
            }

            .page-title {
                font-size: 24px;
                font-weight: 800;
                text-transform: uppercase;
                margin-bottom: 30px;
                color: #222;
            }

            .success-box {
                border: 1px solid #e5e5e5;
                padding: 30px 25px;
                min-height: 320px;
            }

            .success-icon{
                width: 90px;
                height: 90px;
                background: white;
                color: red;
                display: flex;
                align-items: center;
                justify-content: center;
                font-size: 60px;
                margin-bottom: 25px;
            }

            .success-heading {
                font-size: 28px;
                font-weight: 800;
                text-transform: uppercase;
                margin-bottom: 12px;
                color: #222;
            }

            .success-text {
                font-size: 15px;
                color: #666;
                line-height: 1.8;
                margin-bottom: 25px;
            }

            .info-line {
                font-size: 15px;
                margin-bottom: 10px;
            }

            .info-line strong {
                display: inline-block;
                min-width: 130px;
                color: #222;
            }

            .summary-box {
                border: 1px solid #e5e5e5;
                background: #fafafa;
                padding: 24px 20px;
            }

            .summary-title {
                font-size: 18px;
                font-weight: 800;
                margin-bottom: 20px;
                color: #222;
            }

            .summary-row {
                display: flex;
                justify-content: space-between;
                align-items: flex-start;
                margin-bottom: 14px;
                font-size: 15px;
                color: #444;
            }

            .summary-row.total {
                border-top: 1px solid #ddd;
                margin-top: 15px;
                padding-top: 18px;
                font-size: 18px;
                font-weight: 800;
                color: #ef3b33;
            }

            .btn-custom-red {
                background: #ef3b33;
                border: none;
                color: white;
                font-weight: 700;
                text-transform: uppercase;
                padding: 12px 24px;
                min-width: 190px;
            }

            .btn-custom-red:hover,
            .btn-custom-red:focus {
                background: #d9332c;
                color: white;
            }

            .btn-custom-gray {
                background: #9e9e9e;
                border: none;
                color: white;
                font-weight: 700;
                text-transform: uppercase;
                padding: 12px 24px;
                min-width: 190px;
            }

            .btn-custom-gray:hover,
            .btn-custom-gray:focus {
                background: #8a8a8a;
                color: white;
            }

            .action-group {
                margin-top: 30px;
            }

            .action-group .btn {
                margin-right: 10px;
                margin-bottom: 10px;
            }

            @media (max-width: 991px) {
                .summary-box {
                    margin-top: 25px;
                }
            }
        </style>
    </head>
    <body>
        <%@include file="/navbar_header/Header.jspf" %>
        <div class="container">
            <div class="success-wrapper">
                <div class="row">
                    <div class="col-md-7">
                        <div class="success-box">
                            <div class="success-icon">
                                <i class="fa fa-check"></i>
                            </div>
                            <div class="success-heading">Order placed successfully!</div>
                            <div class="success-text">
                                Thank you for your purchase. Your order has been received and is now being processed.
                                Our staff will contact you soon to confirm delivery information.
                            </div>
                            <div class="info-line">
                                <strong>Customer:</strong> ${requestScope.order.custName}
                            </div>
                            <div class="info-line">
                                <strong>Phone:</strong> ${requestScope.order.custPhone}
                            </div>
                            <div class="info-line">
                                <strong>Address:</strong> ${requestScope.order.custAddr}
                            </div>
                            <div class="info-line">
                                <strong>Payment:</strong> ${requestScope.order.paymentMethods}
                            </div>
                            <div class="info-line">
                                <strong>Status:</strong> ${requestScope.order.orderState}
                            </div>

                            <div class="action-group">
                                <a href="MainController" class="btn btn-custom-gray">
                                    Continue Shopping
                                </a>
                                <a href="MainController" class="btn btn-custom-red">
                                    Back to Home
                                </a>
                            </div>
                        </div>
                    </div>

                    <div class="col-md-5">
                        <div class="summary-box">
                            <div class="summary-title">Order Summary</div>

                            <div class="summary-row">
                                <span>Order ID</span>
                                <span>${requestScope.order.orderId}</span>
                            </div>

                            <div class="summary-row">
                                <span>Order Date</span>
                                <span>
                                    <fmt:formatDate value="${requestScope.order.deliveredDate}" pattern="dd/MM/yyyy HH:mm"/>
                                </span>
                            </div>

                            <div class="summary-row">
                                <span>Payment Method</span>
                                <span>${requestScope.order.paymentMethods}</span>
                            </div>

                            <div class="summary-row">
                                <span>Order Status</span>
                                <span>${requestScope.order.orderState}</span>
                            </div>

                            <div class="summary-row total">
                                <span>Total</span>
                                <span>
                                    <fmt:formatNumber value="${requestScope.order.totalValue}" type="number"/> VNĐ
                                </span>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </body>
</html>