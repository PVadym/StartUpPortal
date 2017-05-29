<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Change Password</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/navbar.jsp"/>
<div class="container">
    <div class="col-lg-4 col-md-offset-4">
        <h4><b>Change Password</b></h4>
        <div class="jumbotron">
            <form action="/user/changePassword" method="post">

                <div class="form-group ">
                    <input type="password" class="form-control" name="oldPassword" required placeholder="Old Password">
                    <div class="text-danger" role="alert">
                        ${errorMessageOldPassword}
                    </div>
                </div>

                <div class="form-group ">
                    <input type="password" class="form-control" name="password" required placeholder="New Password">
                    <div class="text-danger" role="alert">
                        ${errorMessagePassword}
                    </div>
                </div>
                <div class="form-group">
                    <input type="password" class="form-control" name="confirmPassword" required
                           placeholder="Confirm New Password">
                    <div class="text-danger" role="alert">
                        ${errorMessagePassword}
                    </div>
                </div>

                <div class="form-group ">
                    <div class="row">
                        <div class="col-sm-6 col-sm-offset-3">
                            <input type="submit" tabindex="3"
                                   class="form-control btn btn-success" value="Save">
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
