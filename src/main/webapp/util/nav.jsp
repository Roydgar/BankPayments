<nav class="navbar navbar-inverse">
    <div class="container-fluid">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target="#myNavbar">
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
                <span class="icon-bar"></span>
            </button>
            <a class="navbar-brand" href="${pageContext.request.contextPath}/index.jsp"><fmt:message key="navbar.index" /></a>
        </div>
        <div class="collapse navbar-collapse" id="myNavbar">
            <ul class="nav navbar-nav">
                <li>
                    <c:choose>
                        <c:when test="${loggedUserRole == 'ADMIN'}">
                            <a href="${pageContext.request.contextPath}/view/admin/admin-menu.jsp"> <fmt:message key="navbar.adminPanel" /> </a>
                        </c:when>
                        <c:when test="${loggedUserRole == 'USER'}">
                            <a href="${pageContext.request.contextPath}/view/user/user-menu.jsp"> <fmt:message key="navbar.userPanel" /></a>
                        </c:when>
                    </c:choose>
                </li>
                <li><a href="${pageContext.request.contextPath}/about.jsp"> <fmt:message key="navbar.about" /> </a></li>
                <li><a href="${pageContext.request.contextPath}/contacts.jsp"> <fmt:message key="navbar.contacts" /> </a></li>
                <li>
                    <form>
                        <label class="label" for="language">Language</label>
                        <br>
                        <select class="nav-select" id="language" name="language" onchange="submit()">
                            <option value="en" ${language == 'en' ? 'selected' : ''}>English</option>
                            <option value="ru" ${language == 'ru' ? 'selected' : ''}>Russian</option>
                        </select>
                    </form>
                </li>
                <li>
                    <form>
                        <label class="label" for="currency">Currency</label>
                        <br>
                        <select class="nav-select" id="currency" name="currency" onchange="submit()">
                            <option value="USD" ${currency == 'USD' ? 'selected' : ''}>USD</option>
                            <option value="EUR" ${currency== 'EUR' ? 'selected' : ''}>EUR</option>
                            <option value="RUB" ${currency == 'RUB' ? 'selected' : ''}>RUB</option>
                        </select>
                    </form>
                </li>
            </ul>
            <ul class="nav navbar-nav navbar-right">
                <c:choose>
                    <c:when test="${empty loggedUserLogin || loggedUserRole == 'UNKNOWN'}">
                        <li><a href="${pageContext.request.contextPath}/registration"> <span class="glyphicon glyphicon-user"></span>
                            <fmt:message key="index.link.registration" /></a></li>
                        <li><a href="${pageContext.request.contextPath}/login">
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

