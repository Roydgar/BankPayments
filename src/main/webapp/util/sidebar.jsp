<div class="col-sm-3 sidenav">

    <ul class="nav nav-pills nav-stacked">
        <li class="active"><a href="${pageContext.request.contextPath}/view/user/account-info.jsp"><fmt:message key="sidebar.accountInfo" /></a></li>
        <li><a href="${pageContext.request.contextPath}/view/user/do-operation.jsp"><fmt:message key="sidebar.doOperation" /></a></li>
        <li><a href="${pageContext.request.contextPath}/view/user/operation-history.jsp"><fmt:message key="sidebar.operationHistory" /></a></li>
        <li><a href="${pageContext.request.contextPath}/view/user/add-user-to-account.jsp"><fmt:message key="sidebar.addUserToAccount" /></a></li>
        <li><a href="${pageContext.request.contextPath}/view/user/open-account.jsp"><fmt:message key="sidebar.openAccount" /></a></li>
        <li><a href="${pageContext.request.contextPath}/view/user/currency-converter.jsp">Currency converter</a></li>
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