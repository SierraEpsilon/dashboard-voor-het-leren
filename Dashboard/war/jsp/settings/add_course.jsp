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
<%@ page import="dashboard.model.*" %>
<%@ page import="dashboard.registry.*" %>
<%@ page import="java.util.*" %>
</head>
<body>
<div data-role="page">
<div data-role="header" data-id='header' data-position="fixed">
	<h1>Learnalyzer</h1>
	<a href="/logout" data-role="button" data-icon="back" class="ui-btn-right">Afmelden</a>
</div><!-- /header -->
<div data-role="content">
<form method='post' action='/add_course'>
	<ul data-role='listview'>
	<%
		Set<String> branchNames = CourseRegistry.getBranches().keySet();
		HashMap<String,Branch> branches = CourseRegistry.getBranches();
		out.println("<div data-role='collapsible-set'>");
		for(String branch: branchNames){
		out.println("<div data-role='collapsible'>"); 
				out.println("<h3>" + branch + "</h3>"); 
				out.println("<div data-role='controlgroup'>");
				for(Course course :branches.get(branch).getCourses()){
					out.println("<li><input type='submit' name='submit' value='" + course + "'/>");
				}
				out.println("</div></div>"); 
 		} 
		out.println("</div>"); 
 	%>
 	</ul>
</form>
</div><!-- /content -->
</div><!-- /page -->
</body>
</html>