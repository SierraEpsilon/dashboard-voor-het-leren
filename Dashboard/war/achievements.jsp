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
<%@page import="dashboard.registry.AchievementRegistry" %>
<%@page import="dashboard.model.achievement.*" %>
<%@page import="dashboard.model.Student" %>
</head>
<body>
<div data-role="page">
<div data-role="header" data-id='header' data-position="fixed">

	<h1>Achievements</h1>
	<a href="/logout" data-role="button" data-icon="back" class="ui-btn-right">Afmelden</a>

</div><!-- /header -->
<div data-role="content">
<% Student student = (Student)session.getAttribute("student"); %>
	<ul data-role='listview'>
		
		<% 
			ArrayList<Achievement> achievementList = (ArrayList<Achievement>)session.getAttribute("achievementList");
			Iterator<Achievement> it = achievementList.iterator();
			String progBarJS = "";
			int id = 1;
			while(it.hasNext()){
				Achievement achievement = it.next();
				out.println("<li id='progressbar" + id + "' data-icon='false'><a href='menu.jsp'><img class='ui-li-icon' src='http://icons.iconarchive.com/icons/deleket/sleek-xp-software/256/Yahoo-Messenger-icon.png' style='z-index:2;'><div class='textinachievement'>" + achievement.getName() + "</div></a></li>");
				progBarJS += ("$('#progressbar" + id + "').progressbar({ max: 100 });");
				progBarJS += ("$('#progressbar" + id + "').progressbar({ value: " + Math.round(achievement.getProgress(student) * 100) + " });");
				progBarJS += ("$('#progressbar" + id + "').removeClass('ui-corner-all');");
				progBarJS += ("$('#progressbar" + id + "').removeClass('completed');");
				if(achievement.getProgress(student) >= 1){
					progBarJS += ("$('#progressbar" + id + "').addClass('completed');");
				}
				id++;
			}
		
		%>
	</ul>
<script type="text/javascript">
	<%=progBarJS%>
	$("div.ui-progressbar-value").removeClass("ui-corner-left");
	$("div.ui-progressbar-value").removeClass("ui-corner-right");
</script>
</div><!-- /content -->
<div data-role='footer' data-id="foo1" data-position="fixed">
	<a href="/menu.jsp" data-role="button" data-icon="grid">Menu</a>
</div>
</div><!-- /page -->
</body>
</html>