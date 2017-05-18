<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <title>Edit Start-Up</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/css/bootstrap.min.css">
</head>
<body>
<jsp:include page="/WEB-INF/views/navbar.jsp"/>
<div class="container">
    <h4><b>Edit Start-Up</b></h4>
    <div class="panel-body">

        <div class="jumbotron">
            <div class="row">

                <div class="col-lg-12">
                    <form class="form-horizontal" action="/startups/edit" method="post" role="form"
                          style="display: block;" modelAttribute="startUp">

                        <div class="form-group" hidden>
                            <div class="col-sm-10">
                                <input type="number" name="author.id" class="form-control"
                                       value="${startup.author.id}">
                            </div>
                        </div>
                        <div class="form-group" hidden>
                            <div class="col-sm-10">
                                <input type="number" name="id" class="form-control"
                                       value="${startup.id}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="name">Name:<br>(100 chars max)</label>
                            <div class="col-sm-10">
                                <input type="text" name="name" id="name" tabindex="1" class="form-control"
                                       placeholder="Name" value="${startup.name}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="minInvestment">Minimal Investment:</label>
                            <div class="col-sm-10">
                                <input type="number" name="minInvestment" id="minInvestment" tabindex="2"
                                       class="form-control"
                                       placeholder="Minimal Investment" value="${startup.minInvestment}">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="control-label col-sm-2" for="needInvestment">Target Investment:</label>
                            <div class="col-sm-10">
                                <input type="number" name="needInvestment" id="needInvestment" tabindex="3"
                                       class="form-control"
                                       placeholder="Target Investment" value="${startup.needInvestment}">
                            </div>
                        </div>

                        <div class="form-group">
                            <label class="control-label col-sm-2" for="description">Description:<br>(1800 chars max)</label>
                            <div class="col-sm-10">
                                <textarea type="text" name="description" id="description" tabindex="4"
                                          class="form-control" rows="5"
                                          placeholder="Description">${startup.description}</textarea>
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
                    </form>
                </div>
            </div>
        </div>
    </div>
</div>
</body>
</html>
