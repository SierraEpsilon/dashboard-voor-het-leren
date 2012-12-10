<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- The HTML 4.01 Transitional DOCTYPE declaration-->
<!-- above set at the top of the file will set     -->
<!-- the browser's rendering engine into           -->
<!-- "Quirks Mode". Replacing this declaration     -->
<!-- with a "Standards Mode" doctype is supported, -->
<!-- but may lead to some differences in layout.   -->

<html>
<head>
<%@include file="/WEB-INF/inc/head.jsp"%>
<%@include file="/WEB-INF/inc/redirect.jsp"%>
<%@ page import="dashboard.model.*" %>
<%@ page import="dashboard.registry.*" %>
<%@ page import="java.util.*" %>
</head>
<body>
<div data-role="page">
<div data-role="header" data-id='header' data-position="fixed">
	<%
		String std = (request.getParameter("std")==null) ? "" : request.getParameter("std");
		Student student = StudentRegistry.getUserByUserName(std);
		Student currentStudent = (Student)session.getAttribute("student");
		if(student.getUserName().equals(currentStudent.getUserName()))
			out.println("<a href='/jsp/menu.jsp' data-icon='back'>Terug</a>");
		else
			out.println("<a href='/jsp/friends/list.jsp' data-icon='back'>Terug</a>");
	%>
	<h1><%=student.getFirstName() + " " + student.getLastName()%></h1>
	<a href="/logout" data-role="button" data-icon="back" class="ui-btn-right">Afmelden</a>
	<div data-role="navbar">
		<ul>
			<li><a href="info.jsp?std=<%=std%>">Info</a></li>
			<li><a href="achievements.jsp?std=<%=std%>">Achievements</a></li>
			<li><a href="courses.jsp?std=<%=std%>">Vakken</a></li>
		</ul>
	</div><!-- /navbar -->
</div><!-- /header -->
<div data-role="content">
	<div>
		<h3>Persoonlijke informatie</h3>
		<h4>Voornaam:</h4>
		<p><%=student.getFirstName()%></p>
		<h4>Naam:</h4>
		<p><%=student.getLastName()%></p>
	</div>
</div><!-- /content -->
</div><!-- /page -->
</body>
</html>