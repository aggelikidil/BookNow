<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="col-md-8 booking-form">			
    
    <h4>You are here</h4>
    
    <div class="map">
        <iframe 
            width="100%" 
            height="300" 
            frameborder="0" 
            scrolling="no" 
            marginheight="0" 
            marginwidth="0" 
            src="https://maps.google.co.in/maps?f=q&amp;source=s_q&amp;hl=en&amp;ie=UTF8&amp;hq=&amp;ll=${USER.latitude},${USER.longitude}&amp;&amp;z=4&amp;output=embed"></iframe>
        <br>
        <small>
            <a  href="https://maps.google.co.in/maps?f=q&amp;source=embed&amp;hl=en&amp;geocode=&amp;sll=${USER.latitude},${USER.longitude}&amp;sspn=0.04941,0.104628&amp;ie=UTF8&amp;hq=&amp;t=m&amp;z=14&amp;ll=${USER.latitude},${USER.longitude}" style="color: #242424;text-shadow: 0 1px 0 #ffffff;text-align: left;font-size: 0.7125em;padding: 5px;font-weight: 600;">View Larger Map</a></small>
    </div>
  
    <form action="${pageContext.request.contextPath}/profile" method="POST">
        <h5>USERNAME</h5>
        <input name="USERNAME" type="text" value="${USER.username}" readonly="true" disabled="true">
        <h5>PASSWORD</h5>
        <input name="PASSWORD" type="text" value=""  readonly="true" disabled="true">
        <h5>PASSWORD VERIFICATION</h5>
        <input name="PASSWORD_VERIFICATION" type="text" value=""  readonly="true" disabled="true">
        <h5>FIRSTNAME</h5>
        <input name="FIRSTNAME" type="text" value="${USER.firstname}">
        <h5>LASTNAME</h5>
        <input name="LASTNAME" type="text" value="${USER.lastname}">
        <h5>E-MAIL</h5>
        <input name="EMAIL" type="text" value="${USER.email}">
        <h5>PHONE NUMBER</h5>
        <input name="PHONENUMBER" type="text" value="${USER.phonenumber}">
        <h5>PHOTO</h5>
        <input name="PHOTO_URL" type="text" value="${USER.photo_url}">
        <h5>CITY</h5>
        <input name="CITY" type="text" value="${USER.city}">
        <h5>COUNTRY</h5>
        <input name="COUNTRY" type="text" value="${USER.country}">
        <h5>LOCATION</h5>
        <input name="LOCATION" type="text" value="${USER.location}">
        
        <input type="submit" value="Save changes">
    </form>			      
</div>