!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
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
</div><!-- /header -->
<div data-role="navbar">
	<ul>
		<li><a href="/friends_friends.jsp">friends</a></li>
		<li><a href="/friends_requests.jsp">requests</a></li>
		<li><a href="/friends_add.jsp">add friend</a></li>
	</ul>
</div><!-- /navbar -->
<div data-role="content">
<form method='post' action='/friends'>
	<%
		Student student = (Student)session.getAttribute("student");
	%>
	<div>
		<h3>Requests</h3>
		<ul data-role="listview" style="margin: 5px">
		<%
			for(String stranger :student.getFriendRequests() ){
				out.println("<li>" + stranger + "</li>");
				out.println("<button type='submit' data-inline='true' data-icon='check' name='submit' value='add_" + stranger + "'>accept</button>");
				out.println("<button type='submit' data-inline='true' data-icon='delete' name='submit' value='deny_" + stranger + "'>ignore</button>");
			}
		%>
		</ul>
	</div>
</form>
</div><!-- /content -->
<div data-role='footer' data-id="footer_settings" data-position="fixed">
	<a href="/menu.jsp" data-role="button" data-icon="back">menu</a>
</div>
</div><!-- /page -->
</body>
</html>