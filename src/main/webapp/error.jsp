
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/util/head.jsp"%>


<html>
<body>
<%@ include file="/util/nav.jsp" %>

<div class="container-fluid">
    <div class="row content">
        <div class="container text-center">

            <a class="btn btn-default" href="${pageContext.request.contextPath}/index.jsp" role="button" style="margin-left: 5%">
                <fmt:message key="error.back" /></a>

        </div>
        </div>
</div>


<%@ include file="/util/footer.jsp" %>
</body>
</html>