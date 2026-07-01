<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Order Manager</title>
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
            .status-pending {
                color: #d9534f;
                font-weight: bold;
            }
            .status-confirmed {
                color: #f0ad4e;
                font-weight: bold;
            }
            .status-shipping {
                color: #5bc0de;
                font-weight: bold;
            }
            .status-delivered {
                color: #5cb85c;
                font-weight: bold;
            }
            .status-cancelled {
                color: #777;
                font-weight: bold;
                text-decoration: line-through;
            }
        </style>
    </head>
    <%@include file="/navbar_header/Header.jspf" %>
    <body>
        <div class="container">
            <h1>List of all orders in the system</h1>
            <br/>
            <table class="table table-striped">
                <tr style="background-color: cadetblue" class="text-center">
                    <th style="width:7%">Order ID</th>
                    <th style="width:15%">Customer Name</th>
                    <th style="width:10%">Phone</th>
                    <th style="width:18%">Address</th>
                    <th style="width:12%">Order Date</th>
                    <th style="width:10%">Total Value</th>
                    <th style="width:8%">Payment</th>
                    <th style="width:8%">Status</th>
                    <th style="width:12%">Action</th>
                </tr>
                <c:forEach var="i" items="${requestScope.listOrder}">
                    <tr>
                        <td>${i.orderId}</td>
                        <td>${i.custName}</td>
                        <td>${i.custPhone}</td>
                        <td>${i.custAddr}</td>
                        <td><fmt:formatDate value='${i.deliveredDate}' pattern='dd-MM-yyyy HH:mm'/></td>
                        <td><fmt:formatNumber value="${i.totalValue}" type="number"/> VNĐ</td>
                        <td>${i.paymentMethods}</td>
                        <td>
                            <c:choose>
                                <c:when test="${i.orderState == 'PENDING'}">
                                    <span class="label label-danger">PENDING</span>
                                </c:when>
                                <c:when test="${i.orderState == 'CONFIRMED'}">
                                    <span class="label label-warning">CONFIRMED</span>
                                </c:when>
                                <c:when test="${i.orderState == 'SHIPPING'}">
                                    <span class="label label-info">SHIPPING</span>
                                </c:when>
                                <c:when test="${i.orderState == 'DELIVERED'}">
                                    <span class="label label-success">DELIVERED</span>
                                </c:when>
                                <c:when test="${i.orderState == 'CANCELLED'}">
                                    <span class="label label-default">CANCELLED</span>
                                </c:when>
                                <c:otherwise>
                                    <span class="label label-default">${i.orderState}</span>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <a href="listOrder?btnOrderAction=ViewDetail&orderId=${i.orderId}" class="btn btn-info btn-xs">
                                <span class="glyphicon glyphicon-eye-open"></span> VIEW
                            </a>
                            <a href="listOrder?btnOrderAction=UpdateStatus&orderId=${i.orderId}" class="btn btn-primary btn-xs">
                                <span class="glyphicon glyphicon-edit"></span> EDIT
                            </a>
                            <a href="listOrder?btnOrderAction=Delete&orderId=${i.orderId}" class="btn btn-danger btn-xs"
                               onclick="return confirm('Are you sure you want to delete this order? This action cannot be undone.')">
                                <span class="glyphicon glyphicon-trash"></span> DELETE
                            </a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <c:if test="${empty requestScope.listOrder}">
                <div class="alert alert-info text-center">
                    <strong>No orders found.</strong> There are no orders in the system yet.
                </div>
            </c:if>
        </div>
    </body>
</html>
