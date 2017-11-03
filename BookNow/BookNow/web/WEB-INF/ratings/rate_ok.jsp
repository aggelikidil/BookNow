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
        <div class="header">
            <jsp:include page="/WEB-INF/js_fragments/menubar.jsp"/>
        </div>
        <div class="main_bg">
            <div class="container">
                <div class="main">
                    <div class="details">
                        <a href="#"><h2>Welcome ${my_name} !!! </h2></a>
                        <div>
                            <p class="para">You have rated successfully!!!</p>
                            <p class="para">Don't forget to check our latest offers !!!!!!</p>
                            
                            <div class="read_more">
                                <center><a href="${pageContext.request.contextPath}/rating">you can rate a host or a room whenever you want!!!</a></center>
                            </div>
                        </div>
                    </div>
                </div>		
            </div>
        </div>	
        <jsp:include page="/WEB-INF/js_fragments/banner_2.jsp"/>    


    </body>
</html>