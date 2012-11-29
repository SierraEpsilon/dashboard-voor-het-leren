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
<%@ page import="dashboard.model.*" %>
<%@ page import="dashboard.registry.*" %>
<%@ page import="java.util.*" %>
</head>
<body>
<div data-role="page">
<div data-role="header" data-id='header' data-position="fixed">
	<h1>Learnalyzer</h1>
	<a href="/logout" data-role="button" data-icon="back" class="ui-btn-right">Afmelden</a>
	<a href="/menu.jsp" data-role="button" data-icon="grid" class="ui-btn-left">Menu</a>
</div><!-- /header -->
<div data-role="navbar">
	<ul>
		<li><a href="/settings_info.jsp">Personal</a></li>
		<li><a href="/settings_pass.jsp">Password</a></li>
		<li><a href="/settings_vak.jsp">Vakken</a></li>
	</ul>
</div><!-- /navbar -->
<div data-role="content">
<form method='post' action='/settings'>
	<%
		Student student = (Student)session.getAttribute("student");
	%>
	<div style="margin-top: 50px">
		<h3>Vakken</h3>
		<ul data-role="listview" style="margin: 5px">
		<%
			for(CourseContract course :student.getCourses() ){
				String name = course.getCourse().getName();
				out.println("<li data-inline='true'>" + name + "</li>");
				out.println("<button  type='submit' data-inline='true' data-icon='delete' name='submit' value='remove_" + name + "'>remove</button>");
			}
		%>
		</ul>
		<button type="submit" name="submit" value="voeg">Voeg een vak toe</button>
	</div>
</form>
</div><!-- /content -->
</div><!-- /page -->
</body>
</html>