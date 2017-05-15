<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Login</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/navbar.jsp"/>
<div class="container">


    <div class="col-lg-4 col-md-offset-4">
        <h4><b>Login</b></h4>
        <c:if test="${param.error ne null}">
            <div class="text-danger" role="alert">
                <h5>Error</h5>
            </div>
        </c:if>
        <c:if test="${param.logout ne null}">
            <div class="text-success" role="alert">
                <h5>You have successfully logged out.</h5>
            </div>
        </c:if>
        <div class="jumbotron">
            <form action="<c:url value='/login'/>" method='post'>
                <div class="form-group">
                    <input id="username" class="form-control" type="text" name="username" tabindex="1" required
                           autofocus
                           placeholder="Username">
                </div>
                <div class="form-group">
                    <input id="password" class="form-control" type="password" name="password" tabindex="2" required
                           placeholder="Password">
                </div>

                <div class="form-group ">
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3">
                            <input type="submit" id="login-submit" tabindex="4"
                                   class="form-control btn btn-success" value="Log In">
                        </div>
                    </div>
                </div>

                <div class="form-group">
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3">
                            <a href="<c:url value='/user/register'/>">
                                <input type="button" class="btn btn-primary" value="Registration">
                            </a>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>


</div>
</body>
</html>
