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
                        <a href="#"><h2>Congratulations !!! You are one of us!!!</h2></a>
                        <div class="det_pic">
                            <img src="${pageContext.request.contextPath}/images/det_pic.jpg" alt="" />
                        </div>
                        <div>
                            <p class="para">You are now a member of Book Now !!!</p>
                            <p class="para">if you are a host your account needs to be activated by the administrators. Once your account has been activated, you will receive a notification e-mail!</p>
                            <p class="para">if you are a renter, you may search and book right now !!!</p>
                            
                            <div class="read_more">
                                <center><a href="${pageContext.request.contextPath}/login">login right now to gain extra bonuses!!!!!</a></center>
                            </div>
                        </div>
                    </div>
                </div>		
            </div>
        </div>	
                            
        <jsp:include page="/WEB-INF/js_fragments/banner_2.jsp"/>    

    </body>
</html>