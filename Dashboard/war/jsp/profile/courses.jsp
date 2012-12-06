<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- The HTML 4.01 Transitional DOCTYPE declaration-->
<!-- above set at the top of the file will set     -->
<!-- the browser's rendering engine into           -->
<!-- "Quirks Mode". Replacing this declaration     -->
<!-- with a "Standards Mode" doctype is supported, -->
<!-- but may lead to some differences in layout.   -->

<%@page import="dashboard.util.Statistics"%>
<%@page import="dashboard.model.achievement.Achievement"%>
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
	<%
		String std = (request.getParameter("std")==null) ? "" : request.getParameter("std");
		Student student = StudentRegistry.getUserByUserName(std);
	%>
	<a href="/jsp/friends/list.jsp" data-icon="back">Terug</a>
	<h1>Tracking Happy</h1>
	<a href="/logout" data-role="button" data-icon="back" class="ui-btn-right">Afmelden</a>
<div data-role="navbar">
	<ul>
		<li><a href="info.jsp?std=<%=std%>">Info</a></li>
		<li><a href="achievements.jsp?std=<%=std%>">Achievements</a></li>
		<li><a href="courses.jsp?std=<%=std%>">Vakken</a></li>
	</ul>
</div><!-- /navbar -->
</div><!-- /header -->
<div data-role="content">
	<div>
		<h3>Vakken</h3>
		<ul data-role="listview" style="margin: 5px">
		<%
			ArrayList<StudyMoment> moments = student.getStudyMoments();
			HashMap<String,Long> times = Statistics.getCourseTimes(moments, student.getCourses());
			int i = 1;
			String JS = "";
			for(CourseContract course : student.getCourses()){
				String name = course.getCourse().getName();
				long time = (times.get(name));
				int lvl = course.getLevel(time);
				long next = course.getTimeNeededNext(time);
				long untilNext = next - course.getTimeUntilNext(time);
				out.println("<h3>" + name + " (" + lvl + ")</h3>");
				out.println("<li id='progressbar" + i + "'></li>");
				JS += ("$('#progressbar" + i + "').progressbar({max: " + next + "});");
				JS += ("$('#progressbar" + i + "').progressbar({value: " + untilNext + "});");
				i++;
			}
		%>
		</ul>
	</div>
	<script>
		<%=JS%>
		$("div.ui-progressbar-value").removeClass("ui-corner-left");
		$("div.ui-progressbar-value").removeClass("ui-corner-right");
		$("div.ui-progressbar-value").addClass("ui-link-inherit");
		$("li.ui-progressbar").removeClass("ui-corner-all");
		$("li.ui-progressbar").removeClass("completed");
		$("li.ui-progressbar").addClass("custom-css");
		$("li.ui-progressbar").addClass("list-edited");
	</script>
</div><!-- /content -->
</div><!-- /page -->
</body>
</html>