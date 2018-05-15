
<%@ include file="/util/head.jsp" %>
<html>
<body>
<%@ include file="/util/nav.jsp" %>
<%@ include file="/util/sidebar.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <h1 class="text-center login-title">Add an user to account</h1>
            <div class="account-wall">
                <img class="profile-img" src="https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=120"
                     alt="">

                <h4  class="error"><c:out value="${wrongUserInputMessage}"/> </h4>

                <form method="post" action="/add-user-to-account">
                    <label for="chosen-account">Choose an account</label>

                    <select class="form-control" id="chosen-account" name="chosenAccount">
                        <c:forEach var="account" items="${sessionScope.accounts}">
                            <option value="${account.number}"><c:out value="${account.number}"/></option>
                        </c:forEach>
                    </select>

                    <input type="text" class="form-control" placeholder=" <fmt:message key="login.placeholder.login" />" id = "Login" name="login" required autofocus>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">
                       Confirm</button>
                    <a href="#" class="pull-right need-help"><fmt:message key="login.link.help" /></a><span class="clearfix"></span>
                </form>

            </div>
            <a href="${pageContext.request.contextPath}/view/login/registration.jsp" class="text-center new-account"><fmt:message key="login.link.registration" /> </a>
        </div>
    </div>
</div>


<%@ include file="/util/footer.jsp" %>
</body>
</html>