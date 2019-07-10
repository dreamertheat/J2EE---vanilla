<%@page import="org.apache.catalina.deploy.ContextService"%>
<%@page import="beans.Users"%>

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="x" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="z" %>
   
   <x:set var="rand"> <%= java.lang.Math.random() * 100+15 %> </x:set>    
    
    <x:set var="loggers" value="${rand}" scope="page"  />
    

<x:import url="header.jsp"/>
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


<br>
<h1> JSTL SQL</h1>

<z:setDataSource var="ds" dataSource="jdbc/alien_x" user="root" />

<z:query dataSource="${ds}" sql="select * from users" var="results"  />



<x:forEach  var="users"  items="${results.rows}">
	 ${users._id} - ${users.first_name} - ${users.last_name}  <br>
</x:forEach>

</div>


<x:import url="footer.jsp"/>
