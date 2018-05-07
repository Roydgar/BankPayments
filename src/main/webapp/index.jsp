<html>
<%@ include file="./util/head.jsp" %>

<body>

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

                <li class="active"><a href="#"> <fmt:message key="navbar.home" /> </a></li>
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
                <li><a href="./view/login.jsp">
                    <span class="glyphicon glyphicon-log-in"></span> <fmt:message key="index.link.login" /></a></li>
            </ul>

            <ul class="nav navbar-nav navbar-right">
                <li><a href="./view/registration.jsp"> <span class="glyphicon glyphicon-user"></span>
                    <fmt:message key="index.link.registration" /></a></li>
            </ul>
        </div>
    </div>
</nav>

<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <img src="https://placehold.it/1200x400?text=IMAGE" alt="Image">
            <div class="carousel-caption">
                <h3>Sell $</h3>
                <p>Money Money.</p>
            </div>
        </div>

        <div class="item">
            <img src="https://placehold.it/1200x400?text=Another Image Maybe" alt="Image">
            <div class="carousel-caption">
                <h3>More Sell $</h3>
                <p>Lorem ipsum...</p>
            </div>
        </div>
    </div>

    <!-- Left and right controls -->
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"></span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>

<div class="container text-center">
    <h3>What We Do</h3><br>
    <div class="row">
        <div class="col-sm-4">
            <img src="https://placehold.it/150x80?text=IMAGE" class="img-responsive" style="width:100%" alt="Image">
            <p>Шо мы делаем! 123sdaf</p>
        </div>
        <div class="col-sm-4">
            <img src="https://placehold.it/150x80?text=IMAGE" class="img-responsive" style="width:100%" alt="Image">
            <p>Project 2</p>
        </div>
        <div class="col-sm-4">
            <div class="well">
                <p><a href="view/admin-menu.jsp"> admin page </a></p>
            </div>
            <div class="well">
                <p><a href="view/user-menu.jsp"> user page </a></p>
            </div>
        </div>
    </div>

</div><br>

<%@ include file="/util/footer.jsp" %>

</body>
</html>