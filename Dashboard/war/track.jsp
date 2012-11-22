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
<%@include file="inc/redirect.jsp"%>
<%@ page import="dashboard.model.*" %>
<%@ page import="java.util.*" %>
</head>
<body>
<div data-role="page">
<div data-role="header" data-id='header' data-position="fixed">
	<h1>Learnalyzer</h1>
	<a href="/logout" data-role="button" data-icon="back" class="ui-btn-right">Logout</a>
</div><!-- /header -->
<div data-role="content">
<%
String startTime;
int start;
if(request.getParameter("mode")!=null && request.getParameter("mode").equals("stop")){
	start = 1;
	Date startDate = (Date)session.getAttribute("startTracking");
	Course course = (Course)session.getAttribute("course");
	String courseName = course.getName();
	startTime = startDate.toString();
	out.println("<p>Start: " + startTime);
	out.println("<p>Course: " + courseName);
	out.println("<p><form action='/track_finish.jsp' method='post'><input type='submit' value='stop'>");
	
	out.println("</form>");
	out.println("<form method='post' action='/track'>");
	out.println("<input type='submit' name='submit' value='cancel'>");
	out.println("</form>");
	out.println("<p id='timePast'>");	
}else{
	start = 0;
	startTime = "";
	out.println("<form action='/track' method='post'>");
	//course list
	out.println("Course: <select name='courseinput'>");
	
	Student student = (Student)session.getAttribute("student");
	ArrayList<CourseContract> ccs = student.getCourses();
	Iterator it = ccs.iterator();
	while(it.hasNext()){
		CourseContract courseC = (CourseContract)it.next();
		String name = courseC.getCourse().getName();
		out.println("<option value='" + name + "'>" + name + "</option>");
	}
	out.println("</select>");
		//end course list
	out.println("<input type='hidden' name='submit' value='start'>");
	out.println("<p><input type='submit' value='start'>");
	out.println("</form>");
}
%>
<script>
$(document).bind("pageinit",function(){
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
	<a href="/stats" data-role="button" data-icon="grid">Stats</a>
</div>
</div><!-- /page -->
</body>
</html>
