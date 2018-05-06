
<%@ include file="../util/head.jsp" %>

<html>

<body>

<%@ include file="../util/nav.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <h1 class="text-center login-title"><fmt:message key="registration.header" /></h1>
            <div class="account-wall">
                <img class="profile-img" src="https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=120"
                     alt="">

                <h4  class="error"><c:out value="${requestScope.wrongUserInputMessage}"/> </h4>

                <form method="post" action="/registration">

                    <input type="text" class="form-control" placeholder="<fmt:message key="registration.placeholder.login" />" id = "Login" name="login" required autofocus>
                    <input type="email" class="form-control" placeholder="<fmt:message key="registration.placeholder.email" />" id = "Email" name="email" required autofocus>
                    <input type="password" class="form-control" placeholder="<fmt:message key="registration.placeholder.password" />" id = "Password"  name="password" required>
                    <input type="password" class="form-control" placeholder="<fmt:message key="registration.placeholder.confirmPassword" />" id = "ConfirmPassword"  name="confirmPassword" required>

                    <button class="btn btn-lg btn-primary btn-block" type="submit">
                        <fmt:message key="registration.button.confirm" /></button>
                    <a href="#" class="pull-right need-help"><fmt:message key="registration.link.help" /></a><span class="clearfix"></span>
                </form>

            </div>
            <a href="../view/login.jsp" class="text-center new-account"><fmt:message key="registration.link.login" /></a>
        </div>
    </div>
</div>

<%@ include file="../util/footer.jsp" %>

</body>
</html>