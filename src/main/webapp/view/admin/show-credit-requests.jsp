<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/util/head.jsp" %>
<html>

<body>


<%@ include file="/util/nav.jsp" %>

<%@ include file="/util/sidebar.jsp"%>

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


            <form method="post" action="/confirm-credit-request">

                <ul class="list-group">
                    <c:forEach var="creditRequest" items="${creditRequests}">
                        <li class="list-group-item">
                            <c:out value="${creditRequest.moneyAmount}"/> : <c:out value="${creditRequest.date}"/>

                            <c:choose>
                                <c:when test="${creditRequest.status == 'NEW'}">
                                    <button class="btn btn-info" type="submit" name="creditRequestId"  value="${creditRequest.id}:confirmed"> Confirm </button>
                                    <button class="btn btn-info" type="submit" name="creditRequestId"  value="${creditRequest.id}:denied">    Deny </button>
                                </c:when>
                                <c:otherwise>
                                    <c:out value="${creditRequest.status}"/>
                                </c:otherwise>
                            </c:choose>
                        </li>
                    </c:forEach>
                </ul>
            </form>
        </div>
    </div>
</div>

<%@ include file="/util/footer.jsp" %>

</body>
</html>