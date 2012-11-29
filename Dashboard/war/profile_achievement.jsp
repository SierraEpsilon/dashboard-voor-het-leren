<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- The HTML 4.01 Transitional DOCTYPE declaration-->
<!-- above set at the top of the file will set     -->
<!-- the browser's rendering engine into           -->
<!-- "Quirks Mode". Replacing this declaration     -->
<!-- with a "Standards Mode" doctype is supported, -->
<!-- but may lead to some differences in layout.   -->

<%@page import="dashboard.model.achievement.Achievement"%>
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
	<%
		String std = (request.getParameter("std")==null) ? "" : request.getParameter("std");
		Student student = StudentRegistry.getUserByUserName(std);
	%>
<div data-role="navbar">
	<ul>
		<li><a href="/profile_info.jsp?std=<%=std%>">info</a></li>
		<li><a href="/profile_achievement.jsp?std=<%=std%>">achievements</a></li>
	</ul>
</div><!-- /navbar -->
<div data-role="content">
	<div>
		<h3>Achievements</h3>
		<ul data-role="listview" style="margin: 5px">
		<%
			for(Achievement achievement :AchievementRegistry.getCompletedAchievements(student)){
				String name = achievement.getName();
				out.println("<li><img class='ui-li-icon' src='/inc/icons/" + achievement.getIcon() + "' style='z-index:2;'>" + name + "</li>");
			}
		%>
		</ul>
	</div>
</div><!-- /content -->
<div data-role='footer' data-id="footer_settings" data-position="fixed">
	<from><input type="button" value="back" data-inline="true" data-icon="back" onClick="history.go(-1);return true;"></form>
</div>
</div><!-- /page -->
</body>
</html>