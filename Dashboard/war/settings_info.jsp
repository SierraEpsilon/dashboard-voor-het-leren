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
<div data-role="navbar">
	<ul>
		<li><a href="/settings_info.jsp">Personal</a></li>
		<li><a href="/settings_pass.jsp">Password</a></li>
		<li><a href="/settings_vak.jsp">Vakken</a></li>
	</ul>
</div><!-- /navbar -->
<div data-role="content">
<script>
$(document).ready(function(){
	$("#settings1").submit(function(){
		var firstname = $("#settings1>input[name='firstname']").val();
		var lastname = $("#settings1>input[name='lastname']").val();
		var cont = true;
		cont = (firstname=="") ? false : cont;
		cont = (lastname=="") ? false : cont;
		if(!cont){
			$("#msg").text("Vul alle velden in");
			return false;
		}else{
			return true;
		}
	});
});
</script>
	<%
		Student student = (Student)session.getAttribute("student");
		String msg = (request.getParameter("msg")==null) ? "" : request.getParameter("msg");
	%>
	<p id='msg' style='color:red;'><%=msg%></p>
<form id='settings1' method='post' action='/settings'>
	<div>
		<h3>Persoonlijke informatie</h3>
		<label for="name">Voornaam:</label>
		<input type="text" name="firstname" value="<%=student.getFirstName()%>"/>
		</h>
		<label for="name">Naam:</label>
		<input type="text" name="lastname" value="<%=student.getLastName()%>"/>
		<button type="submit" name="submit" value="namechange">Verander naam</button>
	</div>
</form>
</div><!-- /content -->
<div data-role='footer' data-id="footer_settings" data-position="fixed">
	<a href="/menu.jsp" data-role="button" data-icon="back">menu</a>
</div>
</div><!-- /page -->
</body>
</html>