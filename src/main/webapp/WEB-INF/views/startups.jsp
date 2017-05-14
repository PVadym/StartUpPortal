<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Startups</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/navbar.jsp"/>
<div class="container">
    <h4><b>Startups</b></h4>

    <div class="table-responsive">
        <table class="table table-striped">
            <tr>
                <th>Name</th>
                <th>Target Investment</th>
                <th>Minimal Investment</th>
                <th>Current Investments</th>
                <c:if test="${is_admin}">
                    <th>Edit</th>
                    <th>Delete</th>
                </c:if>
            </tr>
            <c:forEach items="${startups}" var="startup">
                <tr>
                    <td>${startup.name}</td>
                    <td>${startup.targetInvestment}</td>
                    <td>${startup.minimalInvestment}</td>
                    <td>${startup.currentInvestment}</td>
                    <td>
                        <a class="btn btn-xs btn-primary active" role="button" style="margin: 5px"
                           href="<c:url value='/product/${product.id}'/>">Details</a>
                    </td>
                    <c:if test="${is_admin}">
                        <td>
                            <a class="btn btn-xs btn-primary active" role="button" style="margin: 5px"
                               href="<c:url value='/admin/product/edit/${product.id}'/>">Edit</a>
                        </td>
                        <td>
                            <a class="btn btn-xs btn-danger active" role="button" style="margin: 5px"
                               href="<c:url value="/admin/product/delete/${product.id}"/>">Delete</a>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
