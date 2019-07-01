<%@page import="beans.Accounts"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Index</title>
</head>
<body>
<h1>This is the home page.</h1>
<jsp:useBean id="account" class="beans.Accounts" scope="page" ></jsp:useBean>
<c:out value="${account.number}" ></c:out>
<a href="controller?action=login"> login </a> <br>
<a href="controller?action=about"> about </a> <br>

</body>
</html>