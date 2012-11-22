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
<%@ page import="java.util.*" %>
</head>
<body>
<div data-role="page">
<div data-role="header" data-id='header' data-position="fixed">
		<h1>Register</h1>
		<a href="login.jsp" data-icon="back">Terug</a>
</div><!-- /header -->
<div data-role="content">
<script>
$(document).ready(function(){
	$("#myForm").submit(function(){
		var pass1 = $("#myForm>input[name='password1']").val();
		var pass2 = $("#myForm>input[name='password2']").val();
		var username = $("#myForm>input[name='username']").val();
		var firstname = $("#myForm>input[name='firstname']").val();
		var lastname = $("#myForm>input[name='lastname']").val();
		var mail = $("#myForm>input[name='mail']").val();
		var cont = true;
		var patt=/^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/;
		cont = (pass1=="") ? false : cont;
		cont = (pass2=="") ? false : cont;
		cont = (username=="") ? false : cont;
		cont = (firstname=="") ? false : cont;
		cont = (lastname=="") ? false : cont;
		cont = (mail=="") ? false : cont;
		if(!cont){
			$("#msg").text("Vul alle velden in");
			return false;
		}else if(pass1!=pass2){
			$("#msg").text("De wachtwoorden komen niet overeen");
			return false;
		}else if(!patt.test(mail)){
			$("#msg").text("Geef een geldig emailadres");
			return false;
		}else{
			$("input[name='password']").attr("value",pass1);
			return true;
		}
	});
});
</script>
<%
String msg = (request.getParameter("msg")==null) ? "" : request.getParameter("msg");
%>
<p id='msg' style='color:red;'><%=msg%></p>
<form id='myForm' method='post' action='/register'>
<%
String firstname = "";
String lastname = "";
String username = "";
String mail = "";
if(session.getAttribute("register.jsp")!=null){
	HashMap<String,String> values = (HashMap<String,String>)session.getAttribute("register.jsp");
	firstname = values.get("firstname");
	lastname = values.get("lastname");
	username = values.get("username");
	mail = values.get("mail");
}
%>
<label for="firstname">Voornaam</label>
<input type='text' name='firstname' value='<%=firstname%>'>
<label for="lastname">Achternaam</label>
<input type='text' name='lastname' value='<%=lastname%>'>
<label for="username">Gebruikersnaam</label>
<input type='text' name='username' value='<%=username%>'>
<label for="mail">Email</label>
<input type='text' name='mail' value='<%=mail%>'>
<input type='hidden' name='password'>
<label for="password1">Wachtwoord</label>
<input type='password' name='password1'>
<label for="password2">Herhaal wachtwoord</label>
<input type='password' name='password2'>
<input type='submit' name='submit' value='volgende'>
</form>
</div><!-- /content -->
</div><!-- /page -->
</body>
</html>