<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>




<br><div id="ownage">&nbsp;</div></div><br>
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