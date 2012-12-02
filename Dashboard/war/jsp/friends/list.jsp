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
			<li><a href="list.jsp">Friends</a></li>
			<li><a href="requests.jsp">Requests</a></li>
			<li><a href="add.jsp">Add friend</a></li>
		</ul>
	</div><!-- /navbar -->
</div><!-- /header -->
<div data-role="content">
<form method='post' action='/friends'>
	<%
		Student student = (Student)session.getAttribute("student");
	%>
	<div>
		<h3>Vrienden</h3>
		<ul data-role="listview" style="margin: 5px">
		<%
			for(String friend :student.getFriendList() ){
				out.println("<li><a href='/jsp/profile/info.jsp?std="+ friend +"'>"+ friend + "</a></li>");
			}
		%>
		</ul>
	</div>
</form>
</div><!-- /content -->
</div><!-- /page -->
</body>
</html>