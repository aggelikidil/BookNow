<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<jsp:include page="/WEB-INF/js_fragments/datepicker_raw.jsp"/>   


<div class="col-md-8 booking-form">			 
    <form action="${pageContext.request.contextPath}/host/rooms/create" method="POST">

        <h5>ROOM TYPE</h5>
        <div class="section_room">
            <select id="country" name="ROOM_TYPE" onchange="change_country(this.value)" class="frm-field required">
                <option value="loft">LOFT</option>
                <option value="studio apartment">STUDIO APARTMENT</option>         
                <option value="maisonette">MAISONETTE</option>
                <option value="railroad">RAILROAD</option>
                <option value="bungalow">BUNGALOW</option>
                <option value="garden apartment">GARDEN APARTMENT</option>
            </select>
        </div>


        <h5>MAX COST</h5>
        <input name="MAX_COST" type="text" value="${MAX_COST}">
        <h5> ADDRESS</h5>
        <input name="ADDRESS" type="text" value="${ADDRESS}">
        <h5>AREA</h5>
        <input name="AREA" type="text" value="${AREA}">
        <h5>LATITUDE</h5>
        <input name="LATITUDE" type="text" value="${LATITUDE}">
        <h5>LONGITUDE</h5>
        <input name="LONGITUDE" type="text" value="${LONGITUDE}">
        <h5>MAX PEOPLE</h5>
        <input name="MAX_PEOPLE" type="text" value="${MAX_PEOPLE}">
        <h5>MIN COST</h5>
        <input name="MIN_COST" type="text" value="${MIN_COST}">
        <h5>EXTRA COST PER PERSON</h5>
        <input name="EXTRA_COST_PER_PERSON" type="text" value="${EXTRA_COST_PER_PERSON}">
        <h5>THUMBNAIL</h5>
        <p>You may upload a photo to <a href="http://www.dropbox.com" target="blank">dropbox</a> </p>
        <input name="THUMBNAIL_URL" type="text" value="${THUMBNAIL_URL}">
        <h5>DESCRIPTION</h5>
        <input name="DESCRIPTION" type="text" value="${DESCRIPTION}">
        <h5>BATHROOMS</h5>
        <input name="BATHROOMS" type="text" value="${BATHROOMS}">
        <h5>BEDROOMS</h5>
        <input name="BEDROOMS" type="text" value="${BEDROOMS}">
        <br/>

        <c:if test="${LIVING_ROOM}" >
            <input type="checkbox" name="LIVING_ROOM" value="living_room" checked="true"> LIVING ROOM<br>
        </c:if>
        <c:if test="${not LIVING_ROOM}" >
            <input type="checkbox" name="LIVING_ROOM" value="living_room">LIVING ROOM<br>
        </c:if>

        <c:if test="${KITCHEN}" >
            <input type="checkbox" name="KITCHEN" value="kitchen" checked="true"> KITCHEN <br>
        </c:if>
        <c:if test="${not KITCHEN}" >
            <input type="checkbox" name="KITCHEN" value="kitchen">KITCHEN<br>
        </c:if>

        <br/>
        <br/>


        <h1><center>MORE DETAILS</center></h1>


        <h5>BEDS</h5>
        <input name="BEDS" type="text" value="${BEDS}">
        <h5>MINIMUM DATES</h5>
        <input name="MINIMUM_DATES" type="text" value="${MINIMUM_DATES}">
        <h5>NEIGHBOURHOOD</h5>
        <input name="NEIGHBOURHOOD" type="text" value="${NEIGHBOURHOOD}">
        <h5>PUBLIC TRANSPORT</h5>
        <input name="PUBLIC_TRANSPORT" type="text" value="${PUBLIC_TRANSPORT}">
        <h5>HOST REVIEW</h5>
        <input name="HOST_REVIEW" type="text" value="${HOST_REVIEW}">
        <br/>


        <c:if test="${SMOKING}" >
            <input type="checkbox" name="SMOKING" value="smoke" checked="true"> SMOKING<br>
        </c:if>
        <c:if test="${not SMOKING}" >
            <input type="checkbox" name="SMOKING" value="smoke">SMOKING<br>
        </c:if>

        <c:if test="${PETS}" >
            <input type="checkbox" name="PETS" value="pets" checked="true"> PETS <br>
        </c:if>
        <c:if test="${not PETS}" >
            <input type="checkbox" name="PETS" value="pets">PETS<br>
        </c:if>

        <c:if test="${EVENT}" >
            <input type="checkbox" name="EVENT" value="event" checked="true"> EVENT <br>
        </c:if>
        <c:if test="${not EVENT}" >
            <input type="checkbox" name="EVENT" value="event">EVENT<br>
        </c:if>

        <c:if test="${TV}" >
            <input type="checkbox" name="TV" value="tv" checked="true"> TV <br>
        </c:if>
        <c:if test="${not TV}" >
            <input type="checkbox" name="TV" value="tv">TV<br>
        </c:if>

        <c:if test="${WIFI}" >
            <input type="checkbox" name="WIFI" value="wifi" checked="true"> WIFI <br>
        </c:if>
        <c:if test="${not WIFI}" >
            <input type="checkbox" name="WIFI" value="wifi">WIFI<br>
        </c:if>

        <c:if test="${HEAT}" >
            <input type="checkbox" name="HEAT" value="heat" checked="true"> HEAT <br>
        </c:if>
        <c:if test="${not HEAT}" >
            <input type="checkbox" name="HEAT" value="heat">HEAT<br>
        </c:if>

        <c:if test="${AIRCONDITION}" >
            <input type="checkbox" name="AIRCONDITION" value="aircondition" checked="true"> AIRCONDITION <br>
        </c:if>
        <c:if test="${not AIRCONDITION}" >
            <input type="checkbox" name="AIRCONDITION" value="aircondition">AIRCONDITION<br>
        </c:if>

        <c:if test="${PARKING}" >
            <input type="checkbox" name="PARKING" value="parking" checked="true"> PARKING <br>
        </c:if>
        <c:if test="${not PARKING}" >
            <input type="checkbox" name="PARKING" value="parking">PARKING<br>
        </c:if>

        <c:if test="${ELEVATOR}" >
            <input type="checkbox" name="ELEVATOR" value="elevator" checked="true"> ELEVATOR <br>
        </c:if>
        <c:if test="${not ELEVATOR}" >
            <input type="checkbox" name="ELEVATOR" value="elevator">ELEVATOR<br>
        </c:if>

        <br/>
        <br/>
        <h1><center>OFFER</center></h1>
        
        
        <ul>        
            <h5>DATE FROM</h5>
            <div class="book_date">
                <input class="date" placeholder="select from date" id="datepicker" name="from" type="text" value="" onfocus="this.value = '';" onblur="if (this.value == '') {
                            this.value = '';
                        }">
            </div>					
            <h5>DATE TO</h5>
            <div class="book_date">
                <input class="date" placeholder="select to date" id="datepicker1" name="to" type="text" value="" onfocus="this.value = '';" onblur="if (this.value == '') {
                            this.value = '';
                        }">
            </div>		
        </ul>

        <h5>COST PER DAY</h5>
        <input name="COST_PER_DAY" type="text" value="${COST_PER_DAY}">

        <input type="submit" value="Submit">
        <input type="reset" value="Reset">
    </form>	

</div>