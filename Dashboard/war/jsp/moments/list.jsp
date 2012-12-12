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
<%@include file="/WEB-INF/inc/redirect.jsp"%>
<%@ page import="dashboard.model.*" %>
<%@ page import="dashboard.registry.*" %>
<%@ page import="java.util.*" %>
</head>
<body>
<div data-role="page">
<div data-role="header" data-id='header' data-position="fixed">
	<%
		Student student = (Student)session.getAttribute("student");
	%>
	<a href="/jsp/menu.jsp" data-role="button" data-icon="grid" class="ui-btn-left">Menu</a>
	<h1>Studiemomenten</h1>
	<a href="/logout" data-role="button" data-icon="back" class="ui-btn-right">Afmelden</a>
</div><!-- /header -->
<div data-role="content">
	<div>
		<h3>Studiemomenten</h3>
		<ul data-role="listview" data-filter="true"  style="margin: 5px">
		<%
			ArrayList<StudyMoment> moments = student.getStudyMoments();
			if(moments != null && !moments.isEmpty())
				for(int i = moments.size() - 1; i < 0; i--){
					StudyMoment moment = moments.get(i);
					String name = moment.getStart().toString();
					out.println("<li><a href='/jsp/moments/moment.jsp?mmt="+ name +"'>"+ name + "</a></li>");
				}
		%>
		</ul>
	</div>
</div><!-- /content -->
</div><!-- /page -->
</body>
</html>