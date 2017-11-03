<%@page contentType="text/html" pageEncoding="UTF-8"%>

<%@ taglib prefix = "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<div class="col-md-8 booking-form">			 
    <form action="${pageContext.request.contextPath}/admin/users/active" method="POST">


        <div class="rooms text-center">
            <div class="container">
                <h3>Active users</h3>

                <div class="main_bg">
                    <div class="container">
                        <div class="main">	                                      
                            <c:forEach items="${users}" var="obj" varStatus="i">
                                <c:if test="${i.index == 0}" >
                                    <ul class="service_list">
                                    </c:if>
                                    <c:if test="${i.index % 4 == 0 and i.index > 0 }" >
                                        <ul class="service_list top">
                                        </c:if>
                                        <li>
                                            <div class="ser_img">
                                                <a href="${pageContext.request.contextPath}/admin/users/details?id=${obj.id}">
                                                    <img src="${obj.photo_url}" />
                                                </a>
                                            </div>	
                                            <a href="${pageContext.request.contextPath}/admin/users/details?id=${obj.id}"><h3>View details</h3></a>
                                            <p class="para">${obj.firstname} ${obj.lastname}</p>
                                        </li>
                                        <c:if test="${(i.index + 1) % 4 == 0}" >                                        
                                            <div class="clear"></div>
                                        </ul>
                                    </c:if>
                                </c:forEach>

                                <div class="clear"></div>
                            </ul>      

                            <div class="clear"></div>
                        </div>
                    </div>
                </div>		
            </div>
        </div>	



    </form>	

    <form action="${pageContext.request.contextPath}/admin/users" method="POST">
        <input type="submit" value="Back">
    </form>
</div> 