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
    <hr>
    <div class="row">
         <div class="col-lg-9">
            <div class="jumbotron ">

                    <div class="row">
                        <h4 class="col-lg-3">Name:</h4>
                        <div class="well col-lg-6">${user.name}</div>
                    </div>
                    <a class="btn btn-block btn-primary" role="button"
                       href="<c:url value="/wp/add/${selectedProject.id}"/>">Add new WP</a>


            </div>
            <a class="btn btn-block btn-primary" role="button" href="<c:url value="/projects/add"/>">Add new Project</a>

        </div>

    </div>


</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
