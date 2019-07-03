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
<%@include file="header.jsp" %>

<h4>HOMEPAGE.</h4>
<jsp:useBean id="account" class="beans.Accounts" scope="page" ></jsp:useBean>
Index Token: <c:out value="${account.number}" ></c:out> <br>
<a href="controller?action=login"> login </a> <br>
<a href="controller?action=about"> about </a> <br>

<%-- jstl show counter parameter if counter is more than 0 --%>
<c:if test="${param.counter > 0}">


Counter is: <c:out value="${param.counter} "></c:out><br>

</c:if>

<%
/* out.println("counter: "+request.getParameter("counter"));
if (request.getParameter("counter")!=null) {
	
	Integer counter; 
	try {
		counter = Integer.parseInt(request.getParameter("counter").toString());
	}catch (Exception e){
		counter = 0;
	}
} */
%>

<c:choose>
	<c:when test="${param.id != null}">
		<c:choose>
			<c:when test="${param.id == 'james'}">
				<p> The id is: <c:out value="333"></c:out> </p>
			</c:when>
			
			<c:otherwise>
				Your id is: <c:out value="${param.id} "></c:out> <p>
			</c:otherwise>
		</c:choose>
	</c:when>
</c:choose>

<c:if test="${param.counter>0}">

	<c:forEach var="i" begin="0" end="${param.counter}" varStatus="status" >
	
		<c:if test="${status.first}">
		<p> first </p>
		</c:if>
		
		<c:out value="${i}"></c:out>
		
		<c:if test="${status.last}">
		<p> last </p>
		</c:if>
		
	</c:forEach>
	
</c:if>
<%@include file="footer.jsp" %>
</body>
</html>