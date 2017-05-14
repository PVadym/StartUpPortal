<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <title>Projects</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<%@include file="/WEB-INF/views/navbar.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-lg-5">
            <div class="jumbotron ">
                <h4 class="col-lg-5">Name: ${user.username}</h4>
            </div>
        </div>
    </div>
    <a class="btn btn-primary" role="button" href="<c:url value='/startups/add'/>">Add StartUp</a>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
