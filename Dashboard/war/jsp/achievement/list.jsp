<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- The HTML 4.01 Transitional DOCTYPE declaration-->
<!-- above set at the top of the file will set     -->
<!-- the browser's rendering engine into           -->
<!-- "Quirks Mode". Replacing this declaration     -->
<!-- with a "Standards Mode" doctype is supported, -->
<!-- but may lead to some differences in layout.   -->

<html>
<head>
<%@include file="/WEB-INF/inc/head.jsp" %>
<%@include file="/WEB-INF/inc/redirect.jsp"%>
<%@page import="java.util.ArrayList" %>
<%@page import="java.util.Iterator" %>
<%@page import="java.util.HashMap" %>
<%@page import="java.util.Collections" %>
<%@page import="dashboard.registry.AchievementRegistry" %>
<%@page import="dashboard.model.achievement.*" %>
<%@page import="dashboard.model.Student" %>
<%@page import="dashboard.model.Course" %>
<%@page import="dashboard.model.CourseNameComparator" %>
</head>
<body>
<div data-role="page">
<div data-role="header" data-id='header' data-position='fixed'>
	<a href="/jsp/menu.jsp" data-role="button" data-icon="grid">Menu</a>
	<h1>Achievements</h1>
	<a href="/logout" data-role="button" data-icon="back" class="ui-btn-right">Afmelden</a>

</div><!-- /header -->
<div data-role="content">
<% Student student = (Student)session.getAttribute("student"); %>
<%!
	String progBarJS = "";
	String progBarJS2 = "";
	int id = 1; 
%>
	<div class='custom-css' data-role='collapsible-set' data-inset='false'>
		
		<%
			HashMap<Course,ArrayList<Achievement>> achievementMap = (HashMap<Course,ArrayList<Achievement>>)session.getAttribute("achievementMap");
			
			out.println("<div data-role='collapsible'>");
			out.println("<h3>Vakoverschrijdend</h3>");
			out.println("<ul data-role='listview'>");
			out.println(printAchievementList(achievementMap.get(null), student));
			out.println("</ul></div>");
			
			achievementMap.keySet().remove(null);
			ArrayList<Course> courseList = new ArrayList<Course>(achievementMap.keySet());
			Collections.sort(courseList, new CourseNameComparator());
			Iterator<Course> cit = courseList.iterator();
			while(cit.hasNext()){ 
				Course course = cit.next();
				out.println("<div data-role='collapsible'>");
				out.println("<h3>" + course.getName() + "</h3>");
				out.println("<ul data-role='listview'>");
				out.println(printAchievementList(achievementMap.get(course), student));
				out.println("</ul></div>");
			}
		%>
		<%!	
			private String printAchievementList(ArrayList<Achievement> achievementList, Student student){
				String output = "";
				Iterator<Achievement> ait = achievementList.iterator();
				while(ait.hasNext()){
					Achievement achievement = ait.next();
					String themeString = "";
					output += ("<li id='progressbar" + id + "' data-icon='false'><a href='/jsp/achievement/detail.jsp?id="+ achievement.getId() + "'><img class='ui-li-icon custom-css' src='/inc/icons/" + achievement.getIcon() + "' style='z-index:2;'><div class='textinachievement'>" + achievement.getName() + "</div></a></li>");
					progBarJS += ("$('#progressbar" + id + "').progressbar({ max: 100 });");
					progBarJS += ("$('#progressbar" + id + "').progressbar({ value: " + Math.round(achievement.getProgress(student) * 100) + " });");
					if(achievement.getProgress(student) >= 1){
						progBarJS2 += ("$('#progressbar" + id + "').addClass('completed');");
					}

					if(achievement.getProgress(student) <= 0.005){
						progBarJS2 += ("$('#progressbar" + id + "').addClass('unstarted');");
					}
					id++;
				}
				return output;
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
	$("li.ui-progressbar").addClass("custom-css");
	$("li.ui-progressbar").addClass("list-edited");
	$("div.ui-progressbar-value").each(function(){
		$(this).click(function(){
			$(this).parent().children("a")[0].click();
		})
	});
	<%=progBarJS2%>
</script>
</div><!-- /content -->
</div><!-- /page -->
</body>
</html>