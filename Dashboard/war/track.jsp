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
<%@ page import="java.util.*" %>
</head>
<body>
<div data-role="page">
<div data-role="header">
		<h1>Learnalyzer</h1>
</div><!-- /header -->
<div data-role="content">
<%
String startTime;
int start;
if(request.getParameter("mode")!=null){
	start = 1;
	//startTime = (String) request.getSession().getAttribute("startTracking");
	Date startDate = (Date)request.getSession().getAttribute("start");
	Course course = (Course)request.getSession().getAttribute("course");
	String courseName = course.getName();
	startTime = startDate.toString();
	out.println("<p>Start: " + startTime);
	out.println("<p>Course: " + courseName);
	out.println("<p><form action='/track_finish.jsp' method='post'><input type='submit' value='STOP'>");
	
	out.println("</form>");
	out.println("<form method='post' action='/track'>");
	out.println("<input type='submit' name='submit' value='cancel'>");
	out.println("</form>");
	out.println("<p id='timePast'>");	
}else{
	start = 0;
	startTime = "";
	out.println("<form action='/track' method='post'>");
	out.println("<p>Course: <select><option>Analyse</option><option>Mechanica</option></select>");
	out.println("<input type='hidden' name='submit' value='start'>");
	out.println("<p><input type='submit' value='START'>");
	out.println("</form>");
}
%>
<script>
$(document).ready(function(){
	start = <%= start %>;
	<% if(start==1) {System.out.println("start="+"start"+";");}
	%>
	setTimePast();
	setInterval("setTimePast()",1000);
	
});

function setTimePast(){
	str = "";
	start = new Date("<%= startTime%>");
	now = new Date();
	diff = (now.getTime() - start.getTime());
	diff = Math.round(diff/1000);
	sec = (diff%60);
	if(diff<60){
		name = (diff==1) ? " seconde" : " seconden";
		str = sec + " " + name;
	}else{
		diff = ((diff-sec)/60);
		min = (diff%60);
		if(diff<60){
			name = (diff==1) ? " minuut" : " minuten";
			str = min + " " + name;
		}else{
			diff = ((diff-min)/60);
			hours = diff;
			str = hours + " uur " + min + " minuten";
		}
	}
	$('#timePast').text(str);
}
</script>
</div><!-- /content -->
<div data-role='footer' data-id="foo1" data-position="fixed">
	<a href="/logout" data-role="button" data-icon="back">Logout</a>
</div>
</div><!-- /page -->
</body>
</html>
