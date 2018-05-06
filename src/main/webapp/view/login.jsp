
<%@ include file="../util/head.jsp" %>
<html>
<body>
<%@ include file="../util/nav.jsp" %>
<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <h1 class="text-center login-title">Sign in</h1>
            <div class="account-wall">
                <img class="profile-img" src="https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=120"
                     alt="">

                <form method="post" action="/login">
                    <input type="text" class="form-control" placeholder="Login" id = "Login" name="login" required autofocus>
                    <input type="password" class="form-control" placeholder="Password" id = "Password"  name="password"  required>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">
                        Sign in</button>
                    <a href="#" class="pull-right need-help">Need help? </a><span class="clearfix"></span>
                </form>

            </div>
            <a href="../view/registration.jsp" class="text-center new-account">Create an account </a>
        </div>
    </div>
</div>


<%@ include file="../util/footer.jsp" %>
</body>
</html>