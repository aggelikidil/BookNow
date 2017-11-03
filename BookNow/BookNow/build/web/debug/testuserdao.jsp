<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%@page import="entities.User"%>
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
            UserDaoImpl dao = new UserDaoImpl(true);

            
            // test dao.list();
            List<User> list = dao.list();
            request.setAttribute("x", list);
            

            // test dao.find();
            
            User user = dao.find(2);
            request.setAttribute("y", user);

        %>

        <h1>Test dao.list()</h1>
        <c:forEach var="i" items="${x}">
        <li>Username: ${i.username}  ${i.firstname}</li><br/>
        </c:forEach>
        
            
        <h1>Test dao.find()</h1>
        <p>${y.username}</p>
            
            
    </body>
</html>
