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
                        <a href="#"><h2>Your profile has been updated!!! </h2></a>
                        <div>
                            <p class="para">You can update your profile again whenever you want!!</p>
                            
                            <div class="read_more">
                                <center><a href="${pageContext.request.contextPath}/search/advanced_search">You can search for new offers right now!!</a></center>
                            </div>
                        </div>
                    </div>
                </div>		
            </div>
        </div>	
                            
        <jsp:include page="/WEB-INF/js_fragments/banner_2.jsp"/>    


    </body>
</html>