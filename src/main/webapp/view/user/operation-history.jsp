<%@ include file="../../util/head.jsp" %>

<html>
<body>
<%@ include file="/util/nav.jsp" %>
<%@ include file="/util/sidebar.jsp" %>

<div class="container-fluid">
    <div class="row content">

        <div class="container text-center">
            <div class="row ">
                <div class="center-block col-lg-6">

                    <label for="chosen-account">Choose an account</label>

                    <select class="form-control" id="chosen-account" name="chosenAccount">
                        <option value="allOperations"  selected="selected"> All operations </option>
                        <c:forEach var="account" items="${sessionScope.accounts}">
                            <option value="${account.number}"><c:out value="${account.number}"/></option>
                        </c:forEach>
                    </select>

                    <ul class="list-group">
                        <c:forEach var="operation" items="${operations}">
                            <li class="list-group-item">
                                <button class="btn btn-info" data-toggle="collapse" data-target="#${operation.id}">
                                    <c:out value="recipient :${operation.recipient}"/>; money: <c:out value="${operation.moneyAmount}"/>
                                </button>

                                <div id="${operation.id}" class="collapse">
                                    <p>
                                        When: <c:out value="${operation.date}"/> ;
                                        <br>
                                        Type:  <c:out value="${operation.type}"/>
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