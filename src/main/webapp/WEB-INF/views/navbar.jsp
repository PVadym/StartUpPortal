<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<div class="container">
    <div class="panel-heading">
        <div class="row">
            <h2 class="col-md-8">
                <a href="<c:url value='/'/>">StartUp Founder</a>
                <c:if test="${null != pageContext.request.userPrincipal.name}">
                    <small>
                        <span style='padding-left:100px;'></span>
                        Hello
                        <a href="<c:url value='/'/>">${pageContext.request.userPrincipal.name}</a>
                        !
                    </small>
                </c:if>
            </h2>
            <div class="btn-group pull-right">
                <sec:authorize access="isAuthenticated()">
                    <a class="btn btn-primary" role="button"
                       href="<c:url value='/user/${pageContext.request.userPrincipal.name}/true'/>">My page</a>
                </sec:authorize>

                <a class="btn btn-primary" role="button" href="<c:url value='/'/>">StartUps</a>
                <c:choose>
                    <c:when test="${is_admin}">
                        <a class="btn btn-primary" role="button" href="<c:url value='/user'/>">Users</a>
                    </c:when>
                </c:choose>
                <sec:authorize access="isAuthenticated()">
                    <a class="btn btn-primary" role="button" href="<c:url value='/logout'/>">Logout</a>
                </sec:authorize>
                <sec:authorize access="!isAuthenticated()">
                    <a class="btn btn-primary" role="button" href="<c:url value='/user/register'/>">Registration</a>
                    <a class="btn btn-primary" role="button" href="<c:url value='/login'/>">Login</a>
                </sec:authorize>
            </div>
        </div>
        <hr>
    </div>
</div>