<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Add a Start-Up</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
    <link rel="stylesheet" href="<c:url value='/resources/css/customStyles.css'/>">

</head>
<body>
<jsp:include page="/WEB-INF/views/navbar.jsp"/>
<div class="container">
    <h4><b>Add a Start-Up</b></h4>
    <div class="panel-body">

        <div class="jumbo">
            <div class="row">

                <div class="col-lg-12">
                    <form:form class="form-horizontal" action="/startups/add" method="post" role="form"
                               style="display: block;" modelAttribute="startup">

                        <div class="form-group" hidden>
                            <div class="col-sm-10">
                                <input type="text" name="author.id" class="form-control"
                                       value="${startup.author.id}">

                            </div>
                        </div>
                        <br>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="name">Name:</label>
                            <div class="col-sm-10">
                                <input type="text" name="name" id="name" tabindex="1" class="form-control"
                                       placeholder="Name" required>
                                <div class="text-danger" role="alert">
                                    <form:errors path="name"></form:errors>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="needInvestment">Target Investment:</label>
                            <div class="col-sm-10">
                                <input type="number" name="needInvestment" id="needInvestment" tabindex="3"
                                       class="form-control"
                                       placeholder="Target Investment" required>
                                <div class="text-danger" role="alert">
                                    <form:errors path="needInvestment"></form:errors>
                                </div>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="minInvestment">Minimal Investment:</label>
                            <div class="col-sm-10">
                                <input type="number" name="minInvestment" id="minInvestment" tabindex="2"
                                       class="form-control"
                                       placeholder="Minimal Investment" required>
                                <div class="text-danger" role="alert">
                                    <form:errors path="minInvestment"></form:errors>
                                </div>
                            </div>
                        </div>


                        <div class="form-group">
                            <label class="control-label col-sm-2" for="description">Description:</label>
                            <div class="col-sm-10">
                                <textarea rows="5" name="description" id="description" tabindex="4"
                                          class="form-control"
                                          placeholder="Enter the Startup's description"></textarea>
                            </div>
                        </div>
                        <div class="form-group ">
                            <div class="row">
                                <div class="col-sm-6 col-sm-offset-3">
                                    <input type="submit" tabindex="5"
                                           class="form-control btn-success" value="Submit">
                                </div>
                            </div>
                        </div>
                        <div class="form-group ">
                            <div class="row">
                                <div class="col-sm-6 col-sm-offset-3">
                                    <input type="reset" tabindex="6"
                                           class="form-control btn-danger" value="Reset">
                                </div>
                            </div>
                        </div>
                    </form:form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
