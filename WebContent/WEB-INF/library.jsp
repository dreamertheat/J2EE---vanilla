<%@page import="javax.servlet.jsp.el.ScopedAttributeELResolver"%>
<%@page import="beans.Content"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    
       <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="x" %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="z" %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
 <x:import url="header.jsp"/>
 
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title></title>
</head>
<body>
<h4>Library</h4>

<table>
<x:forEach var="cont" items="${sessionScope.master}" >

	
	<tr> <td> ${cont._id}</td> <td> ${cont.title}</td> <td> <a href="controller?master=${cont._id}&action=library"> open </a> </td> </tr>
</x:forEach>
</table>


<table>
<x:forEach var="bran" items="${sessionScope.branches}" >
	<tr> <td> ${bran._id}</td> <td> ${bran.title}</td> <td> <a href="controller?master=${bran._id}&action=library"> open </a> </td> </tr>
</x:forEach>
</table>


<x:import url="footer.jsp"/>
</body>
</html>