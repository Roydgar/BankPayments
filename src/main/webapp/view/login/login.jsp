
<%@ include file="/util/head.jsp" %>
<html>
<body>
<%@ include file="/util/nav.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <h1 class="text-center login-title"><fmt:message key="login.header" /></h1>
            <div class="account-wall">
                <img class="profile-img" src="${pageContext.request.contextPath}/resources/img/photo.jpg"
                     alt="">

                <h4  class="error"><c:out value="${wrongUserInputMessage}"/> </h4>

                <form method="post" action="/login">
                    <input type="text" class="form-control" placeholder=" <fmt:message key="login.placeholder.login" />" id = "Login" name="login" required autofocus>
                    <input type="password" class="form-control" placeholder=" <fmt:message key="login.placeholder.password" />" id = "Password"  name="password"  required>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">
                        <fmt:message key="login.button.confirm" /></button>
                </form>

            </div>
            <a href="${pageContext.request.contextPath}/view/login/registration.jsp" class="text-center new-account"><fmt:message key="login.link.registration" /> </a>
        </div>
    </div>
</div>


<%@ include file="/util/footer.jsp" %>
</body>
</html>