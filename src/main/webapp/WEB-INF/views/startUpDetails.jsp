<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <title>StartUp Details</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<%@include file="/WEB-INF/views/navbar.jsp" %>
<div class="container">

    <div class="row">

        <div class="col-lg-3">
            <h4><b>StartUp's Details</b></h4>
            <div class="jumbotron ">
                <div class="well well-sm">Name: ${startUp.name}</div>
                <div class="well well-sm">Min. Investment: ${startUp.minInvestment}</div>
                <div class="well well-sm">Target Investment: ${startUp.needInvestment}</div>
                <div class="well well-sm">Current Investment: ${startUp.getCurrentInvestments()}</div>
                <div class="well well-sm">Current Investment: ${startUp.getCurrentInvestments()}</div>
            </div>
        </div>

        <div class="col-lg-9">
            <h4><b>StartUp's Investments</b></h4>
            <div class="table-responsive">
                <table class="table table-striped">
                    <tr>
                        <th>Amount</th>
                        <th>Author</th>
                    </tr>
                    <c:forEach items="${startUp.investments}" var="investment">
                        <tr align="center">
                            <td>${investment.amount}</td>
                            <td>${investment.author.username}</td>
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
