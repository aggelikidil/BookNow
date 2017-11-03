<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-md-8 booking-form">			 
    <form action="${pageContext.request.contextPath}/login" method="POST">
        <h5>USERNAME</h5>        
        <input name="USERNAME" class="time" type="text" value="${USERNAME}">
        <label for="USERNAME">
            <span class="errormsg">${USERNAME_ERROR}</span>
        </label>        
        
        <h5>PASSWORD</h5>
        <input name="PASSWORD" type="password" value="">
        <label for="PASSWORD">
            <span class="errormsg">${PASSWORD_ERROR}</span>
        </label>
        
        <br/>
        <br/>
        <br/>
        
        <input type="submit" value="Submit">
        <input type="reset" value="Reset">
    </form>			      
</div>