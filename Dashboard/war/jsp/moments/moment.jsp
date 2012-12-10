<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- The HTML 4.01 Transitional DOCTYPE declaration-->
<!-- above set at the top of the file will set     -->
<!-- the browser's rendering engine into           -->
<!-- "Quirks Mode". Replacing this declaration     -->
<!-- with a "Standards Mode" doctype is supported, -->
<!-- but may lead to some differences in layout.   -->

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
	String mmt = (request.getParameter("mmt")==null) ? "" : request.getParameter("mmt");
	Student student = (Student)session.getAttribute("student");
	StudyMoment moment = student.getMoment(mmt);
	CourseContract courseContract = null;
	for(CourseContract c: student.getCourses()){
		if(c.getCourse().equals(moment.getCourse())){
			courseContract = c;
		}
	}
%>
<div data-role="page">
<div data-role="header" data-id='header' data-position='fixed'>
	<a href="/jsp/moments/list.jsp" data-role="button" data-icon="grid">Back</a>
	<h1>Momentinformatie</h1>
	<a href="/logout" data-role="button" data-icon="back" class="ui-btn-right">Afmelden</a>
</div><!-- /header -->
<div data-role="content">
	<form method='post' action='/moments'>
	<div>
		<h3>Rapportering</h3>
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
		<%name = moment.getStart().toString(); %>
		<button  type='submit' data-inline='true' data-icon='delete' name='submit' value='remove_<%=name%>'>Verwijder</button>
	</div>
	</form>
</div><!-- /content -->
</div><!-- /page -->
</body>
</html>