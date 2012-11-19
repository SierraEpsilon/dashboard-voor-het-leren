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
</div><!-- /header -->
<div data-role="content">
<%
	out.println("<ul>");
	List<Student> users = StudentRegistry.getUsers();
	Iterator it = users.iterator();
	while(it.hasNext()){
		Student user = (Student)it.next();
		String name = user.getUserName();
		String email = user.getMail();
		String moment = "geen";
		if(user.getCurrentStudyMoment() != null)
			moment = user.getCurrentStudyMoment().getCourse().toString();
		out.println("<li>" + name + " " + email + "</li>");
		out.println("<p>currentVak:" + moment + "</p>");
		out.println("<p>" + user.getStudyMoments().size() + "</p>");
	}
	out.println("</ul>");
%>
</div><!-- /content -->
</div><!-- /page -->
</body>
</html>