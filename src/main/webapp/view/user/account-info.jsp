<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ include file="/util/head.jsp"%>


<html>
<body>
<%@ include file="/util/nav.jsp" %>

<div class="container-fluid">
    <div class="row content">
        <%@ include file="/util/sidebar.jsp" %>
        <div class="container text-center">
             <div class="col-lg-6">

                        <br><br><br>

                        <script>
                            $(document).ready( function () {
                                $('#accountTable').DataTable( {
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

                        <table id="accountTable" class="display">
                            <thead>
                            <tr>
                                <th><fmt:message key="accountInfo.number"/></th>
                                <th><fmt:message key="accountInfo.balance"/> </th>
                                <th><fmt:message key="accountInfo.validityTo"/></th>
                                <th><fmt:message key="accountInfo.type"/></th>
                            </tr>
                            </thead>
                            <tbody>
                            <c:forEach var="account" items="${sessionScope.accounts}">
                                <tr>
                                    <td><c:out value="${account.number}"/></td>
                                    <td><moneyFormatter:formatMoney money="${account.balance}" currencyCode="${currency}"/></td>
                                    <td><dateFormatter:formatDate language="${language}" localDateTime="${account.validityDate}"/></td>
                                    <td><c:out value="${account.type}"/> </td>
                                </tr>
                            </c:forEach>
                            </tbody>
                        </table>
             </div>
        </div>
    </div>
</div>


    <%@ include file="/util/footer.jsp" %>
</body>
</html>
