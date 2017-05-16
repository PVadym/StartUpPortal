<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Users</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/navbar.jsp"/>
<div class="container">

    <div class="row">
        <div class="col-md-6 col-md-offset-3">
            <h4><b>All Users</b></h4>
            <div class="table-responsive">
                <table class="table table-striped">
                    <tr>
                        <th>Name</th>
                        <th>Role</th>
                        <c:if test="${is_admin}">
                            <th>Edit</th>
                            <th>Delete</th>
                        </c:if>
                    </tr>
                    <c:forEach items="${users}" var="user">
                        <tr>
                            <td>${user.username}</td>
                            <td>${user.role}</td>
                            <c:if test="${is_admin}">
                                <td>
                                    <a class="btn btn-xs btn-danger" role="button" style="margin: 5px"
                                       href="<c:url value="/user/edit/${user.id}"/>">Edit</a>
                                </td>
                                <td>
                                    <a class="btn btn-xs btn-danger" role="button" style="margin: 5px"
                                       href="<c:url value="/user/delete/${user.id}"/>">Delete</a>
                                </td>
                            </c:if>
                        </tr>
                    </c:forEach>
                </table>
            </div>
        </div>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
