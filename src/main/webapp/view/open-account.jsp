<%@ include file="../util/head.jsp" %>

<html>

<body>
<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <h1 class="text-center login-title">Open an account</h1>
            <div class="account-wall">
                <img class="profile-img" src="https://lh5.googleusercontent.com/-b0-k99FZlyE/AAAAAAAAAAI/AAAAAAAAAAA/eu7opA4byxI/photo.jpg?sz=120"
                     alt="">
                <form class="form-signin">
                    <label for="account-type">Choose an account type</label>
                    <select class="form-control" id="account-type">
                        <option value="one">Checking</option>
                        <option value="two">Credit</option>
                        <option value="three">Deposit</option>
                    </select>
                    <button class="btn btn-lg btn-primary btn-block" type="submit">
                        Open an account</button>
                    <a href="#" class="pull-right need-help">Need help? </a><span class="clearfix"></span>
                </form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
