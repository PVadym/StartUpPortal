<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="container">
    <div class="panel-heading">
        <div class="row">
            <h4 class="col-lg-3">
                <a href="<c:url value='/user/${pageContext.request.userPrincipal.name}'/>">
                    User: ${pageContext.request.userPrincipal.name}
                </a>
            </h4>
            <div class="btn-group pull-right">
                <a class="btn btn-primary" role="button" href="<c:url value='/'/>">StartUps</a>
                <c:choose>
                    <c:when test="${is_admin}">
                        <a class="btn btn-primary" role="button" href="<c:url value='/users'/>">Users</a>
                        <a class="btn btn-primary" role="button" href="<c:url value='/logout'/>">Logout</a>
                    </c:when>
                    <c:otherwise>
                        <a class="btn btn-primary" role="button" href="<c:url value='/login'/>">Login</a>
                    </c:otherwise>
                </c:choose>
            </div>
        </div>
        <hr>
    </div>
</div>