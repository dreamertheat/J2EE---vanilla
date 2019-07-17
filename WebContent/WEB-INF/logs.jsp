<%@page import="org.apache.catalina.deploy.ContextService"%>
<%@page import="beans.Users"%>

    <%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="x" %>
    <%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="z" %>
   <%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn" %>
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
<x:set var="table1" value="users"/>
<x:set var="table2" value="media"/>

<z:query dataSource="${ds}" sql="select * from ${table1}" var="results" >
</z:query>

<z:query dataSource="${ds}" sql="select * from ${table2}" var="media"></z:query>

<!-- solo query -->
<z:query dataSource="${ds}" sql="select * from ${table2} where media_name LIKE ? limit 1" var="single" >
	<z:param>katakana</z:param>
</z:query>
<b>SINGLE ROW VIEW</b>: ${single.rows[0]}
<x:forEach  var="users"  items="${results.rows}">
	 ${users._id} - ${users.first_name} - ${users.last_name}  <br>
</x:forEach>

</div>


<h1> Images </h1>
<table style="width:100%">
<x:set var="tablewidth" value="3"></x:set>
<x:forEach  var="med"  items="${media.rows}" varStatus="med_">
	 
	 <div style="border:solid 1px">
	 	
	 	current index: ${med_.index} <br>
	 	condition 1: ( ${med_.index } % ${tablewidth } == 0 ) ${ med_.index % tablewidth == 0} <br>
	 	condition 2: ( ${med_.index+1 } % ${tablewidth } == 0 ) ${ med_.index+1 % tablewidth == 0} <br>
	 
	 </div>
	 <x:if test="${med_.index % tablewidth == 0}">
	 <tr> 
	 </x:if>
	 <a href="http://google.com">
	 <td>${med.media_name} : ${fn:substring(med.media_name,0,1)} </td> <td><img width="200px" height="200px" src="${med.media_path}" > </td> 
	 </a>
	 <x:if test="${med_.index+1 % tablewidth == 0}">
	 </tr> 
	 </x:if>
</x:forEach>

</table>




<table>
<!-- 

1 comes out
looks back
adds to itself

1 comes out
looks back
adds to itself

2 comes out
looks back
adds to itself

3 comes out
looks back
adds to itself

5 comes out
looks back
adds to itself

8 comes out
looks back
adds to itself
-->


</table>


<x:import url="footer.jsp"/>
