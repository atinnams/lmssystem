<%-- 
    Document   : admin
    Created on : May 8, 2010, 11:27:44 AM
    Author     : NKLapTop
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">
<% 
   if (session.getAttribute("Admin") == null) {
    response.sendRedirect("index.jsp");
   }
%>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Admin Page <%= request.getQueryString() %></h1>
        
    </body>
</html>
