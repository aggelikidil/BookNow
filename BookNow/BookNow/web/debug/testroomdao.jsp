<%-- 
    Document   : testuserdao
    Created on : Jul 19, 2017, 1:38:26 PM
    Author     : aggeliki
--%>

<%@page import="dao.RoomDaoImpl"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="entities.RoomPojo"%>
<%@page import="java.util.List"%>
<%@page import="java.util.List"%>
<%@page import="dao.UserDaoImpl"%>
<%@page import="dao.UserDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    
    </head>
    <body>
        <%
//            RoomDaoImpl dao = new RoomDaoImpl(true);
//
//            
//            // test dao.list();
//            List<RoomPojo> rooms = dao.list();
//            request.setAttribute("x", rooms);
//            
//
//            // test dao.find();
//            
//            RoomPojo room = dao.find(2);
//            request.setAttribute("y", room);
        %>

        <h1>Test dao.list()</h1>
        <c:forEach var="i" items="${x}">
        <li>Username: ${i.username}  ${i.firstname}</li><br/>
        </c:forEach>
        
            
        <h1>Test dao.find()</h1>
        <p>${y.username}</p>
            
            
    </body>
</html>
