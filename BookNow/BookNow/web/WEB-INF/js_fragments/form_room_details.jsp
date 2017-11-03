<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<div class="col-md-8 booking-form">			 

    <h4>We are here</h4>

    <div class="map">
        <iframe 
            width="100%" 
            height="300" 
            frameborder="0" 
            scrolling="no" 
            marginheight="0" 
            marginwidth="0" 
            src="https://maps.google.co.in/maps?f=q&amp;source=s_q&amp;hl=en&amp;ie=UTF8&amp;hq=&amp;ll=${ROOM.latitude},${ROOM.longitude}&amp;&amp;z=4&amp;output=embed"></iframe>
        <br>
        <small>
            <a  href="https://maps.google.co.in/maps?f=q&amp;source=embed&amp;hl=en&amp;geocode=&amp;sll=${ROOM.latitude},${ROOM.longitude}&amp;sspn=0.04941,0.104628&amp;ie=UTF8&amp;hq=&amp;t=m&amp;z=14&amp;ll=${ROOM.latitude},${ROOM.longitude}" style="color: #242424;text-shadow: 0 1px 0 #ffffff;text-align: left;font-size: 0.7125em;padding: 5px;font-weight: 600;">View Larger Map</a></small>
    </div>

    <ul>        
        <h5>DATE FROM</h5>
        <div class="book_date">
            <input class="date" placeholder="select from date" id="datepicker" name="from" type="text" value="${OFFER.date_from}" onfocus="this.value = '';" onblur="if (this.value == '')  {
                    this.value = '' readonly ="true" disabled="true";
                   }">
        </div>
        <br/>
        <h5>DATE TO</h5>
        <div class="book_date">
            <input class="date" placeholder="select to date" id="datepicker1" name="to" type="text" value="${OFFER.date_to}" onfocus="this.value = '';" onblur="if (this.value == '') {
                    this.value = ''  readonly ="true" disabled="true";
                   }">
        </div>
        <br/>
        <input type="hidden" name="id" value="${id}" />
    </ul>



    <c:if test="${DETAILS.smoking}" >
        <input type="checkbox" name="rules" value="smoke" checked="true" readonly="true" disabled="true"> SMOKING<br>
    </c:if>
    <c:if test="${not DETAILS.smoking}" >
        <input type="checkbox" name="rules" value="smoke" readonly="true" disabled="true"> SMOKING<br>
    </c:if>

    <c:if test="${DETAILS.pets}" >
        <input type="checkbox" name="rules" value="pets" checked="true" readonly="true" disabled="true"> PETS<br>
    </c:if>
    <c:if test="${not DETAILS.pets}" >
        <input type="checkbox" name="rules" value="pets" readonly="true" disabled="true"> PETS<br>
    </c:if>

    <c:if test="${DETAILS.event}" >
        <input type="checkbox" name="rules" value="event" checked="true" readonly="true" disabled="true"> EVENTS<br>
    </c:if>
    <c:if test="${not DETAILS.event}" >
        <input type="checkbox" name="rules" value="event" readonly="true" disabled="true"> EVENTS<br>
    </c:if>

    <c:if test="${ROOM.living_room}" >
        <input type="checkbox" name="properties" value="living_room" checked="true" readonly="true" disabled="true"> LIVING ROOM<br>
    </c:if>
    <c:if test="${not ROOM.living_room}" >
        <input type="checkbox" name="properties" value="living_room" readonly="true" disabled="true">LIVING ROOM<br>
    </c:if>


    <c:if test="${ROOM.kitchen}" >
        <input type="checkbox" name="properties" value="kitchen" checked="true" readonly="true" disabled="true">KITCHEN<br>
    </c:if>
    <c:if test="${not ROOM.kitchen}" >
        <input type="checkbox" name="properties" value="kitchen" readonly="true" disabled="true">KITCHEN<br>
    </c:if>
    <br/>


    <h5><b>BEDS</b></h5>
    <input type="text" value="${DETAILS.beds}" class="time" readonly="true" disabled="true">
    <br/>
    <h5><b>BATHROOMS</b></h5>
    <input type="text" value="${ROOM.bathrooms}" class="time" readonly="true" disabled="true">
    <br/>
    <h5><b>BEDROOMS</b></h5>
    <input type="text" value="${ROOM.bedrooms}" class="time" readonly="true" disabled="true">
    <br/>
    <h5><b>TYPE</b></h5>
    <input type="text" value="${ROOM.room_type}" class="time" readonly="true" disabled="true">
    <br/>
    <h5><b>AREA</b></h5>
    <input type="text" value="${ROOM.area}" class="time" readonly="true" disabled="true">
    <br/>
    <h5><b>MINIMUM DATES</b></h5>
    <input type="text" value="${DETAILS.minimum_dates}" class="time" readonly="true" disabled="true">
    <br/>
    <h5><b>ADDRESS</b></h5>
    <input type="text" value="${ROOM.address}" class="time" readonly="true" disabled="true">
    <br/>
    <h5><b>NEIGHBOURHOOD</b></h5>
    <input type="text" value="${DETAILS.neighbourhood}" class="time" readonly="true" disabled="true">
    <br/>
    <h5><b>PUBLIC TRANSPORT</b></h5>
    <input type="text" value="${DETAILS.public_transport}" class="time" readonly="true" disabled="true">
    <br/>
    <h5><b>PHOTO URL</b></h5>
    <input type="text" value="${ROOM.thumbnail_url}" readonly="true" disabled="true">
    <br/>


    <h5><b>DESCRIPTION</b></h5>
    <input type="text" value="${ROOM.description}" readonly="true" disabled="true">
    <br/>
    <div items="${users}" var="obj">
        <a href="${pageContext.request.contextPath}/search/results/details/host?id=${ROOM.owner_id}"><h5><i><u>HOST</u></i></h5></a>
        <input type="text" value="${ROOM.owner_id}" class="time">
        <br/>
    </div>

    <c:if test="${not empty my_username}"> 
        <form action="${pageContext.request.contextPath}/rentings/create" method="GET">
            <input type="hidden" name="id" value="${ROOM.id}" />
            <input type="submit" value="Book now!">

        </form>  
    </c:if>

    <form action="${pageContext.request.contextPath}/search/results" method="GET">
        <input name="id" type="hidden" value="${ROOM.id}">
        <input type="submit" value="Back">
    </form>



</div>