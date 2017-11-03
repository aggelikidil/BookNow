<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="col-md-8 booking-form">	
    <form action="${pageContext.request.contextPath}/contact/delete" method="POST">

        <h2><b>MESSAGE</b> </h2>
        <br/>
        <h4><strong>From: ${MESSAGE.receiver_name}</strong> </h4>
        <br/>
        <textarea value="" readonly="true" disabled="true">${MESSAGE.text}</textarea>
        <br/>

    </form>

    <form action="${pageContext.request.contextPath}/contact/delete" method="POST">
        <input type="submit" value="Delete">
    </form>


</div>