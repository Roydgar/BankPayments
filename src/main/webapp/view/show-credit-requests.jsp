<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="../util/head.jsp" %>

<html>

<body>

<%@ include file="../util/nav.jsp" %>

<%@ include file="../util/sidebar.jsp"%>

<div class="container text-center">
    <div class="row">
        <div class="col-lg-6">

            <form method="post" action="/sort-credit-requests">
                <label for="sortBy"><fmt:message key="sidebar.sortBy" /></label>
                <select class="form-control" id="sortBy" name="sortBy">
                    <option value="date" selected="selected">Date</option>
                    <option value="moneyAmount"> Money amount</option>
                </select>

                <button class="btn btn-info" type="submit">Sort</button>
            </form>


            <c:set var="totalCount" scope="session" value="${fn:length(creditRequests)}"/>
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

            <form method="post" action="/confirm-credit-request">

                <ul class="list-group">
                    <c:forEach var="creditRequest" items="${creditRequests}"
                               begin="${pageStart}" end="${pageStart + perPage - 1}">
                        <li class="list-group-item">
                            <c:out value="${creditRequest.moneyAmount}"/> : <c:out value="${creditRequest.date}"/>
                            <button class="btn btn-info" type="submit" name="creditRequestId" value="${creditRequest.id}"> Confirm </button>
                        </li>
                    </c:forEach>
                </ul>
            </form>
        </div>
    </div>
</div>

<%@ include file="../util/footer.jsp" %>

</body>
</html>