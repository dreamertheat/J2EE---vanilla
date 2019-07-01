<%@page import="beans.Users"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
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
</div>

</body>
</html>