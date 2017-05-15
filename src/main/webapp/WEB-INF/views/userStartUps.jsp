<div class="container">
    <h4><b>User's Startups</b></h4>

    <div class="table-responsive">
        <table class="table table-striped">
            <tr>
                <th align="center">Name</th>
                <th>Target Investment</th>
                <th>Minimal Investment</th>
                <th>Current Investments</th>
                <th>Details</th>
                <c:if test="${is_admin}">
                    <th>Delete</th>
                </c:if>
            </tr>
            <c:forEach items="${startups}" var="startup">
                <tr align="center">
                    <td width="50%" align="left">${startup.name}</td>
                    <td>${startup.needInvestment}</td>
                    <td>${startup.minInvestment}</td>
                    <td>${startup.getCurrentInvestments()}</td>
                    <td>
                        <a class="btn btn-xs btn-primary active" role="button" style="margin: 5px"
                           href="<c:url value='/startups/${startup.id}'/>">Details</a>
                    </td>
                </tr>
            </c:forEach>
        </table>
    </div>
</div>

