<div class="jumbo" style="text-align: left; margin-top: 20px">

<h4><b>Investments</b></h4>

    <table class="table table-striped">
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
                       onclick="if(confirm('Delete this investment?')) this.submit(); else return false;"
                       href="<c:url value="/investments/delete/${investment.id}"/>">Delete</a>
                </td>
            </tr>
        </c:forEach>
    </table>
</div>


