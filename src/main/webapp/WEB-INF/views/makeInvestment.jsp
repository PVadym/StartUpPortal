<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Make Investment</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/navbar.jsp"/>
<div class="container">
    <div class="jumbotron col-md-4">
        <h4><b>StartUp's Details</b></h4>
        <div class="well well-sm">Name: ${startUp.name}</div>
        <div class="well well-sm">Minimal Investment: ${startUp.minInvestment}</div>
        <div class="well well-sm">Target Investment: ${startUp.needInvestment}</div>
        <div class="well well-sm">Current Investment: ${startUp.getCurrentInvestments()}</div>
    </div>

    <div class="col-xs-1"></div>

    <div class="jumbotron col-md-3">
        <h4><b>Make Investment</b></h4>
        <form class="form-horizontal" action="/investments/invest" method="post" role="form"
              style="display: block;" modelAttribute="investment">

            <div class="form-group" >
                <input type="text" name="author.username" class="form-control"
                       value="${pageContext.request.userPrincipal.name}">
            </div>
            <div class="form-group">
                <div class="row">
                    <input type="number" name="amount" id="amount" tabindex="1"
                           class="form-control"
                           placeholder="Investment Amount">
                </div>
            </div>
            <div class="form-group">
                <div class="row">
                    <input type="submit" tabindex="2"
                           class="form-control btn-success" value="Submit">
                </div>
            </div>
            <div class="form-group">
                <div class="row">

                    <input type="reset" tabindex="3"
                           class="form-control btn-danger" value="Reset">
                </div>
            </div>
    </div>
    </form>
</div>
</div>

</body>
</html>
