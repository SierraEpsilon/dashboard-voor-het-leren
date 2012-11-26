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
	$("#settings2").submit(function(){
		var pass1 = $("#settings2>input[name='pass1']").val();
		var pass2 = $("#settings2>input[name='pass2']").val();
		var pass3 = $("#settings2>input[name='pass3']").val();
		var cont = true;
		cont = (pass1=="") ? false : cont;
		cont = (pass2=="") ? false : cont;
		cont = (pass3=="") ? false : cont;
		if(!cont){
			$("#msg").text("Vul alle velden in");
			return false;
		}else if(pass2!=pass2){
			$("#msg").text("De wachtwoorden komen niet overeen");
			return false;
		}else{
			$("input[name='password']").attr("value",pass1);
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
<form id="settings2" method='post' action='/settings'>
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
</form>
</div><!-- /content -->
<div data-role='footer' data-id="footer_settings" data-position="fixed">
	<a href="/menu.jsp" data-role="button" data-icon="back">menu</a>
</div>
</div><!-- /page -->
</body>
</html>