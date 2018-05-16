<%@ include file="../../util/head.jsp" %>

<html>
<body>
<%@ include file="/util/nav.jsp" %>

<div class="container-fluid">
    <div class="row content">

        <div class="col-sm-3 sidenav">

            <ul class="nav nav-pills nav-stacked">
                <li><a href="${pageContext.request.contextPath}/show-accounts"><fmt:message key="sidebar.accountInfo" /></a></li>
                <li><a href="${pageContext.request.contextPath}/view/user/do-operation.jsp"><fmt:message key="sidebar.doOperation" /></a></li>
                <li><a href="${pageContext.request.contextPath}/show-operation-history"><fmt:message key="sidebar.operationHistory" /></a></li>
                <li><a href="${pageContext.request.contextPath}/view/user/add-user-to-account.jsp"><fmt:message key="sidebar.addUserToAccount" /></a></li>
                <li><a href="${pageContext.request.contextPath}/view/user/open-account.jsp"><fmt:message key="sidebar.openAccount" /></a></li>
                <li><a href="${pageContext.request.contextPath}/view/user/currency-converter.jsp"><fmt:message key="sidebar.currencyConverter" /></a></li>

                <li>--------</li>
                <li><a href="${pageContext.request.contextPath}/show-credit-requests"><fmt:message key="sidebar.admin.confirmCreditRequests" /></a></li>
                <li><a href="admin-registration.jsp"><fmt:message key="sidebar.admin.addNewAdmin" /></a></li>
            </ul><br>

            <div class="input-group">
                <input type="text" class="form-control" placeholder="<fmt:message key="sidebar.search" />">
                <span class="input-group-btn">
          <button class="btn btn-default" type="button">
            <span class="glyphicon glyphicon-search"></span>
          </button>
        </span>
            </div>
        </div>
    </div>
</div>

<div class="container text-center">
</div>

<%@ include file="/util/footer.jsp" %>
</body>
</html>