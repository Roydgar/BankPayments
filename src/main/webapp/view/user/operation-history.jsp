<%@ include file="../../util/head.jsp" %>

<html>
<body>
<%@ include file="/util/nav.jsp" %>
<%@ include file="/util/sidebar.jsp" %>

<div class="container-fluid">
    <div class="row content">

        <div class="container text-center">
            <div class="row ">
                <div class="center-block col-lg-6">

                    <br><br><br>

                    <script>
                        $(document).ready( function () {
                            $('#operationHistoryTable').DataTable( {
                                "language": {
                                    "lengthMenu": "<fmt:message key='table.display'/> _MENU_ <fmt:message key='table.recordsPerPage'/>",
                                    "zeroRecords": "<fmt:message key='table.zeroRecords'/>",
                                    "info": "<fmt:message key='table.info'/> _PAGE_ <fmt:message key='table.info.of'/> _PAGES_",
                                    "infoEmpty": "<fmt:message key='table.infoEmpty'/>",
                                    "infoFiltered": "(<fmt:message key='table.infoFiltered'/> _MAX_)",
                                    "search": "<fmt:message key='table.search'/>",

                                    "columnDefs": [
                                        { "width": "100px", "targets": [2, 3] }
                                    ],

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

                    <table id="operationHistoryTable" class="display">
                        <thead>
                        <tr>
                            <th><fmt:message key="operationHistory.recipient"/></th>
                            <th><fmt:message key="operationHistory.money"/></th>
                            <th><fmt:message key="operationHistory.date"/></th>
                            <th><fmt:message key="operationHistory.type"/></th>
                        </tr>
                        </thead>

                        <tbody>
                            <c:forEach var="operation" items="${operations}">
                                <tr>
                                    <td><c:out value="${operation.recipient}"/></td>
                                    <td><moneyFormatter:formatMoney money="${operation.moneyAmount}" currencyCode="${currency}"/></td>
                                    <td><dateFormatter:formatDate language="${language}" localDateTime="${operation.date}"/></td>
                                    <td><c:out value="${operation.type}"/></td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </div>
</div>
<%@ include file="/util/footer.jsp" %>
</body>
</html>