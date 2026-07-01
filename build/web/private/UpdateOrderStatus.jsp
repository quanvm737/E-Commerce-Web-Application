<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Update Order Status</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <style>
            body {
                padding-top: 70px;
            }
            .form-wrapper {
                max-width: 700px;
                margin: 30px auto;
                background: #fff;
                padding: 35px 30px;
                box-shadow: 0 2px 12px rgba(0,0,0,0.06);
            }
            .form-title {
                font-size: 22px;
                font-weight: 800;
                text-transform: uppercase;
                margin-bottom: 25px;
                color: #222;
                font-family: Bahnschrift, Arial, sans-serif;
            }
        </style>
    </head>
    <%@include file="/navbar_header/Header.jspf" %>
    <body>
        <div class="container">
            <div class="form-wrapper">
                <div class="form-title">
                    <span class="glyphicon glyphicon-edit"></span> Update Order #${requestScope.updateOrder.orderId}
                </div>

                <form action="OrderUpdateStatus" method="POST">
                    <input type="hidden" name="orderId" value="${requestScope.updateOrder.orderId}"/>

                    <div class="form-group">
                        <label for="custName">Customer Name</label>
                        <input type="text" class="form-control" id="custName" name="custName"
                               value="${requestScope.updateOrder.custName}" required/>
                    </div>

                    <div class="form-group">
                        <label for="custPhone">Phone</label>
                        <input type="text" class="form-control" id="custPhone" name="custPhone"
                               value="${requestScope.updateOrder.custPhone}" required/>
                    </div>

                    <div class="form-group">
                        <label for="custAddr">Address</label>
                        <textarea class="form-control" id="custAddr" name="custAddr"
                                  rows="3" required>${requestScope.updateOrder.custAddr}</textarea>
                    </div>

                    <div class="form-group">
                        <label for="orderState">Order Status</label>
                        <select class="form-control" id="orderState" name="orderState" required>
                            <option value="PENDING" ${requestScope.updateOrder.orderState == 'PENDING' ? 'selected' : ''}>
                                PENDING
                            </option>
                            <option value="CONFIRMED" ${requestScope.updateOrder.orderState == 'CONFIRMED' ? 'selected' : ''}>
                                CONFIRMED
                            </option>
                            <option value="SHIPPING" ${requestScope.updateOrder.orderState == 'SHIPPING' ? 'selected' : ''}>
                                SHIPPING
                            </option>
                            <option value="DELIVERED" ${requestScope.updateOrder.orderState == 'DELIVERED' ? 'selected' : ''}>
                                DELIVERED
                            </option>
                            <option value="CANCELLED" ${requestScope.updateOrder.orderState == 'CANCELLED' ? 'selected' : ''}>
                                CANCELLED
                            </option>
                        </select>
                    </div>

                    <div class="form-group">
                        <label>Payment Method</label>
                        <input type="text" class="form-control" value="${requestScope.updateOrder.paymentMethods}" disabled/>
                    </div>

                    <div class="form-group">
                        <label>Total Value</label>
                        <input type="text" class="form-control" 
                               value="<fmt:formatNumber value='${requestScope.updateOrder.totalValue}' type='number'/> VNĐ" disabled/>
                    </div>

                    <div class="form-group">
                        <label>Order Date</label>
                        <input type="text" class="form-control"
                               value="<fmt:formatDate value='${requestScope.updateOrder.deliveredDate}' pattern='dd/MM/yyyy HH:mm'/>" disabled/>
                    </div>

                    <hr/>
                    <button type="submit" class="btn btn-primary">
                        <span class="glyphicon glyphicon-floppy-disk"></span> Save Changes
                    </button>
                    <a href="listOrder" class="btn btn-default">
                        <span class="glyphicon glyphicon-arrow-left"></span> Cancel
                    </a>
                </form>
            </div>
        </div>
    </body>
</html>
