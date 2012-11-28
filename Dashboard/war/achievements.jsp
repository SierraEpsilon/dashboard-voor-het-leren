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
	<div class='list-edited' data-role='collapsible-set' data-inset='false'>
		
		<% 
			HashMap<Course,ArrayList<Achievement>> achievementMap = (HashMap<Course,ArrayList<Achievement>>)session.getAttribute("achievementMap");
			String progBarJS = "";
			String progBarJS2 = "";
			Iterator<Course> cit = achievementMap.keySet().iterator();
			int id = 1;
			while(cit.hasNext()){ 
				Course course = cit.next();
				out.println("<div class='list-edited' data-role='collapsible' data-iconpos='right'>");
				if(course==null){
					out.println("<h3 class='list-edited'>Vakoverschrijdend</h3>");
				} else {
					out.println("<h3 class='list-edited'>" + course.getName() + "</h3>");
				}
				out.println("<ul data-role='listview'>");
				Iterator<Achievement> ait = achievementMap.get(course).iterator();
				while(ait.hasNext()){
					Achievement achievement = ait.next();
					out.println("<li id='progressbar" + id + "' data-icon='false'><a href='/achievement_detail.jsp?id="+ achievement.getId() + "'><img class='ui-li-icon' src='/inc/icons/" + achievement.getIcon() + "' style='z-index:2;'><div class='textinachievement'>" + achievement.getName() + "</div></a></li>");
					progBarJS += ("$('#progressbar" + id + "').progressbar({ max: 100 });");
					progBarJS += ("$('#progressbar" + id + "').progressbar({ value: " + Math.round(achievement.getProgress(student) * 100) + " });");
					if(achievement.getProgress(student) >= 1){
						progBarJS2 += ("$('#progressbar" + id + "').addClass('completed');");
					}
					id++;
				}
				out.println("</ul></div>");
			}
		%>
	</div>
<script type="text/javascript">
	<%=progBarJS%>
	$("div.ui-progressbar-value").removeClass("ui-corner-left");
	$("div.ui-progressbar-value").removeClass("ui-corner-right");
	$("div.ui-progressbar-value").addClass("ui-link-inherit");
	$("li.ui-progressbar").removeClass("ui-corner-all");
	$("li.ui-progressbar").removeClass("completed");
	$("li.ui-progressbar").addClass("list-edited");
	<%=progBarJS2%>
</script>
</div><!-- /content -->
</div><!-- /page -->
</body>
</html>