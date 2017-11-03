<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="col-md-8 booking-form">			 
    <form action="${pageContext.request.contextPath}/rating" method="POST">

        <div class="rooms text-center">
            <div class="container">
                <h3>Ratings</h3>
                <div class="room-grids">
                    
                    <div class="col-md-4 room-sec">
                        <a href="#"><img src="${pageContext.request.contextPath}/images/search.jpg" alt=""/></a>
                        <div class="caption">
                            <a href="${pageContext.request.contextPath}/search/advanced_search">Search Now</a>
                        </div>
                        <h4>Search for a room to rent and then rate it!!!!</h4>
                        <p>You can search our available rooms for free!!</p>
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