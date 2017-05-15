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

        <form class="form-group" action="<c:url value='/'/>" method='get'>
            <table>
                <tr>
                    <td width="100%">
                        <input class="form-control" type="text" name="searchWord"
                               placeholder="What are you looking for?">
                    </td>
                    <td>
                        <input class="btn btn-primary" type="submit" value="Search">
                    </td>
                </tr>
            </table>
        </form>

    <div class="table-responsive">
        <table class="table table-striped">
            <tr>
                <th align="center">Name</th>
                <th>Target Investment</th>
                <th>Minimal Investment</th>
                <th>Current Investments</th>
                <th>Details</th>
                <th>Invest</th>
                <c:if test="${is_admin}">
                    <th>Delete</th>
                </c:if>
            </tr>
            <c:forEach items="${startups}" var="startup">
                <tr align="center">
                    <td width="50%" align="left">${startup.name}</td>
                    <td>${startup.needInvestment}</td>
                    <td>${startup.minInvestment}</td>
                    <td>${startup.getCurrentInvestments()}</td>
                    <td>
                        <a class="btn btn-xs btn-primary" role="button" style="margin: 5px"
                           href="<c:url value='/startups/${startup.id}'/>">Details</a>
                    </td>
                    <td>
                        <c:choose>
                            <c:when test="${not empty pageContext.request.userPrincipal.name}">
                                <a class="btn btn-xs btn-primary" role="button" style="margin: 5px"
                                   href="<c:url value='/investments/invest/${startup.id}/${pageContext.request.userPrincipal.name}'/>">
                                    Invest
                                </a>
                            </c:when>
                            <c:otherwise>
                                <a class="btn btn-xs btn-primary" role="button" style="margin: 5px"
                                   href="<c:url value='/login'/>">Invest</a>
                            </c:otherwise>
                        </c:choose>
                    </td>

                    <c:if test="${is_admin}">
                        <%--<td>--%>
                        <%--<a class="btn btn-xs btn-primary active" role="button" style="margin: 5px"--%>
                        <%--href="<c:url value='/admin/product/edit/${product.id}'/>">Edit</a>--%>
                        <%--</td>--%>
                        <td>
                            <a class="btn btn-xs btn-danger" role="button" style="margin: 5px"
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
