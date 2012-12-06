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
</head>
<body>
<div data-role="page">
<script>
$(document).bind("pageinit",function(){

	//javascript code that needs to access the DOM goes here
	
});
</script>
<div data-role="header" data-id='header' data-position="fixed">

	<h1>Menu</h1>
	<a href="/logout" data-role="button" data-icon="back" class="ui-btn-right">Afmelden</a>

</div><!-- /header -->
<div data-role="content">
	<%
		Student student = (Student)session.getAttribute("student");
		String username = student.getUserName();
		String friendAdd = "";
		int numberOfRequests = student.getRequestNumbers();
		String opt = "";
		if(numberOfRequests != 0){
			opt = "<span class='ui-li-count' name='courseStudents'>"numberOfRequests"</span>";
		}
	%>
	<ul data-role="listview">
		<li><a href="/track">Tracken</a></li>
		<li><a href="/add_manual">Toevoegen van studiemoment</a></li>
		<li><a href="/friends">Vrienden  </a> <%=opt%> </li>
		<li><a href="/stats">Statistieken</a></li>
		<li><a href="/achievements">Achievements</a></li>
		<li><a href="/settings">Instellingen</a></li>
		<li><a href="/jsp/profile/info.jsp?std=<%=username%>">Profielpagina</a></li>
	</ul>
	
</div><!-- /content -->
</div><!-- /page -->
</body>
</html>