<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="col-md-8 booking-form">
    <form action="${pageContext.request.contextPath}/rating/rate_host/rate_ok" method="POST">
        <h5><b>FIRSTNAME</b></h5>
        <input name="FIRSTNAME" type="text" value="" > 
        <br/>
        <h5><b>LASTNAME</b></h5>
        <input name="LASTNAME" type="text" value="" > 
        <br/>

        <h5><b><u>VALUE</u></b></h5>
        <span class="starRating">
            <input id="rating1" type="radio"  name="rating" value="5">
            <label for="rating1">1</label>
            <input id="rating2" type="radio" name="rating" value="4">
            <label for="rating2">2</label>
            <input id="rating3" type="radio" name="rating" value="3">
            <label for="rating3">3</label>
            <input id="rating4" type="radio" name="rating" value="2">
            <label for="rating4">4</label>
            <input id="rating5" type="radio" name="rating" value="1">
            <label for="rating5">5</label>
            <input id="rating6" type="radio" name="rating" value="5">
            <label for="rating6">6</label>
            <input id="rating7" type="radio" name="rating" value="4">
            <label for="rating7">7</label>
            <input id="rating8" type="radio" name="rating" value="3">
            <label for="rating8">8</label>
            <input id="rating9" type="radio" name="rating" value="2">
            <label for="rating9">9</label>
            <input id="rating10" type="radio" name="rating" value="1">
            <label for="rating10">10</label>
        </span>


        <input name="USERNAME" type="hidden" value="">
        <input type="submit" value="Submit">
    </form>

</div>