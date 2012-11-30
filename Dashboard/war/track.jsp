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
<div data-role="page" id="track_jsp">
<div data-role="header" data-id='header' data-position="fixed">
	<h1>Learnalyzer</h1>
	<a href="/logout" data-role="button" data-icon="back" class="ui-btn-right">Afmelden</a>
	<a href="/menu.jsp" data-role="button" data-icon="grid" class="ui-btn-left">Menu</a>
</div><!-- /header -->
<div data-role="content">
<%
String startTime;
int stop;
if(request.getParameter("mode")!=null && request.getParameter("mode").equals("stop")){
	stop = 1;
	Date startDate = (Date)session.getAttribute("startTracking");
	Course course = (Course)session.getAttribute("course");
	String courseName = course.getName();
	startTime = startDate.toString();
	out.println("<p>Start: " + startTime);
	out.println("<p>Course: " + courseName);
	out.println("<p><form action='/track_finish.jsp' method='post'><input type='submit' value='Stop'>");
	
	out.println("</form>");
	out.println("<form method='post' action='/track'>");
	out.println("<input type='submit' name='submit' value='Cancel'>");
	out.println("</form>");
	out.println("<div id='timePast'>timePast</div>");
	out.println("<div id='liveFeed'>liveFeed</div>");		
}else{
	stop = 0;
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
	out.println("<input type='hidden' name='submit' value='Start'>");
	out.println("<p><input type='submit' value='Start'>");
	out.println("</form>");
}
%>
<script>
stop = <%= stop %>;
if(stop){
	$("div#track_jsp").bind("pageshow",function(){
		setTimePast();
		window.intervs = new Object();
		window.intervs.clock = setInterval("setTimePast()",1000);
		updateLiveFeed();
		window.intervs.feed = setInterval("updateLiveFeed()",20000);
	});
	$("div#track_jsp").bind("pagehide",function(){
			clearInterval(window.intervs.clock);
			clearInterval(window.intervs.feed);
	});
}

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
	$('div#timePast').text(str);
}

function updateLiveFeed(){
	$.getJSON("/feed",function(json){
		var str = "<table><tr><td>All students:</td><td>"+json.AllMates+"</td></tr>";
		str += ("<tr><td>Course students:</td><td>"+json.studyMates+"</td></tr></table>");
		$("div#liveFeed").html(str);
	});
}
</script>
</div><!-- /content -->
</div><!-- /page -->
</body>
</html>