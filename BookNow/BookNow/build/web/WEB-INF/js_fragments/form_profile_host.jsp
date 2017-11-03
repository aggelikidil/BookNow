<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="col-md-8 booking-form">	

    <form action="${pageContext.request.contextPath}/contact/create" method="GET">   
        <h5><b>FIRSTNAME</b></h5>
        <input name="FIRSTNAME" type="text" value="${USER.firstname}" readonly="true" disabled="true"> 
        <br/>
        <h5><b>LASTNAME</b></h5>
        <input name="LASTNAME" type="text" value="${USER.lastname}" readonly="true" disabled="true"> 
        <br/>
        <h5><b>E-MAIL</b></h5>
        <input name="E-MAIL" type="text" value="${USER.email}" readonly="true" disabled="true"> 
        <br/>
        <h5><b>PHONENUMBER</b></h5>
        <input name="PHONENUMBER" type="text" value="${USER.phonenumber}" readonly="true" disabled="true">
        <br/>
        <h5><b>PHOTO</b></h5>
        <input name="PHOTO_URL" type="text" value="${USER.photo_url}" readonly="true" disabled="true"> 
        <br/>
        <h5><b>CITY</b></h5>
        <input name="CITY" type="text" value="${USER.city}" readonly="true" disabled="true"> 
        <br/>
        <h5><b>COUNTRY</b></h5>
        <input name="COUNTRY" type="text" value="${USER.country}" readonly="true" disabled="true"> 
        <br/>
        <h5><b>LOCATION</b></h5>
        <input name="LOCATION" type="text" value="${USER.location}" readonly="true" disabled="true">
        <br/>

        <input name="email" type="hidden" value="${USER.email}">
        <input type="submit" value="Send a message">
    </form>

    <form action="${pageContext.request.contextPath}/rating/rate_host" method="POST">
        <input type="submit" value="Rate Now">
    </form>

</div>