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
                        <a href="#"><h2>You have logged out successfully !!! </h2></a>
                        <div>
                            <p class="para">Your sessions has ended !!!</p>
                            <p class="para">Come back later, we have great offers !!!</p>

                            <div class="read_more">
                                <center><a href="${pageContext.request.contextPath}/login">you may login whenever you want!!!</a></center>
                            </div>
                        </div>
                    </div>
                </div>		
            </div>
        </div>	

        <jsp:include page="/WEB-INF/js_fragments/banner_2.jsp"/>    

    </body>
</html>