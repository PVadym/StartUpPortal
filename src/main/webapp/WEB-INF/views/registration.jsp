<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Add a new user</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/navbar.jsp"/>
<div class="container">
    <div class="col-lg-4 col-md-offset-4">
        <h4><b>Registration</b></h4>
        <div class="jumbotron">
            <form:form action="/user/register" method="post" modelAttribute="user">

                <div class="form-group">
                    <input type="text" class="form-control" name="username" autofocus required placeholder="Username"/>
                    <div class="text-danger" role="alert">
                        <form:errors path="username"></form:errors>
                    </div>
                </div>
                <div class="form-group ">
                    <input type="password" class="form-control" name="password" required placeholder="Password">
                    <div class="text-danger" role="alert">
                        <form:errors path="password"></form:errors>
                    </div>
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" name="confirmPassword" required
                           placeholder="Confirm Password">
                    <div class="text-danger" role="alert">
                        <form:errors path="confirmPassword"></form:errors>
                    </div>
                </div>
                <c:if test="${is_admin}">
                    <div class="form-group">
                        <c:forEach items="${roles}" var="role">
                            <label>
                                <input type="radio" name="role" value="<c:out value="${role}"/>" required/>
                                <c:out value="${role}"/>
                            </label>
                            &nbsp;&nbsp;
                        </c:forEach>
                    </div>
                    <div class="form-group">
                        <label>
                            <input type="radio" name="locked" value="true" tabindex="1" required/>Locked
                        </label>
                        &nbsp;&nbsp;
                        <label>
                            <input type="radio" name="locked" value="false" tabindex="2" checked required/>Not locked
                        </label>
                    </div>
                </c:if>

                <div class="form-group ">
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3">
                            <input type="submit" tabindex="3"
                                   class="form-control btn btn-success" value="Register">
                        </div>
                    </div>
                </div>
            </form:form>
        </div>
    </div>
</div>
</body>
</html>
