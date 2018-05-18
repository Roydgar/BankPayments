<%@ include file="/util/head.jsp" %>
<html>
<body>
<%@ include file="/util/nav.jsp" %>
<%@ include file="/util/sidebar.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">

            <h4  class="error"><c:out value="${wrongUserInputMessage}"/> </h4>

            <form class="form-signin" action="/do-operation">
                <label for="payerAccount"><fmt:message key="doOperation.chooseAccount" /> </label>

                <select class="form-control" id="payerAccount" name="payerAccount">

                    <c:forEach var="account" items="${sessionScope.accounts}">
                        <option value="${account.number}"><c:out value="${account.number}"/> : <c:out value="${account.balance.getNumber() / 100} $"/></option>
                    </c:forEach>

                </select>

                <input type="text" class="form-control" placeholder="<fmt:message key="doOperation.placeholder.recipient" /> " name="recipientAccount" required >

                <input type="text"  pattern="[0-9]+(\.[0-9]{0,2})?%?" class="form-control" placeholder="<fmt:message key="doOperation.placeholder.moneyAmount" /> " name="moneyAmount" required >

                <button class="btn btn-lg btn-primary btn-block" type="submit">
                    <fmt:message key="doOperation.button.confirm" /> </button>
            </form>
        </div>
    </div>
</div>


<%@ include file="/util/footer.jsp" %>
</body>
</html>