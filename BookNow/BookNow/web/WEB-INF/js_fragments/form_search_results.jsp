<%@ taglib prefix = "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>   

<div class="contact-bg2">
    <div class="container">
        <div class="booking">
            <h3>Advanced Search</h3>

            <jsp:include page="/WEB-INF/js_fragments/form_advanced_search.jsp"/>
        </div>
    </div>
</div>

<div class="contact-bg2">
    <div class="container">
        <div class="booking">
            <c:if test="${empty rooms}">
                <div class="col-md-4 room-sec">
                    <h3>No results found</h3>
                </div>
            </c:if>

            <c:if test="${not empty rooms}">
                <div class="col-md-4 room-sec">
                    <h3>Results:</h3>
                </div>
            </c:if>
        </div>
    </div>
</div>

<div class="rooms text-center">
    <div class="container">       


        <div class="room-grids">
            <c:forEach items="${rooms}" var="obj">
                <div class="col-md-4 room-sec">
                    <div class="ser_img">
                        <a href="${pageContext.request.contextPath}/search/results/details?id=${obj.id}">
                            <img src="${obj.thumbnail_url}" />
                        </a>
                    </div>	
                    <div class="caption">
                        <span>${obj.offerdetails.cost_per_day}$</span>
                        <c:if test="${not empty my_username}">
                            <a href="${pageContext.request.contextPath}/rentings/create?id=${obj.id}">Book Now</a>
                        </c:if>
                    </div>
                    <br/>
                    <br/>
                    <a href="${pageContext.request.contextPath}/search/results/details?id=${obj.id}"><h4><b><i>${obj.room_type}</i></b></h4></a>
                    <p class="para">Currently: ${obj.offerdetails.cost_per_day}$</p>
                    <p class="para">Beds: ${obj.details.beds}</p>                    

                    <div class="items">
                        <a href="#"><span class="img1"> </span></a>
                        <a href="#"><span class="img2"> </span></a>
                        <a href="#"><span class="img3"> </span></a>
                        <a href="#"><span class="img4"> </span></a>
                        <a href="#"><span class="img5"> </span></a>
                        <a href="#"><span class="img6"> </span></a>
                    </div>
                </div>
            </c:forEach>
            <div class="clearfix"></div>
        </div>
    </div>
</div>