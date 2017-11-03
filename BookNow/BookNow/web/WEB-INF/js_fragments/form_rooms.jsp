<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="col-md-8 booking-form">			 
    <form action="${pageContext.request.contextPath}/rooms" method="POST">

        <div class="rooms text-center">
            <div class="container">
                <h3>Rooms</h3>
                <div class="room-grids">
                    <div class="col-md-4 room-sec">
                        <a href="#"><img src="${pageContext.request.contextPath}/images/active.jpg" alt=""/></a>
                        <div class="caption">
                            <a href="${pageContext.request.contextPath}/host/rooms/active">View Now</a>
                        </div>
                        <h4>Active rooms</h4>
                        <p>Here you can see your rooms that have rents!!</p>
                        <div class="items">
                            <a href="#"><span class="img1"> </span></a>
                            <a href="#"><span class="img2"> </span></a>
                            <a href="#"><span class="img3"> </span></a>
                        </div>
                    </div>
                    <div class="col-md-4 room-sec">
                        <a href="#"><img src="${pageContext.request.contextPath}/images/inactive.jpg" alt=""/></a>
                        <div class="caption">
                            <a href="${pageContext.request.contextPath}/host/rooms/inactive">View Now</a>
                        </div>
                        <h4>Inactive rooms </h4>
                        <p>Here you can see your rooms that are available!!</p>
                        <div class="items">
                            <a href="#"><span class="img1"> </span></a>
                            <a href="#"><span class="img2"> </span></a>
                            <a href="#"><span class="img3"> </span></a>
                        </div>
                    </div>
                    <div class="col-md-4 room-sec">
                        <a href="#"><img src="${pageContext.request.contextPath}/images/create.jpg" alt=""/></a>
                        <div class="caption">
                            <a href="${pageContext.request.contextPath}/host/rooms/create">Create Now</a>
                        </div>
                        <h4>Create a room </h4>
                        <p>Here you can create a new room as a host!!</p>
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