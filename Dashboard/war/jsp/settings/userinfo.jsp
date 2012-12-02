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
<%@ page import="dashboard.model.*" %>
<%@ page import="dashboard.registry.*" %>
<%@ page import="java.util.*" %>
</head>
<body>
<div data-role="page">
<div data-role="header" data-id='header' data-position="fixed">
	<h1>Learnalyzer</h1>
	<a href="/logout" data-role="button" data-icon="back" class="ui-btn-right">Afmelden</a>
	<a href="/jsp/menu.jsp" data-role="button" data-icon="grid" class="ui-btn-left">Menu</a>
<div data-role="navbar">
	<ul>
		<li><a href="userinfo.jsp">Personal</a></li>
		<li><a href="password.jsp">Password</a></li>
		<li><a href="courses.jsp">Vakken</a></li>
	</ul>
</div><!-- /navbar -->
</div><!-- /header -->
<div data-role="content">
	<%
		Student student = (Student)session.getAttribute("student");
		String msg = (request.getParameter("msg")==null) ? "" : request.getParameter("msg");
	%>
	<p id='msg' style='color:red;'><%=msg%></p>
<form id='settings1' method='post' action='/settings'>
	<div>
		<h3>Persoonlijke informatie</h3>
		<label for="name">Voornaam:</label>
		<input type="text" name="firstname" value="<%=student.getFirstName()%>"/>
		</h>
		<label for="name">Naam:</label>
		<input type="text" name="lastname" value="<%=student.getLastName()%>"/>
		<button type="submit" name="submit" value="namechange">Verander naam</button>
	</div>
</form>
</div><!-- /content -->
</div><!-- /page -->
</body>
</html>