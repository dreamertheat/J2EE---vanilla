<%@page import="org.apache.catalina.deploy.ContextService"%>
<%@page import="beans.Users"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="x" %>
   <x:set var="rand"> <%= java.lang.Math.random() * 100+15 %> </x:set>    
    
    <x:set var="loggers" value="${rand}" scope="page"  />
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>LOGS</title>
</head>
<body>
<jsp:include page="../header.jsp"/>
You can check logs here below.<br>

<h1>Cookies and session</h1>

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


<h1>MAPS and lists</h1>
Technology: <x:out value='${requestScope.map["technology"]}'></x:out><br>
Manufacturer: ${requestScope.map["manufacturer"]} <br>
Website: ${requestScope.map["website"]}<br>


<div>
<% int counter = 0; %>
<table >
<x:forEach  var="animals" items="${sessionScope.animals}">
	<% counter++; pageContext.setAttribute("counter", counter); %>
	<x:set var="counter" value="${counter}"  />
	<tr><td>${counter}</td><td>${animals}<td> <tr> 
	
</x:forEach>
</table>
</div>



</div>


<jsp:include page="../footer.jsp"/>
</body>
</html>