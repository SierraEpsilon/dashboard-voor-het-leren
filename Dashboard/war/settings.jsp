<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- The HTML 4.01 Transitional DOCTYPE declaration-->
<!-- above set at the top of the file will set     -->
<!-- the browser's rendering engine into           -->
<!-- "Quirks Mode". Replacing this declaration     -->
<!-- with a "Standards Mode" doctype is supported, -->
<!-- but may lead to some differences in layout.   -->

<html>
<head>
<%@include file="inc/head.jsp"%>
<%@ page import="dashboard.model.*" %>
<%@ page import="dashboard.registry.*" %>
<%@ page import="java.util.*" %>
</head>
<body>
<div data-role="page">
<div data-role="header" data-id='header' data-position="fixed">
	<h1>Learnalyzer</h1>
</div><!-- /header -->
<div data-role="content">
	<%
		Student student = (Student)session.getAttribute("student");
	%>
	<div>
		<h3>Persoonlijke informatie</h3>
		<label for="name">Voornaam:</label>
		<input type="text" name="name" id="name" value="<%=student.getFirstName()%>"/>
		</h>
		<label for="name">Naam:</label>
		<input type="text" name="name" id="name" value="<%=student.getLastName()%>"/>
	</div>
	<div style="margin-top: 50px">
		<h3>Paswoord</h3>
		<label for="name">Oud paswoord:</label>
		<input type="text" name="name" id="name" value=""/>
		<label for="name">Nieuw paswoord:</label>
		<input type="text" name="name" id="name" value=""/>
		<label for="name">Herhaal nieuw paswoord:</label>
		<input type="text" name="name" id="name" value=""/>
	</div>
	<script>
		$('a').buttonMarkup({ inline: true });
	</script>
	<div style="margin-top: 50px">
		<h3>Vakken</h3>
		<ul data-role="listview" style="margin: 5px">
		<%
			for(CourseContract course :student.getCourses() ){
				String name = course.getCourse().getName();
				out.println("<li data-inline='true'>" + name + "</li>");
				out.println("<button input type='submit' value='remove_" + name + "'>remove</button>");
			}
		%>
		</ul>
		<input type="submit" value="Voeg een vak toe" />
	</div>
</div><!-- /content -->
</div><!-- /page -->
</body>
</html>