<%@ include file="../util/head.jsp" %>

<html>
<body>
<%@ include file="../util/nav.jsp" %>

<div class="container-fluid">
    <div class="row content">

        <%@ include file="../util/user-sidebar.jsp" %>

        <div class="container text-center">
            <div class="row">
                <div class="col-sm-4">
                    <p class="header-panel">

                        <c:if test="${empty selectedAccount}">
                                Please, select an account.
                        </c:if>
                        <c:if test="${not empty selectedAccount}">
                            <c:out value="${selectedAccount.number}"/> :  <c:out value="${selectedAccount.balance}"/>
                            <br>
                            Created: <c:out value="${selectedAccount.creationDate}"/> ; Validity to:  <c:out value="${selectedAccount.validityDate}"/>
                            <br>
                            Rate: <c:out value="${selectedAccount.rate}"/> ; Accrued interest:  <c:out value="${selectedAccount.accruedInterest}"/>
                            <br>
                            Type:  <c:out value="${selectedAccount.type}"/>
                        </c:if>
                    </p>
                </div>
            </div>
        </div>
    </div>
</div>
    <%@ include file="../util/footer.jsp" %>
</body>
</html>
