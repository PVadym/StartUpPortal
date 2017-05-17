<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <title>Projects</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value='/resources/css/customStyles.css'/>">
</head>
<body>
<%@include file="/WEB-INF/views/navbar.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-md-3">
            <div class="jumbo">
                <div class="picture">
                    <img src="/images/${user.imageId}"/>
                </div>
                <form method="POST" enctype="multipart/form-data" action="/images/upload/${user.id}/${user.imageId}">
                    <input type="file" name="file">
                    <input type="submit" value="Upload">
                </form>
                <h4>
                    <p>${user.username}</p>
                </h4>
                <h5>
                    <small>contacts:</small>
                    <p>${user.contacts}</p>
                </h5>
                <br>
                <a class="btn  btn-success" role="button"
                   href="<c:url value='/startups/add/${user.id}'/>">Add StartUp</a>
                <br>
                <a class="btn-sm" role="button"
                   href="<c:url value='/user/edit/${user.id}'/>">Edit Personal Info</a>
            </div>
        </div>
        <div class="col-md-9">
            <div class="row">
                <div class="btn-group btn-group-justified">
                    <a class="btn  btn-primary" role="button"
                       href="<c:url value='/user/${pageContext.request.userPrincipal.name}/true'/>">My StartUps</a>
                    <a class="btn  btn-primary" role="button"
                       href="<c:url value='/user/${pageContext.request.userPrincipal.name}/false'/>">My Investments</a>
                </div>
            </div>
        </div>
        <div class="col-md-9">
            <div class="row">
                <c:choose>
                    <c:when test="${isStartUps}">
                        <%@include file="/WEB-INF/views/userStartUps.jsp" %>
                        <br/>
                    </c:when>
                    <c:otherwise>
                        <%@include file="/WEB-INF/views/userInvestments.jsp" %>
                        <br/>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
    </div>
</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
