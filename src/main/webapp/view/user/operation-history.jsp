<%@ include file="../../util/head.jsp" %>

<html>
<body>
<%@ include file="../../util/nav.jsp" %>
<%@ include file="../../util/sidebar.jsp" %>

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

            </div>
        </div>
    </div>
</div>
<%@ include file="../../util/footer.jsp" %>
</body>
</html>