

<%@page contentType="text/html" pageEncoding="UTF-8"%>

<jsp:include page="/WEB-INF/js_fragments/datepicker_raw.jsp"/>   

<div class="col-md-8 booking-form">			 
    <form action="${pageContext.request.contextPath}/search/advanced_search" method="POST">
        <h5>PROPERTIES</h5>
        <input type="checkbox" name="tv" value="tv"> TV 
        <input type="checkbox" name="wifi" value="wifi" > WIFI
        <input type="checkbox" name="heat" value="heat"> HEAT
        <input type="checkbox" name="aircondition" value="aircondition"> AIR CONDITION
        <br/>
        <br/>
        <input type="checkbox" name="living_room" value="living_room"> LIVING ROOM
        <input type="checkbox" name="kitchen" value="kitchen"> KITCHEN
        <input type="checkbox" name="parking" value="parking"> PARKING
        <input type="checkbox" name="elevator" value="elevator"> ELEVATOR
        <br/>
        <br/>
        <input type="checkbox" name="smoking" value="smoking"> SMOKING
        <input type="checkbox" name="pets" value="pets"> PETS
        <input type="checkbox" name="event" value="event"> EVENTS
        
        <br/>

        <h5>ROOM TYPE</h5>
        <select name="roomtype" class="frm-field required">
            <option value="any">undefined</option>
            <option value="loft">LOFT</option>
            <option value="studio apartment">STUDIO APARTMENT</option>         
            <option value="maisonette">MAISONETTE</option>
            <option value="railroad">RAILROAD</option>
            <option value="bungalow">BUNGALOW</option>
            <option value="garden apartment">GARDEN APARTMENT</option>
        </select>
        
        <h5 class="mem">MEMBERS</h5>
        <select name="members" class="frm-field required">
            <option value="1">1</option>
            <option value="2">2</option>         
            <option value="3">3</option>
            <option value="4">4</option>
            <option value="5">5</option>
            <option value="6">6</option>
        </select>

        <h5>MAX COST</h5>
        <input type="text" name="maxcost" value="${maxcost}" placeholder="leave empty for undefined" class="form-control input-small">
        
        <h5>LOCATION / DESCRIPTION / ADDRESS</h5>
        <input type="text" name="location" placeholder="leave empty for undefined">

        <ul>        
            <h5>Check in</h5>
            <div class="book_date">
                <input class="date" placeholder="select from date" id="datepicker" name="from" value="${from}" type="text" value="" onfocus="this.value = '';" onblur="if (this.value == '') {
                            this.value = '';
                        }">
            </div>					
            <h5>Check out</h5>
            <div class="book_date">
                <input class="date" placeholder="select to date" id="datepicker1" name="to" value="${to}" type="text" value="" onfocus="this.value = '';" onblur="if (this.value == '') {
                            this.value = '';
                        }">
            </div>		
        </ul>
        <br/>
        <br/>
        <br/>
        <br/>


        <input type="submit" value="Search now">
    </form>


</div>