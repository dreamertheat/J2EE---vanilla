<%@page import="beans.Users"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="x" %>
   <c:set var="rand"> <%= java.lang.Math.random() * 100+15 %> </c:set>    
    
    <x:set var="loggers" value="${rand}" scope="page"  />
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LOGS</title>
</head>
<body>
You can check logs here.

<h1>cookies section</h1>

<div>
<%
	if (session.getAttribute("user")==null)
	out.print("session not found!");
else out.print("<div> session: "+((Users)session.getAttribute("user")).getEmail()+"/div");

Cookie[] cookies = request.getCookies();

if (cookies==null) {
	out.println(" no cookies at the moment");
}
else {
	for (Cookie c : cookies) {
		out.println("<br >name "+c.getName());
		out.println("<br> value "+c.getValue());
	}
}
%>

<br><br>
page: <x:out value="${loggers}"></x:out><br>
request: <x:out value="${requestScope.logger}"></x:out><br>
session: <x:out value="${sessionScope.logger}"></x:out><br>
application: <x:out value="${applicationScope.logger}"></x:out><br>


<h1>MAPS</h1>
Hashmap 1: <x:out value='${requestScope.map["technology"]}'></x:out><br>

</div>

</body>
</html>