<%@ include file="../util/head.jsp" %>

<html>
<body>
<%@ include file="../util/nav.jsp" %>

<div class="container-fluid">
    <div class="row content">
        <div class="col-sm-3 sidenav">

            <form class="nav nav-pills nav-stacked">

                <form method="post" action="/show-accounts">
                <li class="active"><button class="btn btn-info" data-toggle="collapse"
                            data-target="#hide-me"  type="submit"><fmt:message key="sidebar.chooseAnAccount" /></button>
                </form>
                    <div id="hide-me" class="collapse in">
                    </div></li>
                <li class="active"><a href="/show-accounts"><fmt:message key="sidebar.accountInfo" /></a></li>
                <li><a href="#section2"><fmt:message key="sidebar.doOperation" /></a></li>
                <li><a href="#section3"><fmt:message key="sidebar.operationHistory" /></a></li>
                <li><a href="#section4"><fmt:message key="sidebar.addUserToAccount" /></a></li>
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
            </form>
        </div>

        <div class="container text-center">
            <div class="row">
                <div class="col-sm-4">

                    <label for="sort-by"><fmt:message key="sidebar.sortBy" /></label>

                    <select class="form-control" id="sort-by">
                        <option value="balance"><fmt:message key="sidebar.sortBy.balance" /></option>
                        <option value="type"><fmt:message key="sidebar.sortBy.type" /></option>
                        <option value="time"><fmt:message key="sidebar.sortBy.creationTime" /></option>
                    </select>

                    <br>
                    <c:forEach var="account" items="${sessionScope.accounts}">
                        <h4 class="header-panel" >
                            <c:out value="${account.number}"/> : <c:out value="${account.balance}"/>
                            <br>
                            Created: <c:out value="${account.creationDate}"/> ; Valid to <c:out value="${account.validityDate}"/>
                            <br>
                            Type :   <c:out value="${account.type}"/> <br>
                            ---------------------------
                        </h4>
                    </c:forEach>
                </div>
            </div>
        </div>
<%@ include file="../util/footer.jsp" %>
</body>
</html>