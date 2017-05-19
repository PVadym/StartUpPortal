<h4><b>User's Investments</b></h4>

<div class="table-responsive">
    <table class="table table-striped" style="text-align: center">
        <tr>
            <th>StartUp</th>
            <th>Amount</th>
            <th>Delete</th>
        </tr>
        <c:forEach items="${investments}" var="investment">
            <tr>
                <td><a href="<c:url value='/startups/${investment.startup.id}'/>">${investment.startup.name}</a></td>
                <td>${investment.amount}</td>
                <td>
                    <a class="btn btn-xs btn-danger" role="button" style="margin: 5px"
                       href="<c:url value="/investments/delete/${investment.id}"/>">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>


