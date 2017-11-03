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
                        <a href="#"><h2>Your room has been updated!!! </h2></a>
                        <div>
                            <p class="para">You can update your room whenever you want!!!</p>

                            <div class="read_more">
                                <center><a href="${pageContext.request.contextPath}/host/rooms/properties?id=${obj.id}">Here you can see your room with updated properties!!</a></center>
                            </div>
                        </div>
                    </div>
                </div>		
            </div>
        </div>	
        <jsp:include page="/WEB-INF/js_fragments/banner_2.jsp"/>    

    </body>
</html>