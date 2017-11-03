<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="col-md-8 booking-form">	
    <form action="${pageContext.request.contextPath}/rating/rate_room" method="POST">
    
        <h5><b><u>VALUE</u></b></h5>
        <span class="starRating">
            <input id="value1" type="radio" name="value" value="1">
            <label for="value1">1</label>
            
            <input id="value2" type="radio" name="value" value="2">
            <label for="value2">2</label>
            
            <input id="value3" type="radio" name="value" value="3">
            <label for="value3">3</label>
            
            <input id="value4" type="radio" name="value" value="4">
            <label for="value4">4</label>
            
            <input id="value5" type="radio" name="value" value="5" checked="true">
            <label for="value5">5</label>
            
            <input id="value6" type="radio" name="value" value="6">
            <label for="value6">6</label>
            
            <input id="value7" type="radio" name="value" value="7">
            <label for="value7">7</label>
            
            <input id="value8" type="radio" name="value" value="8">
            <label for="value8">8</label>
            
            <input id="value9" type="radio" name="value" value="9">
            <label for="value9">9</label>
            
            <input id="value10" type="radio" name="value" value="10">
            <label for="value10">10</label>
        </span>
        
        <br/>

        <input type="hidden" name="id" value="${id}" />

        <input type="submit" value="Submit">
    </form>

</div>