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


    <h4 style="text-align: center"><b>${startup.name}</b></h4>

    <div class="row">

        <div class="col-md-3">
            <div class="jumbo">
                <div class="imgcontainer">
                    <img class="img" src="/images/${startup.imageId}"/>
                </div>
                <c:if test="${pageContext.request.userPrincipal.name eq startup.author.username}">
                    <form method="POST" enctype="multipart/form-data"
                          action="/images/uploadStartupImage/${startup.id}/${startup.imageId}"
                          id="image">
                        <label class="btn-xs btn btn-file">
                            choose new photo <input type="file" name="file" style="display: none;" required>
                        </label>
                        <label class="btn-xs btn btn-file">
                            submit <input class="btn-xs" type="submit" style="display: none;">
                        </label>
                    </form>
                </c:if>
                <h4>
                    <small>author:</small>
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
                    <a class="btn btn-primary" role="button" style="margin: 5px"
                       href="<c:url value='/investments/invest/${startup.id}'/>">
                        Invest
                    </a>
                </c:if>
                <c:if test="${pageContext.request.userPrincipal.name eq startup.author.username}">
                    <a class="btn btn-primary" role="button" style="margin: 5px"
                       href="<c:url value='/startups/edit/${startup.id}'/>">Edit</a>
                </c:if>

                <c:if test="${pageContext.request.userPrincipal.name eq startup.author.username or is_admin}">
                    <a class="btn btn-danger" role="button" style="margin: 5px"
                       onclick="if(confirm('Delete this startup?')) this.submit; else return false;"
                       href="<c:url value='/startups/delete/${startup.id}'/>">Delete</a>
                </c:if>

            </div>
        </div>

        <div class="col-md-9">

            <div class="jumbo">
                ${startup.description}
            </div>

            <c:if test="${pageContext.request.userPrincipal.name eq startup.author.username or is_admin}">

                <div class="jumbo" style="text-align: left">
                    <h4><b>Current Investments: </b></h4>
                    <table class="table table-striped">
                        <tr>
                            <th>Amount</th>
                            <th>Investor</th>
                            <th>Contacts</th>
                            <c:if test="${is_admin}">
                                <th>Delete</th>
                            </c:if>
                        </tr>
                        <c:forEach items="${startup.investments}" var="investment">
                            <tr>
                                <td>${investment.amount}</td>
                                <td>${investment.investor.username}</td>
                                <td>${investment.investor.contacts}</td>
                                <td>
                                    <a class="btn btn-xs btn-danger" role="button" style="margin: 5px"
                                       onclick="if(confirm('Delete this investment?')) this.submit"
                                       href="<c:url value="/investments/delete/${investment.id}"/>">Delete</a>
                                </td>
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