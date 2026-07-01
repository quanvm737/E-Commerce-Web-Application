<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Add Product</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <style>
        body{
            padding-top: 70px;
        }
    </style>
    </head>
    <%@include file="/navbar_header/Header.jspf" %>
    <body>
        <div class="container">
        <h1>Add new product</h1>
        <form class="form-horizontal" action="addProduct" method="POST">
            <div class="form-group">
                <label class="control-label col-sm-2">Product ID:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" placeholder="Enter product ID" name="productId" value="" required="">
                </div>
            </div>
            <c:if test="${not empty requestScope.err}">
                <div class="container alert alert-danger">
                    <strong>${requestScope.err}</strong>
                </div>
            </c:if>
            <div class="form-group">
                <label class="control-label col-sm-2">Product Name:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" placeholder="Enter product name" name="productName" value="" required="">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2">Product Image:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" placeholder="Enter product image name (VD: testimage.jpg,....)" name="productImg" value="" required="">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2">Brief:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" placeholder="Enter brief about product" name="productBrief" value="" required="">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2">Posted date:</label>
                <div class="col-sm-10">
                    <input type="date" class="form-control" name="productPostedDate" value="" required="">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2">Type:</label>
                <div class="col-sm-10">
                    <select name="productTypeId" class="form-control">
                        <c:forEach var="i" items="${requestScope.typeList}">
                            <option value="${i.typeId}">${i.categoryName}</option>
                        </c:forEach>
                    </select>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2">Account:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" name="productAccountName" value="${sessionScope.loginInfo.account}" readonly>
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2">Unit:</label>
                <div class="col-sm-10">
                    <input type="text" class="form-control" placeholder="Enter unit" name="productUnit" value="" required="">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2">Price:</label>
                <div class="col-sm-10">
                    <input type="number" class="form-control" placeholder="Enter price" name="productPrice" value="" required="">
                </div>
            </div>
            <div class="form-group">
                <label class="control-label col-sm-2">Discount</label>
                <div class="col-sm-10">
                    <input type="number" class="form-control" placeholder="Enter discount" name="productDiscount" value="" required="">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Submit</button>
                </div>
            </div>
        </form>
        </div>
    </body>
</html>
