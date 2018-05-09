<div class="col-sm-3 sidenav">

    <ul class="nav nav-pills nav-stacked">

        <li class="active"><a href="../view/account-info.jsp"><fmt:message key="sidebar.accountInfo" /></a></li>
        <li><a href="#section2"><fmt:message key="sidebar.doOperation" /></a></li>
        <li><a href="#section3"><fmt:message key="sidebar.operationHistory" /></a></li>
        <li><a href="#section4"><fmt:message key="sidebar.addUserToAccount" /></a></li>
        <li><a href="../view/open-account.jsp"><fmt:message key="sidebar.openAccount" /></a></li>
        <li>   -----------------------------------------------------------------------------------------</li>
        <form method="post" action="/show-credit-requests">
            <li> <button class="btn btn-link" type="submit">
                <fmt:message key="sidebar.admin.confirmCreditRequests" /> </button></li>
        </form>
        <li><a href="../view/admin-registration.jsp"><fmt:message key="sidebar.admin.addNewAdmin" /></a></li>
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