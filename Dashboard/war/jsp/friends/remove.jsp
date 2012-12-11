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
	<h1>Vrienden</h1>
	<a href="/logout" data-role="button" data-icon="back" class="ui-btn-right">Afmelden</a>
	<a href="/jsp/menu.jsp" data-role="button" data-icon="grid" class="ui-btn-left">Menu</a>
	<div data-role="navbar">
		<ul>
			<li><a href="/jsp/friends/list.jsp">Vrienden</a></li>
			<li><a href="/jsp/friends/requests.jsp">Vriendschapsverzoeken</a></li>
			<li><a href="/jsp/friends/add.jsp">Voeg vrienden toe</a></li>
			<li><a href="/jsp/friends/remove.jsp">Verwijder</a></li>
		</ul>
	</div><!-- /navbar -->
</div><!-- /header -->
<div data-role="content">
	<%
		Student currentStudent = (Student)session.getAttribute("student");
		currentStudent = StudentRegistry.getUserByUserName(currentStudent.getUserName());
	%>
<form method='post' action='/friends'>
	<ul data-role="listview" data-filter="true">
	<%
		for(String possibleEnemy: currentStudent.getFriendList())
			out.println("<li><button type='submit' name='submit' value='rem_" + possibleEnemy + "'>" + possibleEnemy + "</button></li>");
	%>
	</ul>
</form>
</div><!-- /content -->
</div><!-- /page -->
</body>
</html>