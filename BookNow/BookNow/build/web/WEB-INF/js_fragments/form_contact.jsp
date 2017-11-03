
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-md-8 booking-form">			 
    <form action="${pageContext.request.contextPath}/rooms" method="POST">
        
    <div class="rooms text-center">
        <div class="container">
            <h3>Messages</h3>
                <div class="room-grids">
                    <div class="col-md-4 room-sec">
                        <a href="#"><img src="${pageContext.request.contextPath}/images/create.jpg" alt=""/></a>
                        <div class="caption">
                            <a href="${pageContext.request.contextPath}/contact/create">Create Now</a>
                        </div>
                        <h4>Create a message</h4>
                        <p>Contact with any user!!!!!</p>
                        <div class="items">
                            <a href="#"><span class="img1"> </span></a>
                            <a href="#"><span class="img2"> </span></a>
                            <a href="#"><span class="img3"> </span></a>
                        </div>
                    </div>
                    <div class="col-md-4 room-sec">
                        <a href="#"><img src="${pageContext.request.contextPath}/images/inbox.jpg" alt=""/></a>
                        <div class="caption">
                            <a href="${pageContext.request.contextPath}/contact/inbox">View Now</a>
                        </div>
                        <h4>Inbox</h4>
                        <p>Check your inbox</p>
                        <div class="items">
                            <a href="#"><span class="img1"> </span></a>
                            <a href="#"><span class="img2"> </span></a>
                            <a href="#"><span class="img3"> </span></a>
                        </div>
                    </div>
                    <div class="col-md-4 room-sec">
                        <a href="#"><img src="${pageContext.request.contextPath}/images/outbox.jpg" alt=""/></a>
                        <div class="caption">
                            <a href="${pageContext.request.contextPath}/contact/outbox">View Now</a>
                        </div>
                        <h4>Outbox</h4>
                        <p>Check your outbox</p>
                        <div class="items">
                            <a href="#"><span class="img1"> </span></a>
                            <a href="#"><span class="img2"> </span></a>
                            <a href="#"><span class="img3"> </span></a>
                        </div>
                    </div>
                    <div class="clearfix"></div>
                    <div class="clearfix"></div>
                </div>
            </div>
        </div>   
        
    </form>			      
</div>                    