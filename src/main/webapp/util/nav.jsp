<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="#">Logo</a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li>
                    <c:choose>
                        <c:when test="${loggedUserRole == 'ADMIN'}">
                            <a href="/view/admin/admin-menu.jsp"> Admin panel </a>
                        </c:when>
                        <c:when test="${loggedUserRole == 'USER'}">
                            <a href="/view/user/user-menu.jsp"> Home </a>
                        </c:when>
                    </c:choose>
                </li>
                <li><a href="#"> <fmt:message key="navbar.about" /> </a></li>
                <li><a href="#"> <fmt:message key="navbar.contacts" /> </a></li>
                <li>
                    <br>
                    <select class="form-control" id="language" name="language" onchange="submit()">
                        <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                        <option value="ru" ${language == 'ru' ? 'selected' : ''}>Russian</option>
                    </select>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">

                <c:choose>
                    <c:when test="${empty loggedUserLogin || loggedUserRole == 'UNKNOWN'}">
                        <li><a href="/view/login/registration.jsp"> <span class="glyphicon glyphicon-user"></span>
                            <fmt:message key="index.link.registration" /></a></li>
                        <li><a href="/view/login/login.jsp">
                            <span class="glyphicon glyphicon-log-in"></span> <fmt:message key="index.link.login" /></a></li>
                    </c:when>

                    <c:otherwise>
                        <li><a href="/logout"><span class="glyphicon glyphicon-log-in"></span> <fmt:message key="navbar.logout" /> </a></li>
                    </c:otherwise>
                </c:choose>
            </ul>
        </div>
    </div>
</nav>

