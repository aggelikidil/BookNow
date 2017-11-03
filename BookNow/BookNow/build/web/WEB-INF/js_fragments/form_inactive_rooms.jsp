<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix = "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>


<form action="${pageContext.request.contextPath}/host/rooms/inactive" method="POST">

    <div class="rooms text-center">
        <div class="container">
            <h3>Inactive rooms</h3>
            <div class="room-grids">
                
                <c:forEach items="${rooms}" var="obj">
                    <div class="col-md-4 room-sec">
                        <div class="ser_img">

                            <a href="${pageContext.request.contextPath}/host/rooms/properties?id=${obj.id}">
                                <img src="${obj.thumbnail_url}" />
                            </a>
                        </div>	
                        <a href="${pageContext.request.contextPath}/host/rooms/properties?id=${obj.id}"><h4>${obj.room_type} </h4></a>
                        <p>${obj.description}</p>
                        <div class="items">
                            <li><a href=""><span class="img1"> </span></a></li>
                            <li><a href=""><span class="img2"> </span></a></li>
                            <li><a href=""><span class="img3"> </span></a></li>
                            <li><a href=""><span class="img4"> </span></a></li>
                            <li><a href=""><span class="img5"> </span></a></li>
                            <li><a href=""><span class="img6"> </span></a></li>
                        </div>
                    </div>
                </c:forEach>   

                <div class="clearfix"></div>
            </div>
        </div>
    </div>

</form>	