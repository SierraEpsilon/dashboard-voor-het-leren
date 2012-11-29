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
	<div data-role="navbar">
		<ul>
			<li><a href="/friends_friends.jsp">Friends</a></li>
			<li><a href="/friends_requests.jsp">Requests</a></li>
			<li><a href="/friends_add.jsp">Add friend</a></li>
		</ul>
	</div><!-- /navbar -->
</div><!-- /header -->
<div data-role="content">
<form method='post' action='/friends'>
	<ul data-role="listview" data-filter="true">
	<%
		Student currentStudent = (Student)session.getAttribute("student");
		for(Student student: StudentRegistry.getUsers()){
			boolean already = false;
			if(student.getFriendRequests() != null)
				already = student.getFriendRequests().contains(currentStudent.getUserName());
			if(student.getFriendList() != null)
				already = (already) || (student.getFriendList().contains(currentStudent.getUserName()));
			if(!currentStudent.getUserName().equals(student.getUserName()) && !already){
				String name = student.getUserName();
				out.println("<li><button type='submit' name='submit' value='req_" + name + "'>" + name + "</button></li>");
			}
		}
	%>
	</ul>
</form>
</div><!-- /content -->
</div><!-- /page -->
</body>
</html>