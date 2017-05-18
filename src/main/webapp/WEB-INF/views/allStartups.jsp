<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Startups</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value='/resources/css/customStyles.css'/>">
</head>
<body>
<jsp:include page="/WEB-INF/views/navbar.jsp"/>
<div class="container">

    <div class="row">
        <div class="col-md-2"><h4><b>Startups</b></h4></div>

        <div class="col-md-10">
        <form class="form-inline pull-right" action="<c:url value='/'/>" method='get'>
            <div class="form-group">
                <input type="text" name="searchWord" class="form-control" placeholder="What are you looking for?"/>
            </div>
            <button type="submit" class="btn btn-primary">Search</button>
        </form>
        </div>
    </div>

    <div class="row">
        <c:forEach items="${startups}" var="startup">
            <div class="col-md-3">
                <a href="<c:url value='/startups/${startup.id}'/>" class="">
                    <div class="jumbo">
                        <div class="picture" style="width: 90%; margin: auto;">
                            <img style="width: 100%;" src="/images/${startup.imageId}"/>
                        </div>
                        <h4>
                            <p>${startup.name}</p>
                        </h4>
                        <h4>
                            <small>target investments:</small>
                            <p>${startup.needInvestment}</p>
                        </h4>
                        <h4>
                            <small>curr. invetments:</small>
                            <p>${startup.getCurrentInvestments()}</p>
                        </h4>
                    </div>
                </a>
            </div>
        </c:forEach>

    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
