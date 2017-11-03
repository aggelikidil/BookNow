<%@page contentType="text/html" pageEncoding="UTF-8"%>


<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri = "http://java.sun.com/jsp/jstl/functions" %>

<div class="col-md-8 booking-form">
    
    <form action="${pageContext.request.contextPath}/register" method="POST">
        <h5>USERNAME</h5>        
        <input name="USERNAME" type="text" value="${USERNAME}">
        <label for="USERNAME">
            <span class="errormsg">${USERNAME_ERROR}</span>
        </label>        
        
        <h5>PASSWORD</h5>
        <input name="PASSWORD" type="password" value="">
        <label for="PASSWORD">
            <span class="errormsg">${PASSWORD_ERROR}</span>
        </label>
        
        <h5>PASSWORD VERIFICATION</h5>
        <input name="PASSWORD_VERIFICATION" type="password" value="">
        <label for="PASSWORD_VERIFICATION">
            <span class="errormsg">${PASSWORD_VERIFICATION_ERROR}</span>
        </label>
        
        <h5>FIRSTNAME</h5>
        <input name="FIRSTNAME" type="text" value="${FIRSTNAME}">
        <label for="FIRSTNAME">
            <span class="errormsg">${FIRSTNAME_ERROR}</span>
        </label> 
        
        <h5>LASTNAME</h5>
        <input name="LASTNAME" type="text" value="${LASTNAME}">
        <label for="LASTNAME">
            <span class="errormsg">${LASTNAME_ERROR}</span>
        </label> 
        
        <h5>E-MAIL</h5>
        <input name="EMAIL" type="text" value="${EMAIL}">
        <label for="EMAIL">
            <span class="errormsg">${EMAIL_ERROR}</span>
        </label> 
        
        <h5>PHONE NUMBER</h5>
        <input name="PHONENUMBER" type="text" value="${PHONENUMBER}">
        <label for="PHONENUMBER">
            <span class="errormsg">${PHONENUMBER_ERROR}</span>
        </label> 
        
        <h5>PHOTO URL</h5>
        <p>You may upload your photo to <a href="http://www.dropbox.com" target="blank">dropbox</a> </p>
        <input name="PHOTO_URL" type="text" value="${PHOTO_URL}">
        <label for="PHOTO_URL">
            <span class="errormsg">${PHOTO_URL_ERROR}</span>
        </label> 
        
        <h5>CITY</h5>
        <input name="CITY" type="text" value="${CITY}">
        <label for="CITY">
            <span class="errormsg">${CITY_ERROR}</span>
        </label> 
        
        <h5>COUNTRY</h5>
        <input name="COUNTRY" type="text" value="${COUNTRY}">
        <label for="COUNTRY">
            <span class="errormsg">${COUNTRY_ERROR}</span>
        </label> 
        
        <h5>ADDRESS</h5>
        <input name="LOCATION" type="text" value="${LOCATION}">
        <label for="LOCATION">
            <span class="errormsg">${LOCATION_ERROR}</span>
        </label> 
        
        <h5>LONGITUDE</h5>
        <input name="LONGITUDE" type="text" value="${LONGITUDE}">
        <label for="LONGITUDE">
            <span class="errormsg">${LONGITUDE_ERROR}</span>
        </label> 
        
        <h5>LATITUDE</h5>
        <input name="LATITUDE" type="text" value="${LATITUDE}">
        <label for="LATITUDE">
            <span class="errormsg">${LATITUDE_ERROR}</span>
        </label> 
        
        
        <h5>Your roles</h5>
        <p><input type="checkbox" value="RENTER" checked="true" readonly="true"> I want to be renter</p>
        <p>
            <c:if test="${not empty ROLES}" >
                <input type="checkbox" name="ROLES" value="HOST" checked="true"> I want to be host        
            </c:if>
            <c:if test="${empty ROLES}" >
                <input type="checkbox" name="ROLES" value="HOST"> I want to be host        
            </c:if>
        </p>

        <input type="submit" value="Submit">
        <input type="reset" value="Reset">
    </form>			      
</div>