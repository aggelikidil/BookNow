<!DOCTYPE html>
<html>
    <jsp:include page="/WEB-INF/js_fragments/header.jsp"/>

    <body>
        <div class="header">
            <jsp:include page="/WEB-INF/js_fragments/menubar.jsp"/>
        </div>

        <div class="contact-bg2">
            <div class="container">
                <div class="booking">
                    <h3>Register</h3>

                    <jsp:include page="/WEB-INF/js_fragments/form_register.jsp"/>

                    <jsp:include page="/WEB-INF/js_fragments/latest.jsp"/>
                </div>
            </div>
        </div>

        <jsp:include page="/WEB-INF/js_fragments/banner_2.jsp"/>
      
    </body>
</html>