<%@ taglib prefix = "fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"  %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>

<head>
    <style>
        table {
            font-family: arial, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #dddddd;
            text-align: left;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #dddddd;
        }
    </style>
</head>




<form action="${pageContext.request.contextPath}/contact/outbox" method="POST">
    <body>

        <table>
            <tr>
                <th>Date</th>
                <th>Receiver</th>
                <th></th>
            </tr>
            <tr>
             <c:forEach items="${messages}" var="obj">
                <td><a href="${pageContext.request.contextPath}/contact/outbox/details?id=${obj.id}">${obj.date}</a></td>
                <td>${obj.receiver_name}</td>
                
                <td><center><i><b><a href="${pageContext.request.contextPath}/contact/delete">Delete</a></b></i></center></td>
            </tr>
             </c:forEach> 
        </table>

    </body>
</form>