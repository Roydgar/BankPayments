<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/util/head.jsp" %>
<html>

<body>


<%@ include file="/util/nav.jsp" %>

<%@ include file="/util/sidebar.jsp"%>

<div class="container text-center">
    <div class="row">
        <div class="col-lg-6">

            <br><br><br>

            <script>
                $(document).ready( function () {
                    $('#creditRequestTable').DataTable( {
                        "language": {
                            "lengthMenu": "<fmt:message key='table.display'/> _MENU_ <fmt:message key='table.recordsPerPage'/>",
                            "zeroRecords": "<fmt:message key='table.zeroRecords'/>",
                            "info": "<fmt:message key='table.info'/> _PAGE_ <fmt:message key='table.info.of'/> _PAGES_",
                            "infoEmpty": "<fmt:message key='table.infoEmpty'/>",
                            "infoFiltered": "(<fmt:message key='table.infoFiltered'/> _MAX_)",
                            "search": "<fmt:message key='table.search'/>",
                            paginate: {
                                previous: '<<<',
                                next:     '>>>'
                            },
                            aria: {
                                paginate: {
                                    previous: 'Previous',
                                    next:     'Next'
                                }
                            }
                        },
                        "lengthMenu": [ 3, 5, 10 ],
                        pageLength: 5
                    } );
                } );
            </script>


            <form method="post" action="/confirm-credit-request">

                <table id="creditRequestTable" class="display">
                    <thead>
                        <tr>
                            <th><fmt:message key="creditRequest.moneyAmount"/></th>
                            <th><fmt:message key="creditRequest.date"/></th>
                            <th><fmt:message key="creditRequest.status"/></th>
                        </tr>
                    </thead>

                    <tbody>
                        <c:forEach var="creditRequest" items="${creditRequests}">
                            <tr>
                                <td><c:out value="${creditRequest.moneyAmount}"/></td>
                                <td><c:out value="${creditRequest.date}"/></td>
                                <td>
                                    <c:choose>
                                    <c:when test="${creditRequest.status == 'NEW'}">
                                        <button class="btn btn-info" type="submit" name="creditRequestId"  value="${creditRequest.id}:confirmed"> <fmt:message key="creditRequest.confirm" /> </button>
                                        <button class="btn btn-info" type="submit" name="creditRequestId"  value="${creditRequest.id}:denied">    <fmt:message key="creditRequest.deny" /> </button>
                                    </c:when>
                                    <c:otherwise>
                                        <c:out value="${creditRequest.status}"/>
                                    </c:otherwise>
                                    </c:choose>
                                </td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </form>
        </div>
    </div>
</div>

<%@ include file="/util/footer.jsp" %>

</body>
</html>