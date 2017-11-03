<%-- 
    Document   : menubar
    Created on : Jul 20, 2017, 1:31:03 PM
    Author     : aggeliki
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-md-8 booking-form">
    <form action="${pageContext.request.contextPath}/contact/create" method="POST">

        <h5><b>RECIPIENT's E-MAIL</b></h5>
        <input name="email" type="text" value="${email}">
        <br/>

        <h5><b>MESSAGE TEXT</b></h5>
        <textarea name="TEXT"></textarea>
        <br/>

        <input type="submit" value="Send">
    </form>


</div>