<%@page contentType="text/html" pageEncoding="UTF-8"%>

<div class="col-md-8 booking-form">
    <form action="${pageContext.request.contextPath}/contact/reply" method="GET">

        <h2><b>MESSAGE</b> </h2>
        <br/>
        <h4><strong>From: ${MESSAGE.sender_name}</strong> </h4>
        <br/>
        <textarea readonly="true" disabled="true">${MESSAGE.text}</textarea>
        <br/>
        
        <input type="hidden" name="email" value="${MESSAGE.sender_email}" />
        
        <input type="submit" value="Reply">
    </form>

    <form action="${pageContext.request.contextPath}/contact/delete" method="POST">
        <input type="submit" value="Delete">
    </form>


</div>