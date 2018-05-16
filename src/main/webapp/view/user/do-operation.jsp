<%@ include file="/util/head.jsp" %>
<html>
<body>
<%@ include file="/util/nav.jsp" %>
<%@ include file="/util/sidebar.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">

            <form class="form-signin" action="/do-operation">
                <label for="payerAccount">Choose an account</label>

                <select class="form-control" id="payerAccount" name="payerAccount">

                    <c:forEach var="account" items="${sessionScope.accounts}">
                        <c:choose>
                            <c:when test="${account.type == 'CHECKING'}">
                                <option value="${account.number}"><c:out value="${account.number}"/></option>
                            </c:when>
                        </c:choose>
                    </c:forEach>

                </select>

                <input type="text" class="form-control" placeholder="Recipient card number" name="recipientAccount" required >

                <input type="text"  pattern="[0-9]+(\.[0-9]{0,2})?%?" class="form-control" placeholder="Money amount" name="moneyAmount" required >

                <button class="btn btn-lg btn-primary btn-block" type="submit">
                    Confirm</button>
            </form>
        </div>
    </div>
</div>


<%@ include file="/util/footer.jsp" %>
</body>
</html>