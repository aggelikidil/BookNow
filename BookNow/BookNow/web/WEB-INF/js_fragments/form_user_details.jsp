<%@ taglib prefix = "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<div class="col-md-8 booking-form">
    <h5><b>USERNAME</b></h5>
    <input name="USERNAME" type="text" value="${USER.username}" readonly="true" disabled="true">
    <br/>
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
    <h5><b>ROLE</b></h5>
    <input name="ROLE" type="text" value="" readonly="true" disabled="true"> 
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
    <h5><b>LATITUDE</b></h5>
    <input name="LATITUDE" type="text" value="${USER.latitude}" readonly="true" disabled="true"> 
    <br/>
    <h5><b>LONGITUDE</b></h5>
    <input name="LONGITUDE" type="text" value="${USER.longitude}" readonly="true" disabled="true"> 
    <br/>
    <c:if test="${ not USER.is_approved }">
        <form action="${pageContext.request.contextPath}/admin/users/activate" method="POST">
            <input name="id" type="hidden" value="${USER.id}">
            <input type="submit" value="Activate">
        </form>
    </c:if>

    <c:if test="${USER.is_approved }">
        <form action="${pageContext.request.contextPath}/admin/users/active" method="POST">
            <input name="id" type="hidden" value="${USER.id}">
            <input type="submit" value="Back">
        </form>
    </c:if>

    <c:if test="${not USER.is_approved }">
        <form action="${pageContext.request.contextPath}/admin/users/inactive" method="POST">
            <input name="id" type="hidden" value="${USER.id}">
            <input type="submit" value="Back">
        </form>
    </c:if>

</div>