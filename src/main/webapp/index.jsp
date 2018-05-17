<html>
<%@ include file="./util/head.jsp" %>
<body>

<%@ include file="/util/nav.jsp" %>

<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
    </ol>

    <!-- Wrapper for slides -->
    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <img src="${pageContext.request.contextPath}/resources/img/index_img2.jpg" alt="Image">
            <div class="${pageContext.request.contextPath}carousel-caption">
                <h3><fmt:message key="index.personalAccounts" /> </h3>
                <p><fmt:message key="index.saveMoney" /></p>
            </div>
        </div>

        <div class="item">
            <img class="img-fluid d-block mx-auto" src="${pageContext.request.contextPath}/resources/img/index_img1.jpg" alt="Image">
            <div class="carousel-caption">
                <h3><fmt:message key="index.openAccounts" /></h3>
                <p><fmt:message key="index.shareMoney" /></p>
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
    <h3><fmt:message key="index.whatDoWeDo" /></h3><br>
    <div class="row">
        <div class="col-sm-4">
            <img src="${pageContext.request.contextPath}/resources/img/staff.jpg" class="img-responsive" style="width:100%" alt="Image">
            <p><fmt:message key="index.credits" /></p>
        </div>
        <div class="col-sm-4">
            <br>
            <img src="${pageContext.request.contextPath}/resources/img/proj2.jpg" class="img-responsive" style="width:100%" alt="Image">
            <br>
            <p><fmt:message key="index.money" /></p>
        </div>
    </div>

</div><br>

<%@ include file="/util/footer.jsp" %>

</body>
</html>