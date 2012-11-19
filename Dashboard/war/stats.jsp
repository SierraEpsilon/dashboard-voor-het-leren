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
	out.println("<table>");
	
	Student student = (Student)session.getAttribute("student");
	ArrayList<StudyMoment> ccs = student.getStudyMoments();
	Iterator it = ccs.iterator();
	while(it.hasNext()){
		StudyMoment moment = (StudyMoment)it.next();
		String name = moment.getCourse().getName();
		String startDate = moment.getStart().toString();
		String endDate = moment.getEnd().toString();
		out.println("<tr><td>" 	+ name 		+ "</td><td>" 
								+ startDate + "</td><td>" 
								+ endDate 	+ "</td></tr>");
	}
	out.println("</table>");
%>
</div><!-- /content -->
<div data-role='footer' data-id="foo1" data-position="fixed">
	<a href="/track" data-role="button" data-icon="back">Track</a>
</div>
</div><!-- /page -->
</body>
</html>