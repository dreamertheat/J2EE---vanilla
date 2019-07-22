<%@ tag language="java" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>

<%@ attribute name="number" required="true" type="java.lang.Integer" rtexprvalue="true" %>
<%@ attribute name="fractions" required="false" type="java.lang.Integer" rtexprvalue="true" %>

<c:choose>

<c:when test="${fractions != null}">
value:
<fmt:formatNumber type="number" maxFractionDigits="${fractions}" > <%=java.lang.Math.random()*number+1 %> </fmt:formatNumber>
</c:when>



<c:otherwise>
default:
 <%=java.lang.Math.random()*number+1 %> 
</c:otherwise>

</c:choose>
