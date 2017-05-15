<h4><b>User's Investments</b></h4>

<div class="table-responsive">
    <table class="table table-striped">
        <tr>
            <%--<th align="center">Investor</th>--%>
            <th>Amount</th>
        </tr>
        <c:forEach items="${investments}" var="investment">
            <tr align="center">
                    <%--<td width="50%" align="left">${startup.name}</td>--%>
                <td>${investment.amount}</td>
            </tr>
        </c:forEach>
    </table>
</div>


