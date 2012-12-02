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
<%@ page import="java.util.*" %>
</head>
<body>
<div data-role="page">
<div data-role="header" data-id='header' data-position="fixed">
	<h1>Statistieken</h1>
	<a href="/logout" data-role="button" data-icon="back" class="ui-btn-right">Afmelden</a>
	<a href="/jsp/menu.jsp" data-role="button" data-icon="grid" class="ui-btn-left">Menu</a>
</div><!-- /header -->
<div data-role="content">
<ul data-role="listview"  data-autodividers="true" data-filter="true" data-divider-theme="c">
<%
String returl = request.getParameter("returl");
if(returl==null) returl = "";
Student student = (Student)session.getAttribute("student");
ArrayList<Course> ccs = student.getCourseList();
Collections.sort(ccs, new CourseNameComparator());
Iterator<Course> it = ccs.iterator();
while(it.hasNext()){
	Course course = it.next();
	String name = course.getName();
	String hrefAttr = returl + "?course=" + name;
	out.println("<li><a href='" + hrefAttr + "'>" + name + "</a></li>");
}

%>
</ul>
</div><!-- /content -->
</div><!-- /page -->
</body>
</html>
