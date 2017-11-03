<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib prefix = "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>

<div class="top-header">
    <div class="container">
        <div class="logo">
            <a href="${pageContext.request.contextPath}/">
                <img src="${pageContext.request.contextPath}/images/logo.png"/>
            </a>
        </div>
        <span class="menu"> </span>
        <div class="m-clear"></div>
        <div class="top-menu">
            <ul>
                <li class="active"><a href="${pageContext.request.contextPath}/">START</a></li>
                <li><a class="scroll" href="${pageContext.request.contextPath}/search">SEARCH</a></li>                
                
                

                
                <c:if test="${i_am_host}">                    
                    <li><a class="scroll" href="${pageContext.request.contextPath}/host/rooms">ROOMS</a></li>
                </c:if>
                
                <c:if test="${empty my_username}">
                    <li><a class="scroll" href="${pageContext.request.contextPath}/register">REGISTER</a></li>                
                    <li><a class="scroll" href="${pageContext.request.contextPath}/login">LOGIN</a></li>
                </c:if>

                <c:if test="${not empty my_username}">                    
                    <li><a class="scroll" href="${pageContext.request.contextPath}/profile">PROFILE</a></li>
                    <li><a class="scroll" href="${pageContext.request.contextPath}/recommend">RECOMMEND</a></li>
                    <li><a class="scroll" href="${pageContext.request.contextPath}/rentings">RENT</a></li>
                    <li><a class="scroll" href="${pageContext.request.contextPath}/rating">RATE</a></li>
                    <li><a class="scroll" href="${pageContext.request.contextPath}/logout">LOGOUT</a></li>                
                </c:if>    
                
                <c:if test="${i_am_admin}">
                    <li><a class="scroll" href="${pageContext.request.contextPath}/admin/users">USERS</a></li>
                    <li><a class="scroll" href="${pageContext.request.contextPath}/admin/export">EXPORT</a></li>
                </c:if>    
                    
                <li><a class="scroll" href="${pageContext.request.contextPath}/contact">CONTACT</a></li>
            </ul>
            <script>
                $("span.menu").click(function () {
                    $(".top-menu ul").slideToggle(200);
                });
            </script>
        </div>
        <div class="clearfix"></div>
    </div>
</div>                     