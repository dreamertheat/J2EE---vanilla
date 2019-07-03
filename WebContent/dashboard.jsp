<%@page import="beans.Users"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="x" %>    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<%
	String request_session = "";
	for(Cookie c : request.getCookies()){
		if (c.getName().equalsIgnoreCase("JSESSIONID")){
			//request.getRequestDispatcher("controller").forward(request, response);
			 request_session = c.getValue().toString();
			 pageContext.setAttribute("request_session", request_session);
			//out.print(request_session);
		}
	} 
		//request.getRequestDispatcher("controller").forward(request, response);
 	 
%>
<x:choose>

	<x:when test="${cookie.JSESSIONID.value}">
		<% request.getRequestDispatcher("controller").forward(request, response); %>
	</x:when>

</x:choose>

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

<p> session : <%= session.getMaxInactiveInterval() %> </p><br>

Attribute from HttpSession: <x:out value="${accounts.username}"></x:out><br>
Attribute from ServletContext:<x:out value="${dummy.username}"></x:out><br>
Attribute from Cookie:<x:out value="${cookie.sunny.value}"></x:out><br>
Attribute from URLencodeRequest:<x:out value="${request_session}"></x:out><br>

<%@include file="footer.jsp" %>
</body>
</html>