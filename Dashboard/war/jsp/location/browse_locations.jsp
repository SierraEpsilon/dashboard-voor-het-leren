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
<div data-role="page">
<div data-role="header" data-id='header' data-position="fixed">
	<%
		String std = (request.getParameter("std")==null) ? "" : request.getParameter("std");
		Student student = StudentRegistry.getUserByUserName(std);
	%>
	<h1>Locatie toevoegen</h1>
	<a href="/logout" data-role="button" data-icon="back" class="ui-btn-right">Afmelden</a>
	<a href="/jsp/menu.jsp" data-role="button" data-icon="grid" class="ui-btn-left">Menu</a>
	<div data-role="navbar">
		<ul>
			<li><a href="/jsp/location/add_location.jsp?std=<%=std%>">Locatie toevoegen</a></li>
			<li><a href="/jsp/location/browse_locations.jsp?std=<%=std%>">Bestaande locaties</a></li>
		</ul>
	</div><!-- /navbar -->
</div><!-- /header -->
<div data-role="content">
	<div>
		<h3>Locaties</h3>
		<ul data-role="listview" style="margin: 5px">
		<%
			ArrayList<Location> locations = student.getStarredLocations();
			for(Location location : locations){
				out.println("<li>" + location.getName() + "</li>");
			}
		%>
		</ul>
	</div>
</div><!-- /content -->
</div><!-- /page -->
</body>
</html>