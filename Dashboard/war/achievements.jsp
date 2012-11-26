<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- The HTML 4.01 Transitional DOCTYPE declaration-->
<!-- above set at the top of the file will set     -->
<!-- the browser's rendering engine into           -->
<!-- "Quirks Mode". Replacing this declaration     -->
<!-- with a "Standards Mode" doctype is supported, -->
<!-- but may lead to some differences in layout.   -->

<html>
<head>

<%@include file="inc/head.jsp" %>
<%@include file="inc/redirect.jsp"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.HashMap" %>
<%@page import="dashboard.registry.AchievementRegistry" %>
<%@page import="dashboard.model.achievement.*" %>
<%@page import="dashboard.model.Student" %>
<%@page import="dashboard.model.Course" %>
</head>
<body>
<div data-role="page">
<div data-role="header" data-id='header' data-position='fixed'>
	<a href="/menu.jsp" data-role="button" data-icon="grid">Menu</a>
	<h1>Achievements</h1>
	<a href="/logout" data-role="button" data-icon="back" class="ui-btn-right">Afmelden</a>

</div><!-- /header -->
<div data-role="content">
<% Student student = (Student)session.getAttribute("student"); %>
	<ul data-role='listview'><div data-role='collapsible-set'>
		
		<% 
			HashMap<Course,ArrayList<Achievement>> achievementMap = (HashMap<Course,ArrayList<Achievement>>)session.getAttribute("achievementMap");
			String progBarJS = "";
			Iterator<Course> cit = achievementMap.keySet().iterator();
			int id = 1;
			while(cit.hasNext()){ 
				Course course = cit.next();
				out.println("<div data-role='collapsible'><h3>" + course.getName() + "</h3>");
				Iterator<Achievement> ait = achievementMap.get(course).iterator();
				while(ait.hasNext()){
					Achievement achievement = ait.next();
					out.println("<li id='progressbar" + id + "' data-icon='false'><a href='/achievement_detail.jsp?id="+ achievement.getId() + "'><img class='ui-li-icon' src='/inc/icons/" + achievement.getIcon() + "' style='z-index:2;'><div class='textinachievement'>" + achievement.getName() + "</div></a></li>");
					progBarJS += ("$('#progressbar" + id + "').progressbar({ max: 100 });");
					progBarJS += ("$('#progressbar" + id + "').progressbar({ value: " + Math.round(achievement.getProgress(student) * 100) + " });");
					//progBarJS += ("$('#progressbar" + id + "').removeClass('ui-corner-all');");
					//progBarJS += ("$('#progressbar" + id + "').removeClass('completed');");
					//progBarJS += ("$('#progressbar" + id + "').addClass('list-edited');");
					if(achievement.getProgress(student) >= 1){
						//progBarJS += ("$('#progressbar" + id + "').addClass('completed');");
					}
					id++;
				}
				out.println("</div>");
			}
		%>
	</div></ul>
<script type="text/javascript">
	<%=progBarJS%>
	$("div.ui-progressbar-value").removeClass("ui-corner-left");
	$("div.ui-progressbar-value").removeClass("ui-corner-right");
</script>
</div><!-- /content -->
</div><!-- /page -->
</body>
</html>