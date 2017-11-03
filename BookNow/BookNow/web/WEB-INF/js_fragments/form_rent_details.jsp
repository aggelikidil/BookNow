<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<div class="col-md-8 booking-form">	
    <div class="contact-bg">
        <div class="container">
            <div class="contact-us">				
                <div class="contact-us_left">
                    <div class="contact-us_info">
                        <h3 class="style">Find Us Here</h3>

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
                    </div>
                    <div class="company_address">
                        <h3 class="style">Room Information :</h3>
                        <p>${ROOM.room_type}</p>
                        <p>${ROOM.address}</p>
                        <p>${DETAILS.neighbourhood}</p>
                        <br/>
                        <h4 class="style">Host Information :</h4>
                        <p>${OWNER.country}</p>

                        <p>Phone: ${OWNER.phonenumber}</p>
                        <p>Email: <a href="mailto:${OWNER.email}">${OWNER.email}</a></p>
                        <p>Follow on: <a href="#">Facebook</a>, <a href="#">Twitter</a></p>
                    </div>
                </div>				
                <div class="contact_right">
                    <div class="contact-form">
                        <h3 class="style"> </h3>

                        <c:if test="${DETAILS.smoking}" >
                            <input type="checkbox" name="smoking" value="smoking" checked="true" readonly="true" disabled="true"> SMOKING<br>
                        </c:if>
                        <c:if test="${not DETAILS.smoking}" >
                            <input type="checkbox" name="smoking" value="smoking" readonly="true" disabled="true"> SMOKING<br>
                        </c:if>

                        <c:if test="${DETAILS.pets}" >
                            <input type="checkbox" name="pets" value="pets" checked="true" readonly="true" disabled="true"> PETS<br>
                        </c:if>
                        <c:if test="${not DETAILS.pets}" >
                            <input type="checkbox" name="pets" value="pets" readonly="true" disabled="true"> PETS<br>
                        </c:if>

                        <c:if test="${DETAILS.event}" >
                            <input type="checkbox" name="event" value="event" checked="true" readonly="true" disabled="true"> EVENTS<br>
                        </c:if>
                        <c:if test="${not DETAILS.event}" >
                            <input type="checkbox" name="event" value="event" readonly="true" disabled="true"> EVENTS<br>
                        </c:if>

                        <c:if test="${ROOM.living_room}" >
                            <input type="checkbox" name="living_room" value="living_room" checked="true" readonly="true" disabled="true"> LIVING ROOM<br>
                        </c:if>
                        <c:if test="${not ROOM.living_room}" >
                            <input type="checkbox" name="living_room" value="living_room" readonly="true" disabled="true">LIVING ROOM<br>
                        </c:if>


                        <c:if test="${ROOM.kitchen}" >
                            <input type="checkbox" name="kitchen" value="kitchen" checked="true" readonly="true" disabled="true">KITCHEN<br>
                        </c:if>
                        <c:if test="${not ROOM.kitchen}" >
                            <input type="checkbox" name="kitchen" value="kitchen" readonly="true" disabled="true">KITCHEN<br>
                        </c:if>

                        <br/>

                        <h5><b>TYPE</b></h5>
                        <input type="text" value="${ROOM.room_type}" readonly="true" disabled="true"  >
                        <br/>
                        <h5><b>AREA</b></h5>
                        <input type="text" value="${ROOM.area}"  readonly="true" disabled="true">
                        <br/>
                        <h5><b>ADDRESS</b></h5>
                        <input type="text" value="${ROOM.address}" readonly="true" disabled="true">
                        <br/>
                        <h5><b>BEDS</b></h5>
                        <input type="text" value="${DETAILS.beds}" readonly="true" disabled="true">
                        <br/>
                        <h5><b>BATHROOMS</b></h5>
                        <input type="text" value="${ROOM.bathrooms}"readonly="true" disabled="true" >
                        <br/>
                        <h5><b>BEDROOMS</b></h5>
                        <input type="text" value="${ROOM.bedrooms}"  readonly="true" disabled="true">
                        <br/>
                        <h5><b>MINIMUM DATES</b></h5>
                        <input type="text" value="${DETAILS.minimum_dates}" readonly="true" disabled="true" >
                        <br/>
                        <h5><b>MAX COST</b></h5>
                        <input type="text" value="${ROOM.max_cost}" readonly="true" disabled="true" >
                        <br/>
                        <h5><b>MAX PEOPLE</b></h5>
                        <input type="text" value="${ROOM.max_people}" readonly="true" disabled="true" >
                        <br/>
                        <h5><b>MIN COST</b></h5>
                        <input type="text" value="${ROOM.min_cost}" readonly="true" disabled="true" >
                        <br/>
                        <h5><b>EXTRA COST PER PERSON</b></h5>
                        <input type="text" value="${ROOM.extra_cost_per_person}" readonly="true" disabled="true" >
                        <br/>
                        <h5><b>NEIGHBOURHOOD</b></h5>
                        <input type="text" value="${DETAILS.neighbourhood}"  readonly="true" disabled="true">
                        <br/>
                        <h5><b>PUBLIC TRANSPORT</b></h5>
                        <input type="text" value="${DETAILS.public_transport}" readonly="true" disabled="true">
                        <br/>
                        <h5><b>PHOTO URL</b></h5>
                        <input type="text" value="${ROOM.thumbnail_url}" readonly="true" disabled="true" >
                        <br/>

                        <h5><b>DESCRIPTION</b></h5>
                        <input type="text"  value="${ROOM.description}" readonly="true" disabled="true">
                        <br/>


                        <form action="${pageContext.request.contextPath}/rating/rate_room" method="GET">
                            <input type="hidden" name="id" value="${ROOM.id}" />
                            <input type="submit" value="Rate now">
                        </form>


                    </div>
                </div>		
                <div class="clear"></div>		
            </div>
        </div>	
    </div>
</div>