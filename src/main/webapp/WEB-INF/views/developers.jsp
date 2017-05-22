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


    <div class="row" style="margin-top: 40px">
        <%--Pavel--%>
        <h3 class="col-md-offset-5">Our Team:</h3>
            <br>
        <div class="col-md-3 col-md-offset-1">
            <div class="jumbo">
                <div class="imgcontainer" style="height: 180px;">
                    <img class="img" src="/resources/developersphoto/Pavlo.jpg"/>
                </div>
                <h4>Pavlo Perevoznyk</h4>
                <p><a style="text-decoration: underline" href="mailto:pavlo.perevoznyk@gmail.com" target="_blank">pavlo.perevoznyk@gmail.com</a>
                </p>
                <p><a style="text-decoration: underline" href="https://www.linkedin.com/in/pavlo-perevoznyk-456018a5/"
                      target="_blank" title="Linked In">Linked In profile</a></p>
                <p><a style="text-decoration: underline" href="https://github.com/pperevoznyk/" target="_blank"
                      title="GitHub">GitHub page</a></p>
            </div>
        </div>

        <%--Vadym--%>
        <div class="col-md-3">
            <div class="jumbo">
                <div class="imgcontainer" style="height: 180px;">
                    <img class="img" src="/resources/developersphoto/vadym.PNG"/>
                </div>
                <h4>Vadym Pylypchenko</h4>
                <p><a style="text-decoration: underline" href="mailto:vadym_pylyp@ukr.net" target="_blank">vadym_pylyp@ukr.net</a>
                </p>
                <p><a style="text-decoration: underline" href="https://www.linkedin.com/in/pylypchenko-vadym-55a329141/"
                      target="_blank" title="Linked In">Linked In profile</a></p>
                <p><a style="text-decoration: underline" href="https://github.com/PVadym/" target="_blank"
                      title="GitHub page">GitHub page</a></p>
            </div>
        </div>

        <%--Slava--%>
        <div class="col-md-3">
            <div class="jumbo">
                <div class="imgcontainer" style="height: 180px;">
                    <img class="img" src="/resources/developersphoto/slava.PNG"/>
                </div>
                <h4>Slava Makhinich</h4>
                <p><a style="text-decoration: underline" href="mailto:s.makhinich@gmail.com" target="_blank">s.makhinich@gmail.com</a>
                </p>
                <p><a style="text-decoration: underline" href="https://www.linkedin.com/in/viacheslav-makhinich-99408113b/"
                      target="_blank" title="Linked In">Linked In profile</a></p>
                <p><a style="text-decoration: underline" href="https://github.com/Makhinich" target="_blank"
                      title="GitHub page">GitHub page</a></p>
            </div>
        </div>
    </div>
    <div class="row">
        <br>
        <h4 class="col-md-offset-4">The source code of this site:
            <a style="text-decoration: underline" target="_blank" href="https://github.com/pperevoznyk/startupMy">
                GitHub</a>
        </h4>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
