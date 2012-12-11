<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- The HTML 4.01 Transitional DOCTYPE declaration-->
<!-- above set at the top of the file will set     -->
<!-- the browser's rendering engine into           -->
<!-- "Quirks Mode". Replacing this declaration     -->
<!-- with a "Standards Mode" doctype is supported, -->
<!-- but may lead to some differences in layout.   -->

<%@page import="dashboard.util.Statistics"%>
<html>
<head>
<%@include file="/WEB-INF/inc/head.jsp"%>
<%@include file="/WEB-INF/inc/redirect.jsp"%>
<%@ page import="dashboard.model.*" %>
<%@ page import="dashboard.registry.*" %>
<%@ page import="dashboard.model.achievement.Achievement" %>
<%@ page import="dashboard.util.AchievementProgressComparator" %>
<%@ page import="java.util.*" %>
</head>
<body>
<%
	Student student = (Student)session.getAttribute("student");
	ArrayList<StudyMoment> moments = student.getStudyMoments();
	StudyMoment moment = moments.get(moments.size() -1);
	CourseContract courseContract = null;
	for(CourseContract c: student.getCourses()){
		if(c.getCourse().equals(moment.getCourse())){
			courseContract = c;
		}
	}
%>
<div data-role="page">
<div data-role="header" data-id='header' data-position='fixed'>
	<a href="/jsp/menu.jsp" data-role="button" data-icon="grid">Menu</a>
	<h1>Tracking Happy</h1>
	<a href="/logout" data-role="button" data-icon="back" class="ui-btn-right">Afmelden</a>
</div><!-- /header -->
<div data-role="content">
	<div>
		<h3>Eind Rapportering</h3>
		<h4>Tijd:</h4>
		<%
			String name = "";
			String timeString = "";
			long time = moment.getTime();
			long sec = (time%60);
				if(time<60){
					name = (time==1) ? " seconde" : " seconden";
					timeString = sec + " " + name;
				}
				else{
					time = ((time-sec)/60);
					long min = (time%60);
						if(time<60){
							name = (time==1) ? " minuut" : " minuten";
							timeString = min + " " + name;
						}else{
							time = ((time-min)/60);
							long hours = time;
							timeString = hours + " uur " + min + " minuten";
						}
				}
		%>
		<p><%=timeString%></p>
		<h4>Vak:</h4>
		<p><%=moment.getCourse().getName()%></p>
		<%
			if(moment.getKind().equals("Theorie"))
				out.println("<h4>Aantal gestudeerde bladzijden:</h4>");
			else
				out.println("<h4>Aantal gemaakte oefeningen:</h4>");
		%>
		<p><%=moment.getAmount()%></p>
		<%
			if(moment.getLocation()!= null){
				out.println("<h4>Locatie:</h4>");
				out.println("<p>" + moment.getLocation().getAlias() + ":</p>");
			}
		%>
		<h3>U bent nu level:</h3>
		<%
			long lvlTime = Statistics.getTime(moment.getCourse(), student.getStudyMoments());
		%>
		<p>Level <%=courseContract.getLevel(lvlTime) %></p>
		<div id="levelbar"></div>
		<%
			String levelbarJS = "$('#levelbar').progressbar({max:" + courseContract.getTimeNeededNext(lvlTime) + "});";
			levelbarJS += "$('#levelbar').progressbar({value:" + (courseContract.getTimeNeededNext(lvlTime) - courseContract.getTimeUntilNext(lvlTime)) + "});";
		%>
			
		<h3>Achievements met vooruitgang:</h3>
		<ul data-role="listview">
			<%
				ArrayList<Achievement> changedAchievements = (ArrayList<Achievement>)session.getAttribute("changedAchievements");
				Collections.sort(changedAchievements, new AchievementProgressComparator(student));
				String output = "";
				int id = 1;
				String progBarJS2 = "";
				String progBarJS = "";
				Iterator<Achievement> ait = changedAchievements.iterator();
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
				out.println(output);
			%>
		</ul>
	</div>
	<script>
	<%=levelbarJS%>
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