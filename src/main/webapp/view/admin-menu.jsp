<%@ include file="../util/head.jsp" %>

<html>
<body>
<%@ include file="../util/nav.jsp" %>

<div class="container-fluid">
    <div class="row content">
        <div class="col-sm-3 sidenav">

            <ul class="nav nav-pills nav-stacked">
                <li class="active"><button class="btn btn-info" data-toggle="collapse"
                                           data-target="#hide-me"><fmt:message key="sidebar.chooseAnAccount" /></button>
                    <div id="hide-me" class="collapse in">
                        <label for="sort-by"><fmt:message key="sidebar.sortBy" /></label>
                        <select class="form-control" id="sort-by">
                            <option value="balance"><fmt:message key="sidebar.sortBy.balance" /></option>
                            <option value="type"><fmt:message key="sidebar.sortBy.type" /></option>
                            <option value="time"><fmt:message key="sidebar.sortBy.creationTime" /></option>
                        </select>
                        <ul class="list-group">
                            <c:forEach var="account" items="${sessionScope.accounts}">
                                <li class="list-group-item">account.number  :  account:balance</li>
                            </c:forEach>
                        </ul>
                    </div></li>
                <li class="active"><a href="#section1"><fmt:message key="sidebar.accountInfo" /></a></li>
                <li><a href="#section2"><fmt:message key="sidebar.doOperation" /></a></li>
                <li><a href="#section3"><fmt:message key="sidebar.operationHistory" /></a></li>
                <li><a href="#section4"><fmt:message key="sidebar.addUserToAccount" /></a></li>
                <li><a href="../view/open-account.jsp"><fmt:message key="sidebar.openAccount" /></a></li>
                <li>   -----------------------------------------------------------------------------------------</li>
                <li><a href="open-account.jsp"><fmt:message key="sidebar.admin.confirmCreditRequests" /></a></li>
                <li><a href="open-account.jsp"><fmt:message key="sidebar.admin.addNewAdmin" /></a></li>
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

        <div class="container text-center">

        </div>
        <%@ include file="../util/footer.jsp" %>
</body>
</html>