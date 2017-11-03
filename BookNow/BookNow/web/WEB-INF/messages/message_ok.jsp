<!--
Author: W3layouts
Author URL: http://w3layouts.com
License: Creative Commons Attribution 3.0 Unported
License URL: http://creativecommons.org/licenses/by/3.0/
-->
<!DOCTYPE html>
<html>
    <jsp:include page="/WEB-INF/js_fragments/header.jsp"/>

    <body>
        <!--header starts-->
        <div class="header">
            <jsp:include page="/WEB-INF/js_fragments/menubar.jsp"/>
        </div>
        <!---->
        <div class="main_bg">
            <div class="container">
                <div class="main">
                    <div class="details">
                        <a href="#"><h2>Welcome ${my_name} !!! </h2></a>
                        <div>
                            <p class="para">You have sent the message successfully!!!</p>
                            <p class="para">Don't forget to check our latest offers !!!!!!</p>
                            
                            <div class="read_more">
                                <center><a href="${pageContext.request.contextPath}/contact/inbox">you can check your inbox whenever you want!!!</a></center>
                            </div>
                        </div>
                    </div>
                </div>		
            </div>
        </div>	
        <!---->
        <jsp:include page="/WEB-INF/js_fragments/banner_2.jsp"/>    

        <jsp:include page="/WEB-INF/js_fragments/footer.jsp"/>
        <!---->

    </body>
</html>