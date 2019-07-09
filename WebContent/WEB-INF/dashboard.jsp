<%@page import="beans.Users"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="x" %>    

<%
	

	session.setAttribute("title", "dashboard");
	//urlencoded cookie checker
	if(request.getCookies()!=null) {
		for(Cookie c : request.getCookies()){
			if (c.getName().equalsIgnoreCase("JSESSIONID")){
				//
				 String request_session = c.getValue().toString();
				 pageContext.setAttribute("request_session", request_session);
				 if (c.getValue()==null) {
					 request.getRequestDispatcher("controller").forward(request, response);
				 }
			}
		} 
	} else {
		 request.getRequestDispatcher("controller").forward(request, response);
	}
 	 
%>
<x:choose>

	<x:when test="${cookie.JSESSIONID.value}">
		<% request.getRequestDispatcher("controller").forward(request, response); %>
	</x:when>

</x:choose>


<x:import url="../header.jsp"></x:import>


This is your dashboard.
<br>
<a href="controller">go back to index.</a>
<br>
<a href="controller?action=logs">see logs here.</a>
<a href="controller?action=logout">logout</a>

<p> session : <%= session.getMaxInactiveInterval() %> </p><br>

Attribute from HttpSession: <x:out value="${accounts.username}"></x:out><br>
Attribute from ServletContext:<x:out value="${dummy.username}"></x:out><br>
Attribute from Cookie:<x:out value="${cookie.sunny.value}"></x:out><br>
Attribute from URLencodeRequest:<x:out value="${request_session}"></x:out><br>


<x:import url="../footer.jsp"></x:import>
