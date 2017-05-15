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
        <div class="col-lg-4">
            <div class="jumbotron ">
                <div class="well well-sm">Name: ${user.username}</div>
                <div class="well well-sm">Contacts: ${user.contacts}</div>
                <a class="btn-sm  btn-primary pull-right" role="button"
                   href="<c:url value='/startups/add/${user.id}'/>">Add StartUp</a>
            </div>
        </div>
        <div class="col-lg-8">
            <div class="row">
                <div class="btn-group btn-group-justified">
                    <a class="btn  btn-primary" role="button"
                       href="<c:url value='/user/${pageContext.request.userPrincipal.name}/true'/>">StartUps</a>
                    <a class="btn  btn-info" role="button"
                       href="<c:url value='/user/${pageContext.request.userPrincipal.name}/false'/>">Investments</a>
                </div>
            </div>
        </div>
        <div class="col-lg-8">
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
