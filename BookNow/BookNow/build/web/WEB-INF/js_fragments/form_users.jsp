<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="col-md-8 booking-form">			 
    <form action="${pageContext.request.contextPath}/admin/users" method="POST">

        <div class="rooms text-center">
            <div class="container">
                <h3>Users</h3>
                <div class="room-grids">
                    <div class="col-md-4 room-sec">
                        <a href="#"><img src="${pageContext.request.contextPath}/images/active.jpg" alt=""/></a>
                        <div class="caption">
                            <a href="${pageContext.request.contextPath}/admin/users/active">View Now</a>
                        </div>
                        <h4>Active users</h4>
                        <p>Here you can see the users who have been activated!!</p>
                        <div class="items">
                            <a href="#"><span class="img1"> </span></a>
                            <a href="#"><span class="img2"> </span></a>
                            <a href="#"><span class="img3"> </span></a>
                        </div>
                    </div>
                    <div class="col-md-4 room-sec">
                        <a href="#"><img src="${pageContext.request.contextPath}/images/inactive.jpg" alt=""/></a>
                        <div class="caption">
                            <a href="${pageContext.request.contextPath}/admin/users/inactive">View Now</a>
                        </div>
                        <h4>Inactive users </h4>
                        <p>Check the inactive users and activate them!</p>
                        <div class="items">
                            <a href="#"><span class="img1"> </span></a>
                            <a href="#"><span class="img2"> </span></a>
                            <a href="#"><span class="img3"> </span></a>
                        </div>
                    </div>
                    <div class="col-md-4 room-sec">
                        <a href="#"><img src="${pageContext.request.contextPath}/images/allusers.jpg" alt=""/></a>
                        <div class="caption">
                            <a href="${pageContext.request.contextPath}/admin/users/all">View Now</a>
                        </div>
                        <h4>All users </h4>
                        <p>Here you can see all the users!!</p>
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