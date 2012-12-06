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
<%@ page import="java.util.*" %>
</head>
<body>
<%
	Student student = (Student)session.getAttribute("student");
	ArrayList<StudyMoment> moments = student.getStudyMoments();
	StudyMoment moment = moments.get(moments.size() -1);
%>
<div data-role="page">
<div data-role="header" data-id='header' data-position='fixed'>
	<a href="/jsp/menu.jsp" data-role="button" data-icon="grid">Menu</a>
	<h1>Tracking Happy</h1>
	<a href="/logout" data-role="button" data-icon="back" class="ui-btn-right">Afmelden</a>
</div><!-- /header -->
<div data-role="content">
	<div>
		<h3>Eind Raportering</h3>
		<h4>Tijd:</h4>
		<p><%=moment.getTime()%> seconden</p>
		<h4>Vak:</h4>
		<p><%=moment.getCourse().getName()%></p>
		<%
			if(moment.getKind().equals("Theorie"))
				out.println("<h4>Aantal gestudeerde bladzijden:</h4>");
			else
				out.println("<h4>Aantal gemaakte oefeningen:</h4>");
		%>
		<p><%=moment.getAmount()%></p>
	</div>
</div><!-- /content -->
</div><!-- /page -->
</body>
</html>