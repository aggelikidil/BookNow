<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>

<sql:query var="rs" dataSource="jdbc/rentals">
    select * from User
</sql:query>

<html>
  <head>
    <title>DB Test</title>
  </head>
  <body>

  <h1>Testing database connection pool!</h1>

  <%
      for (int i=0;i<10;i++) {
          %>
          <p>yolo</p>
          <%
      }


    %>
  
    <c:forEach var="row" items="${rs.rows}">
        Username: ${row.username}<br/>
        Firstname: ${row.firstname}<br/>
        Lastname: ${row.lastname}<br/>
    </c:forEach>

  </body>
</html>