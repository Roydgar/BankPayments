<%@ include file="/util/head.jsp" %>


<html>

<body>

<%@ include file="/util/nav.jsp" %>
<%@ include file="/util/sidebar.jsp" %>

<div class="container">
    <div class="row">
        <div class="col-sm-6 col-md-4 col-md-offset-4">
            <h1 class="text-center login-title"><fmt:message key="openAccount.title" /></h1>
            <div class="account-wall">

                <h4  class="error"><c:out value="${wrongUserInputMessage}"/> </h4>

                <form class="form-signin" action="/open-account">
                    <label for="account-type"><fmt:message key="openAccount.accountType" /> </label>

                    <select class="form-control" id="account-type" name="account-type">
                        <option value="checking"><fmt:message key="accountType.checking" /> </option>
                        <option value="credit"><fmt:message key="accountType.credit" /></option>
                        <option value="deposit"><fmt:message key="accountType.deposit" /> </option>
                    </select>

                    <script >
                        $(document).ready(function() {
                            $.viewMap = {
                                'checking' : $([]),
                                'credit' : $('#money-amount'),
                                'deposit' : $('#money-amount')
                            };

                            $('#account-type').change(function() {
                                // hide all
                                $.each($.viewMap, function() { this.hide(); });
                                // show current
                                $.viewMap[$(this).val()].show();
                            });
                        });
                    </script>

                    <div id="money-amount">
                        <input type="text"  pattern="[0-9]+(\.[0-9]{0,2})?%?" class="form-control" placeholder="<fmt:message key="openAccount.placeholder.moneyAmount" /> " name="moneyAmount" required >
                    </div>



                    <button class="btn btn-lg btn-primary btn-block" type="submit">
                        <fmt:message key="openAccount.button.confirm" /> </button></form>
            </div>
        </div>
    </div>
</div>

</body>
</html>
