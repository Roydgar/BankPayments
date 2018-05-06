<%@ include file="../util/head.jsp" %>

<html>
<body>
<%@ include file="../util/nav.jsp" %>

<div class="container-fluid">
    <div class="row content">
        <div class="col-sm-3 sidenav">

            <ul class="nav nav-pills nav-stacked">
                <li class="active"><button class="btn btn-info" data-toggle="collapse"
                                           data-target="#hide-me">Choose an account</button>
                    <div id="hide-me" class="collapse in">
                        <label for="sort-by">Sort by</label>
                        <select class="form-control" id="sort-by">
                            <option value="balance">Balance</option>
                            <option value="type">Type</option>
                            <option value="time">Creation time</option>
                        </select>
                        <ul class="list-group">
                            <c:forEach var="account" items="${sessionScope.accounts}">
                                <li class="list-group-item">account.number  :  account:balance</li>
                            </c:forEach>
                        </ul>
                    </div></li>
                <li class="active"><a href="#section1">Info</a></li>
                <li><a href="#section2">Do operation</a></li>
                <li><a href="#section3">Operation history</a></li>
                <li><a href="#section4">Add an user to your account</a></li>
                <li><a href="open-account.jsp">Open a new account</a></li>
                <li>   -----------------------------------------------------------------------------------------</li>
                <li><a href="open-account.jsp">Confirm credit request</a></li>
                <li><a href="open-account.jsp">Add new admin</a></li>
            </ul><br>
            <div class="input-group">
                <input type="text" class="form-control" placeholder="Search">
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