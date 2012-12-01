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
<div data-role="page" id="track_stop_jsp">
<div data-role="header" data-id='header' data-position="fixed">
	<h1>Learnalyzer</h1>
	<a href="/logout" data-role="button" data-icon="back" class="ui-btn-right">Afmelden</a>
	<a href="/menu.jsp" data-role="button" data-icon="grid" class="ui-btn-left">Menu</a>
</div><!-- /header -->
<div data-role="content">
<%
	String startTime = "0";
	String courseName = "";
	Date startDate = (Date)session.getAttribute("startTracking");
	Course course = (Course)session.getAttribute("course");
	if(course==null){
		response.sendRedirect("/track");
	}else{
		courseName = course.getName();
		startTime = startDate.toString();
	}
%>
	<fieldset data-role='fieldcontain'>
	
		<div data-role='collapsible' data-collapsed='false' data-theme='c' data-content-theme='c'>
			<h1>Algemeen</h1>
			<ul data-role='listview'>
				<li><%= courseName %></li>
			</ul>
		</div><div data-role='collapsible' data-collapsed='false' data-theme='c' data-content-theme='c'>
			<h1>Timer</h1>
			<ul data-role='listview'>
				<li name='time'></li>
			</ul>
		</div><div data-role='collapsible' data-collapsed='false' data-theme='c' data-content-theme='c'>
			<h1>Live Feed</h1>
			<ul data-role='listview'>
				<li>Alle studenten<span class='ui-li-count' name='allStudents'>0</span></li>
				<li>Voor dit vak<span class='ui-li-count' name='courseStudents'>0</span></li>
			</ul>
		</div>
	</fieldset>

<div class="ui-grid-a">
<div class="ui-block-a">
<form action='/track_finish.jsp' method='post'>
<input type='submit' value='Stop' data-icon='check'>
</form>
</div><div class="ui-block-b">
<form method='post' action='/track'>
<input type='submit' name='submit' value='Cancel' data-icon='delete'>
</form>
</div></div>


<script>
$("div#track_stop_jsp").bind("pageshow",function(){
	setTimePast();
	window.intervs = new Object();
	window.intervs.clock = setInterval("setTimePast()",1000);
	updateLiveFeed();
	window.intervs.feed = setInterval("updateLiveFeed()",20000);
});
$("div#track_stop_jsp").bind("pagehide",function(){
		clearInterval(window.intervs.clock);
		clearInterval(window.intervs.feed);
});

function setTimePast(){
	str = "";
	startms = "<%= startDate.getTime()%>";
	now = new Date();
	diff = (now.getTime() - startms);
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
	$("li[name='time']").text(str);
}

function updateLiveFeed(){
	$.getJSON("/feed",function(json){
		$("span[name='allStudents']").html(json.AllMates);
		$("span[name='courseStudents']").html(json.studyMates);
	});
}
</script>
</div><!-- /content -->
</div><!-- /page -->
</body>
</html>
