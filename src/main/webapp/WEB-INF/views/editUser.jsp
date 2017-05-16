<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Edit</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/navbar.jsp"/>
<div class="container">
    <div class="col-lg-4 col-md-offset-4">
        <h4><b>Edit User's Information</b></h4>
        <div class="jumbotron">
            <form action="<c:url value="/user/edit"/>" method="post">

                <div class="form-group" hidden>
                    <input type="text" class="form-control" name="id" value="${user.id}">
                </div>

                <div class="form-group">
                    <label for="username">Name:</label>
                    <input type="text" class="form-control" name="username" id="username" tabindex="1"
                           required autofocus placeholder="Username" value="${user.username}">
                </div>


                <div class="form-group">
                    <label for="contacts">Contacts:</label>
                    <textarea class="form-control" rows="5" id="contacts" name="contacts" tabindex="2">${user.contacts}</textarea>
                </div>


                <div class="form-group">
                    <label for="password">Password:</label>
                    <input type="password" class="form-control" name="password" id="password" tabindex="3"
                           required placeholder="Password" value="${user.password}">
                </div>

                <c:if test="${is_admin}">
                    <div class="form-group">
                        <c:forEach items="${roles}" var="role">
                            <label>
                                <input type="radio" name="role" value="<c:out value="${role}"/>" required
                                       <c:if test="${role eq user.role}">checked</c:if>/>
                                <c:out value="${role}"/>
                            </label>
                            &nbsp;&nbsp;
                        </c:forEach>
                    </div>
                    <div class="form-group">
                        <label>
                            <input type="radio" name="locked" value="true" tabindex="4"
                                   <c:if test="${user.isLocked}">checked</c:if>/>Locked
                        </label>
                        &nbsp;&nbsp;
                        <label>
                            <input type="radio" name="locked" value="false" tabindex="5"
                                   <c:if test="${!user.isLocked}">checked</c:if>/>Not locked
                        </label>
                    </div>
                </c:if>

                <div class="form-group ">
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3">
                            <input type="submit" tabindex="4"
                                   class="form-control btn btn-success" value="Save"/>
                        </div>
                    </div>
                </div>
                <div class="form-group ">
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3">
                            <a class="btn-block btn btn-primary" href="/" type="button"
                               tabindex="5">Cancel</a>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
