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
<script>
$(document).bind("pageinit",function(){
	$("#myCourseForm").submit(function(){
		var ret = '';
		$("input[type='checkbox']").each(function(){
			if($(this).attr("checked")=="checked"){
				ret += ($(this).attr("name") + ";");
			}
			$("#myCourseForm>input[name='courses']").attr("value",ret);
		});
		return true;
	});
});
</script>
<div data-role="header" data-id='header' data-position="fixed">
	<h1>Learnalyzer</h1>
</div><!-- /header -->
<div data-role="content">
<form id='myCourseForm' action="/register" method="post">
	<input type='submit' name='submit' value='Opslaan'>
	<input type='hidden' name='courses'>
</form>
	<ul data-role='listview' style="margin-top: 5px">
	<%
		Set<String> branchNames = CourseRegistry.getBranches().keySet();
		HashMap<String,Branch> branches = CourseRegistry.getBranches();
		out.println("<div data-role='collapsible-set'>");
		for(String branch: branchNames){
		out.println("<div data-role='collapsible'>"); 
				out.println("<h3>" + branch + "</h3>"); 
				out.println("<div data-role='controlgroup'>");
				ArrayList<Course> courseList = branches.get(branch).getCourses();
				Collections.sort(courseList, new CourseNameComparator());
				for(Course course :courseList){
				out.println("<li><label><input type='checkbox' name='" + course + "' />" + course + "</label>");
				}
				out.println("</div></div>"); 
 		} 
		out.println("</div>"); 
 %>
</div><!-- /content -->
</div><!-- /page -->
</body>
</html>