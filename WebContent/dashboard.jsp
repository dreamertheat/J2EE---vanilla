<%@page import="beans.Users"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="x" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%
	if (session.getAttribute("accounts")==null) {
	request.getRequestDispatcher("controller").forward(request, response);
 }
%>
<title>Welcome, <%
	//out.print(((Users )session.getAttribute("user")).getEmail());
%></title>
</head>
<body>



This is your dashboard.
<br>
<a href="controller">go back to index.</a>
<br>
<a href="controller?action=logs">see logs here.</a>

<p> session : <%= session.getMaxInactiveInterval() %> </p>
</body>
</html>