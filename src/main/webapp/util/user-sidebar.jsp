<div class="col-sm-3 sidenav">

    <ul class="nav nav-pills nav-stacked">
        <li class="active">

            <button class="btn btn-info" data-toggle="collapse" data-target="#hide-me">Choose an account</button>
            <div id="hide-me" class="collapse in">
                <label for="sort-by"><fmt:message key="sidebar.sortBy" /></label>

                <select class="form-control" id="sort-by">
                    <option value="balance"><fmt:message key="sidebar.sortBy.balance" /></option>
                    <option value="type"><fmt:message key="sidebar.sortBy.type" /></option>
                    <option value="time"><fmt:message key="sidebar.sortBy.creationTime" /></option>
                </select>

                <br>
                <ul class="nav nav-pills nav-stacked">
                    <c:forEach var="account" items="${sessionScope.accounts}">
                        <li class="active">
                            <form method="post" action="/select-account">
                                <button class="btn btn-info" type="submit" name="${account.number}" value="${account.number}">
                                    <c:out value="${account.number}"/> : <c:out value="${account.balance}"/>
                                </button>
                            </form>
                            <h5 class="header-panel">
                                ---------------------------
                            </h5>
                        </li>
                    </c:forEach>
                </ul
            </div>

        </li>

        <li class="active"><a href="../view/account-info.jsp"><fmt:message key="sidebar.accountInfo" /></a></li>
        <li><a href="#section2"><fmt:message key="sidebar.doOperation" /></a></li>
        <li><a href="../view/operation-history.jsp"><fmt:message key="sidebar.operationHistory" /></a></li>
        <li><a href="../view/add-user-to-account.jsp"><fmt:message key="sidebar.addUserToAccount" /></a></li>
        <li><a href="../view/open-account.jsp"><fmt:message key="sidebar.openAccount" /></a></li>
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