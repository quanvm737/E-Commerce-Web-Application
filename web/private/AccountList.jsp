<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Account Manager</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
        <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
        <style>
            body{
                padding-top: 70px;
            };
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
            <h1>List of all account in the system</h1>
            <br/>
            <table class="table table-striped">
                <tr style="background-color: cadetblue" class="text-center">
                    <th style="width:10%">Account</th>
                    <th style="width:18%">Full name</th>
                    <th style="width:12%">Birthday</th>
                    <th style="width:8%">Gender</th>
                    <th style="width:10%">Phone</th>
                    <th style="width:10%">Role</th>
                    <th style="width:7%">Status</th>
                    <th style="width:25%">Action</th>
                </tr>
                <c:forEach var="i" items="${requestScope.listAccount}">
                    <tr>
                        <td>${i.account}</td>
                        <td>${i.lastName.concat(' ').concat(i.firstName)}</td>
                        <td><fmt:formatDate value='${i.birthday}' pattern='dd-MM-yyyy'/></td>
                        <td>
                            <c:choose>
                                <c:when test="${i.gender}">
                                    Male
                                </c:when>
                                <c:otherwise>
                                    Female
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>${i.phone}</td>
                        <td>
                            <c:if test="${i.roleInSystem == 1}">
                                Administrator
                            </c:if>
                            <c:if test="${i.roleInSystem == 2}">
                                Staff
                            </c:if>
                            <c:if test="${i.roleInSystem == 3}">
                                Customer
                            </c:if>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${i.isUse == true}">
                                    <p class="label label-success">Active</p>
                                </c:when>
                                <c:otherwise>
                                    <p class="label label-danger">Disabled</p>
                                </c:otherwise>
                            </c:choose>
                        </td>
                        <td>
                            <c:choose>
                                <c:when test="${sessionScope.loginInfo.roleInSystem == 1}">
                                    <a href="listAccount?btnAccountAction=Update&accountName=${i.account}" class="btn btn-primary">UPDATE</a>
                                    <a href="listAccount?btnAccountAction=Delete&accountName=${i.account}" class="btn btn-danger"
                                       onclick="return confirm('Are you sure do you want to delete this account? All the products belong to this will be deleted')">DELETE</a>
                                    <a href="listAccount?btnAccountAction=Activation&accountName=${i.account}" class="btn btn-warning">
                                        <c:choose>
                                            <c:when test="${i.isUse}">
                                                Deactivate
                                            </c:when>
                                            <c:otherwise>
                                                Activate
                                            </c:otherwise>
                                        </c:choose>
                                    </a>
                                </c:when>
                                <c:otherwise>
                                    <c:choose>
                                        <c:when test="${(i.roleInSystem != 1)}">
                                            <a href="listAccount?btnAccountAction=Update&accountName=${i.account}" class="btn btn-primary">UPDATE</a>
                                            <a href="listAccount?btnAccountAction=Delete&accountName=${i.account}" class="btn btn-danger"
                                               onclick="return confirm('Are you sure do you want to delete this account? All the products belong to this will be deleted')">DELETE</a>
                                            <a href="listAccount?btnAccountAction=Activation&accountName=${i.account}" class="btn btn-warning">
                                                <c:choose>
                                                    <c:when test="${i.isUse}">
                                                        Deactivate
                                                    </c:when>
                                                    <c:otherwise>
                                                        Activate
                                                    </c:otherwise>
                                                </c:choose>
                                            </a>
                                        </c:when>
                                        <c:otherwise>
                                            <button class="btn btn-primary" disabled = "disabled">UPDATE</button>
                                            <button class="btn btn-danger" disabled = "disabled">DELETE</button>
                                            <button class="btn btn-warning" disabled = "disabled">
                                                <c:choose>
                                                    <c:when test="${i.isUse}">
                                                        Deactivate
                                                    </c:when>
                                                    <c:otherwise>
                                                        Activate
                                                    </c:otherwise>
                                                </c:choose>
                                            </button>
                                        </c:otherwise>
                                    </c:choose>
                                </c:otherwise>
                            </c:choose>
                        </td>
                    </tr>
                </c:forEach>
            </table>
        </div>
    </body>
</html>
