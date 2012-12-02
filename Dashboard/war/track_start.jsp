<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- The HTML 4.01 Transitional DOCTYPE declaration-->
<!-- above set at the top of the file will set     -->
<!-- the browser's rendering engine into           -->
<!-- "Quirks Mode". Replacing this declaration     -->
<!-- with a "Standards Mode" doctype is supported, -->
<!-- but may lead to some differences in layout.   -->

<html>
<head>
<%@include file="inc/head.jsp"%>
<%@include file="inc/redirect.jsp"%>
<%@ page import="dashboard.model.*" %>
<%@ page import="java.util.*" %>
</head>
<body>
<div data-role="page" id="track_start_jsp">
<div data-role="header" data-id='header' data-position="fixed">
	<h1>Learnalyzer</h1>
	<a href="/logout" data-role="button" data-icon="back" class="ui-btn-right">Afmelden</a>
	<a href="/menu.jsp" data-role="button" data-icon="grid" class="ui-btn-left">Menu</a>
</div><!-- /header -->
<div data-role="content">
<%
	String course = request.getParameter("course");
	if(course==null){
		out.println("<a href='course_select.jsp?returl=track_start.jsp' data-role='button'>Kies een vak</a>");
		out.println("<p><a class='ui-disabled' data-role='button'>Start</a>");
	}else{
		out.println("<form action='/track' method='post'>");
		out.println("<a href='course_select.jsp?returl=track_start.jsp' data-role='button'>"+course+"</a>");
		out.println("<p><input type='hidden' name='courseinput' value='"+course+"'>");
		out.println("<p><input type='submit' name='submit' value='Start'>");
		out.println("</form>");
	}
%>

</div><!-- /content -->
</div><!-- /page -->
</body>
</html>
