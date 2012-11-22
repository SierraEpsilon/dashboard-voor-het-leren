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
	<a href="/logout" data-role="button" data-icon="back" class="ui-btn-right">Afmelden</a>
</div><!-- /header -->
<div data-role="content">
<form method='post' action='/settings'>
	<%
		Student student = (Student)session.getAttribute("student");
	%>
	<div>
		<h3>Persoonlijke informatie</h3>
		<label for="name">Voornaam:</label>
		<input type="text" name="firstname" value="<%=student.getFirstName()%>"/>
		</h>
		<label for="name">Naam:</label>
		<input type="text" name="lastname" value="<%=student.getLastName()%>"/>
		<button type="submit" name="submit" value="namechange">Verander naam</button>
	</div>
	<div style="margin-top: 50px">
		<h3>Paswoord</h3>
		<label for="name">Oud paswoord:</label>
		<input type="text" name="pass1" value=""/>
		<label for="name">Nieuw paswoord:</label>
		<input type="text" name="pass2" value=""/>
		<label for="name">Herhaal nieuw paswoord:</label>
		<input type="text" name="pass3" value=""/>
		<button type="submit" name="submit" value="passchange">Verander paswoord</button>
	</div>
	<div style="margin-top: 50px">
		<h3>Vakken</h3>
		<ul data-role="listview" style="margin: 5px">
		<%
			for(CourseContract course :student.getCourses() ){
				String name = course.getCourse().getName();
				out.println("<li data-inline='true'>" + name + "</li>");
				out.println("<button type='submit' name='submit' value='remove_" + name + "'>remove</button>");
			}
		%>
		</ul>
		<button type="submit" name="submit" value="voeg">Voeg een vak toe</button>
	</div>
</form>
</div><!-- /content -->
</div><!-- /page -->
</body>
</html>