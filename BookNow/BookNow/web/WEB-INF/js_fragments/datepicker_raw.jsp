<%-- 
    Document   : menubar
    Created on : Jul 20, 2017, 1:31:03 PM
    Author     : aggeliki
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!---strat-date-piker---->
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/jquery-ui.css" />
<script src="${pageContext.request.contextPath}/js/jquery-ui.js"></script>
<script>
    $(function () {
        $("#datepicker,#datepicker1").datepicker();
    });
</script>
<!---/End-date-piker---->

<!-- Set here the key for your domain in order to hide the watermark on the web server -->
<script type="text/javascript">
    (function () {
        JC.init({
            domainKey: ''
        });
    })();
</script>