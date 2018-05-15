<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../util/head.jsp" %>

<html>
<body>
<%@ include file="../util/nav.jsp" %>

<div class="container-fluid">
    <div class="row content">

        <%@ include file="../util/sidebar.jsp" %>

            <div class="container text-center">
                <div class="row">
                    <div class="col-lg-6">

                        <form method="post" action="/sort-accounts">
                            <label for="sortBy"><fmt:message key="sidebar.sortBy" /></label>
                            <select class="form-control" id="sortBy" name="sortBy">
                                <option value="balance"><fmt:message key="sidebar.sortBy.balance" /></option>
                                <option value="type"><fmt:message key="sidebar.sortBy.type" /></option>
                                <option value="date"><fmt:message key="sidebar.sortBy.creationTime" /></option>
                            </select>

                            <button class="btn btn-info" type="submit">Sort</button>
                        </form>

                        <c:set var="totalCount" scope="session" value="${fn:length(accounts)}"/>
                        <c:set var="perPage" scope="session"  value="${4}"/>
                        <c:set var="pageStart" value="${param.start}"/>

                        <c:if test="${empty pageStart or pageStart < 0}">
                            <c:set var="pageStart" value="0"/>
                        </c:if>
                        <c:if test="${totalCount < pageStart}">
                            <c:set var="pageStart" value="${pageStart - perPage}"/>
                        </c:if>

                        <a href="?start=${pageStart - perPage}"><<</a>${pageStart + 1} - ${pageStart + perPage}
                        <a href="?start=${pageStart + perPage}">>></a>

                        <ul class="list-group">
                            <c:forEach var="account" items="${sessionScope.accounts}"
                                       begin="${pageStart}" end="${pageStart + perPage - 1}">
                                <li class="list-group-item">
                                    <button class="btn btn-info" data-toggle="collapse" data-target="#${account.number}">
                                        <c:out value="${account.number}"/> :  <c:out value="${account.balance}"/>
                                    </button>

                                    <div id="${account.number}" class="collapse">
                                            <p>
                                                Created: <c:out value="${account.creationDate}"/> ; Validity to:  <c:out value="${account.validityDate}"/>
                                                <br>
                                                Rate: <c:out value="${account.rate}"/> ; Accrued interest:  <c:out value="${account.accruedInterest}"/>
                                                <br>
                                                Type:  <c:out value="${account.type}"/>
                                            </p>
                                    </div>
                                </li>
                            </c:forEach>
                        </ul>
                    </div>
            </div>
        </div>
</div>

    <%@ include file="../util/footer.jsp" %>
</body>
</html>
