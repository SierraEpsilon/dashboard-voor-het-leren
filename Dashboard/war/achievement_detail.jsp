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
<%@page import="dashboard.model.Student" %>
<%@page import="dashboard.model.achievement.Achievement" %>
<%@page import="dashboard.registry.AchievementRegistry" %>
</head>
<body>
<div data-role="page">
<div data-role="header" data-id='header' data-position="fixed">
	<%
		Achievement achievement = AchievementRegistry.getByID(request.getParameter("id"));
		if(achievement== null){
			response.sendRedirect("/achievements");
		}
	%>
	<a href="/achievements" data-role="button" data-icon="back">Terug</a>
	<h1><% out.println(achievement.getName()); %></h1>
	<a href="/logout" data-role="button" data-icon="back" class="ui-btn-right">Afmelden</a>

</div><!-- /header -->
<div data-role="content">
	<% 
		Student student = (Student)session.getAttribute("student");
		int percent = Math.round(achievement.getProgress(student) * 100);
		String JSpbar = "$('#pbar').progressbar({value:" + percent + "});";
		if(percent >= 100){
			JSpbar += "$('#pbar').addClass('completed');";
		}
		
		out.println("<img height='160' width='160' src='/inc/icons/" + achievement.getIcon() + "'>");
		out.println("<p>Voortgang: " + percent + "% </p>"); %>
	<div id=pbar></div>
	<% out.println("<p>" + achievement.getDesc() + "</p>"); %>
	<script>
		$("#pbar").progressbar({ max: 100});
		<%=JSpbar%>
	</script>
</div><!-- /content -->
</div><!-- /page -->
</body>
</html>