<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/util/head.jsp" %>

<html>
<body>
<%@ include file="/util/nav.jsp" %>

<div class="container-fluid">
    <div class="row content">
        <%@ include file="/util/sidebar.jsp" %>
        <div class="container text-center">

            <div class="row">
                <div class="col-lg-6">

                    <form method="post" action="/sort-accounts">
                        <label for="sortBy"><fmt:message key="sortBy" /></label>
                        <select class="form-control" id="sortBy" name="sortBy">
                            <option value="balance"><fmt:message key="sortBy.balance" /></option>
                            <option value="type"><fmt:message key="sortBy.type" /></option>
                            <option value="date"><fmt:message key="sortBy.creationTime" /></option>
                        </select>

                        <button class="btn btn-info" type="submit"><fmt:message key="sortBy.button.sort" /></button>
                    </form>


                    <ul class="list-group">
                        <c:forEach var="account" items="${sessionScope.accounts}">
                            <li class="list-group-item">
                                <button class="btn btn-info" data-toggle="collapse" data-target="#${account.number}">
                                    <c:out value="${account.number}"/> :  <c:out value="${account.balance}"/>
                                </button>

                                <div id="${account.number}" class="collapse">
                                    <p>
                                        <fmt:message key="accountInfo.created" /> <c:out value="${account.creationDate}"/> ; <fmt:message key="accountInfo.validityTo" />  <c:out value="${account.validityDate}"/>
                                        <br>
                                        <fmt:message key="accountInfo.rate" />  <c:out value="${account.rate}"/> ; <fmt:message key="accountInfo.accruedInterest" />   <c:out value="${account.accruedInterest}"/>
                                        <br>
                                        <fmt:message key="accountInfo.type" />   <c:out value="${account.type}"/>
                                    </p>
                                </div>
                            </li>
                        </c:forEach>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>


    <%@ include file="/util/footer.jsp" %>
</body>
</html>
