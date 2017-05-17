<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <title>StartUp Details</title>

    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value='/resources/css/customStyles.css'/>">

</head>
<body>
<%@include file="/WEB-INF/views/navbar.jsp" %>
<div class="container">

    <h4><b>${startup.name}</b></h4>

    <div class="row">

        <div class="col-md-3">
            <div class="jumbo">
                <h4>
                    <small>Author:</small>
                    <p>${startup.author.username}</p>
                </h4>
                <h5>
                    <small>contacts:</small>
                    <p>${startup.author.contacts}</p>
                </h5>
                <h4>
                    <small>min. investments:</small>
                    <p>${startup.minInvestment}</p>
                </h4>
                <h4>
                    <small>target investments:</small>
                    <p>${startup.needInvestment}</p>
                </h4>
                <h4>
                    <small>curr. invetments:</small>
                    <p>${startup.getCurrentInvestments()}</p>
                </h4>

                <c:if test="${!(pageContext.request.userPrincipal.name eq startup.author.username)}">
                    <c:choose>
                        <c:when test="${not empty pageContext.request.userPrincipal.name}">
                            <a class="btn btn-primary" role="button" style="margin: 5px"
                               href="<c:url value='/investments/invest/${startup.id}/${pageContext.request.userPrincipal.name}'/>">
                                Invest
                            </a>
                        </c:when>
                        <c:otherwise>
                            <a class="btn btn-primary" role="button" style="margin: 5px"
                               href="<c:url value='/login'/>">Invest</a>
                        </c:otherwise>
                    </c:choose>
                </c:if>

                <c:if test="${pageContext.request.userPrincipal.name eq startup.author.username}">
                    <a class="btn btn-primary" role="button" style="margin: 5px"
                       href="<c:url value='/startups/edit/${startup.id}'/>">Edit</a>
                </c:if>

                <c:if test="${pageContext.request.userPrincipal.name eq startup.author.username or is_admin}">
                    <a class="btn btn-danger" role="button" style="margin: 5px"
                       href="<c:url value='/startups/delete/${startup.id}'/>">Delete</a>
                </c:if>

            </div>
        </div>

        <div class="col-md-9">

            <div class="jumbo">
                ${startup.description}
            </div>

            <c:if test="${pageContext.request.userPrincipal.name eq startup.author.username or is_admin}">
                <h4><b>Current Investments: </b></h4>
                <div class="table-responsive">
                    <table class="table table-striped">
                        <tr>
                            <th>Amount</th>
                            <th>Investor</th>
                            <th>Contacts</th>
                        </tr>
                        <c:forEach items="${startup.investments}" var="investment">
                            <tr>
                                <td>${investment.amount}</td>
                                <td>${investment.investor.username}</td>
                                <td>${investment.investor.contacts}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </c:if>

        </div>
    </div>

</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
