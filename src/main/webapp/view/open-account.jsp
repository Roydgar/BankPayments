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
                <form class="form-signin" action="/open-account">
                    <label for="account-type">Choose an account type</label>

                    <select class="form-control" id="account-type" name="account-type">
                        <option value="checking">Checking</option>
                        <option value="credit">Credit</option>
                        <option value="deposit">Deposit</option>
                    </select>

                    <script >
                        $(document).ready(function() {
                            $.viewMap = {
                                'checking' : $([]),
                                'credit' : $('#checking-card, #money-amount'),
                                'deposit' : $('#checking-card, #money-amount')
                            };

                            $('#account-type').change(function() {
                                // hide all
                                $.each($.viewMap, function() { this.hide(); });
                                // show current
                                $.viewMap[$(this).val()].show();
                            });
                        });
                    </script>

                    <div id="checking-card">
                        <select class="form-control" id="chosenAccount" name="chosenAccount">
                            <c:forEach var="account" items="${sessionScope.accounts}">
                                <option value="${account.number}"><c:out value="${account.number}"/></option>
                            </c:forEach>
                        </select>
                    </div>

                    <div id="money-amount">
                        <input type="text" class="form-control" placeholder="Money amount" name="moneyAmount">
                    </div>



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
