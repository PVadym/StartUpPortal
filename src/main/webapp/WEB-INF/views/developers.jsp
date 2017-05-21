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
    <h3>The source code of this site:
        <a style="text-decoration: underline" target="_blank" href="https://github.com/pperevoznyk/startupMy"> GitHub</a>
    </h3>

    <div style="margin-top: 40px">

        <%--Pavel--%>
        <div class="col-md-3">
            <div class="jumbo">
                <div class="imgcontainer" style="height: 180px;">
                    <img class="img" src="/resources/developersphoto/Pavlo.PNG"/>
                </div>
                <h4>Pavlo Perevoznyk</h4>
                <p>pavlo.perevoznyk@gmail.com</p>
                <p><a style="text-decoration: underline" href="https://www.facebook.com"
                      target="_blank" title="Facebook page">Facebook page</a></p>
                <p><a style="text-decoration: underline" href="https://github.com/pperevoznyk/" target="_blank"
                      title="GitHub page">GitHub page</a></p>
            </div>
        </div>

        <%--Vadym--%>
        <div class="col-md-3">
            <div class="jumbo">
                <div class="imgcontainer" style="height: 180px;">
                    <img class="img" src="/resources/developersphoto/vadym.PNG"/>
                </div>
                <h4>Vadym Pylypchenko</h4>
                <p>vadym_pylyp@ukr.net</p>
                <p><a style="text-decoration: underline" href="https://www.facebook.com/PylypchenkoVadym"
                      target="_blank" title="Facebook page">Facebook page</a></p>
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
                <p>s.makhinich@gmail.com</p>
                <p><a style="text-decoration: underline" href="https://www.facebook.com/profile.php?id=100002030620256"
                      target="_blank" title="Facebook page">Facebook page</a></p>
                <p><a style="text-decoration: underline" href="https://github.com/Makhinich" target="_blank"
                      title="GitHub page">GitHub page</a></p>
            </div>
        </div>

    </div>

</div>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.2.0/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.3.7/js/bootstrap.min.js"></script>
</body>
</html>
