<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>

<% 
if (session.getAttribute("accounts")!=null) {
	//request.getRequestDispatcher("dashboard.jsp").forward(request, response);
} 
if (request.getCookies().length>0) {
	//request.getRequestDispatcher("dashboard.jsp").forward(request, response);
} 
if (getServletContext().getAttribute("dummy")!=null) {
	//request.getRequestDispatcher("dashboard.jsp").forward(request, response);
}

%>
<meta charset="ISO-8859-1">
<title>Login</title>
</head>
<body>

<%


%>

<form action="controller" method="post"  >

<input type="text" name="username" placeholder="enter username" value="<%=request.getAttribute("username") %>" >
<input type="password" name="password" placeholder="enter password" value="" >
<input type="hidden" name="action" value="submitlogin">
<br><br>
Choose persistence: <br>
<input type="checkbox" name="persistence" value="cookie">Enable cookies<br>
<input type="checkbox" name="persistence" value="servlet">Enable servlet context<br>
<input type="checkbox" name="persistence" value="session">Enable session<br>

*default is request<br> 
<input type="submit" value="login">
</form>

<span>
<%= request.getAttribute("login_message") %>
</span>

<%@include file="footer.jsp" %>
</body>
</html>