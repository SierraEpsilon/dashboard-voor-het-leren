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
<%@ page import="java.util.*" %>
</head>
<body>
<div data-role="page" id="register_jsp">
<div data-role="header" data-id='header' data-position="fixed">
		<h1>Registreren</h1>
		<a href="/jsp/login/login.jsp" data-icon="back">Terug</a>
</div><!-- /header -->
<div data-role="content">
<script>
$("div#register_jsp").bind("pageshow",function(){

	$("#username").change(function(){
		var given =  $("#username").val();
		if(given.length > 5){
			$("#msg2").text("");
		}else{
			$("#msg2").text("De gebruikersnaam moet minimaal 6 en maximaal 24 tekens bevatten.");
		}
	});
	
	$("#password1").change(function(){
		var pw1 =  $("#password1").val();
		var pw2 =  $("#password2").val();
		if(pw1==pw2){
			$("#msg4").text("");
		}
		if(pw1.length > 5){		
			$("#msg3").text("");
		}else{
			$("#msg3").text("Het wachtwoord moet minimaal 6 en maximaal 24 tekens bevatten.");
		}
	});
	
	$("#password2").change(function(){
		var pw1 =  $("#password1").val();
		var pw2 = $("#password2").val();
		if(pw1==pw2){
			$("#msg4").text("");
		}else{
			$("#msg4").text("De opgegeven wachtwoorden zijn niet aan elkaar gelijk.");
		}
	});
	
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
			$("#msg").text("");$("#msg2").text("");$("#msg3").text("");$("#msg4").text("");
			$("#password1").attr("value",pass1);
			return true;
		}
	});
});
$(document).tooltip();
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
<input type='text' id='firstname' name='firstname' value='<%=firstname%>' >
<label for="lastname">Achternaam</label>
<input type='text' id='lastname' name='lastname' value='<%=lastname%>'>
<label for="username">Gebruikersnaam</label>
<input type='text' id='username' name='username' value='<%=username%>' placeholder='Minstens 6 en maximaal 24 tekens lang'>
<%
String msg2 = (request.getParameter("msg2")==null) ? "" : request.getParameter("msg2");
%>
<p id='msg2' style='color:red;'><%=msg2%></p>
<label for="mail">Email</label>
<input type='text' id='mail' name='mail' value='<%=mail%>' >
<input type='hidden' name='password'>
<label for="password1">Wachtwoord</label>
<input type='password' id='password1' name='password1' placeholder='Minstens 6 en maximaal 24 tekens lang'>
<%
String msg3 = (request.getParameter("msg3")==null) ? "" : request.getParameter("msg3");
%>
<p id='msg3' style='color:red;'><%=msg3%></p>
<label for="password2">Herhaal wachtwoord</label>
<input type='password' id='password2' name='password2' >

<%
String msg4 = (request.getParameter("msg4")==null) ? "" : request.getParameter("msg4");
%>
<p id='msg4' style='color:red;'><%=msg4%></p>

<input type='submit' name='submit' value='Volgende'>



</form>
</div><!-- /content -->
</div><!-- /page -->
</body>
</html>