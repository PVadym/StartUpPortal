<h4><b>User's Investments</b></h4>

<div class="table-responsive">
    <table class="table table-striped">
        <tr>
            <th>StartUp</th>
            <th>Investor</th>
            <th>Amount</th>
        </tr>
        <c:forEach items="${investments}" var="investment">
            <tr>
                <td><a href="<c:url value='/startups/${investment.startup.id}'/>">${investment.startup.name}</a></td>
                <td>${investment.investor.username}</td>
                <td>${investment.amount}</td>
            </tr>
        </c:forEach>
    </table>
</div>


