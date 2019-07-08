<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>copyright 2019</title>
</head>
<body>




<br>--------------------------------------------------------------<br>
<a href="controller?action=copyright">*</a><br>
<a href="<%= request.getContextPath() %>/controller?action=copyright">**</a><br>
<a href="<%= response.encodeRedirectURL(request.getContextPath()+"/controller?action=copyright") %>">***</a><br>

<form method="get"  action="controller">
<input type="submit" value="copyright">
<input type="hidden" name="action" value="copyright">
</form>
copyright 2019
</body>
</html>