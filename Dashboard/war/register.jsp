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
</head>
<body>
<div data-role="page">
<div data-role="header">
		<h1>Register</h1>
</div><!-- /header -->
<div data-role="content">
<script>
$(document).ready(function(){
	$("#register").click(function(){
		var pass1 = $("input[name='password1']").val();
		var pass2 = $("input[name='password2']").val();
		var username = $("input[name='username']").val();
		var firstname = $("input[name='firstname']").val();
		var lastname = $("input[name='lastname']").val();
		var mail = $("input[name='mail']").val();
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
		}else if(pass1!=pass2){
			$("#msg").text("De wachtwoorden komen niet overeen");
		}else if(!patt.test(mail)){
			$("#msg").text("Geef een geldig emailadres");
		}else{
			$("input[name='password']").attr("value",pass1);
			$("input[name='username']").attr("value",username);
			$("input[name='firstname']").attr("value",firstname);
			$("input[name='lastname']").attr("value",lastname);
			$("input[name='mail']").attr("value",mail);
			$("form[name='submit']").submit();
		}
	});
});
</script>
<p id='msg' style='color:red;'>
<p>Voornaam<input type='text' name='firstname'>
Achternaam<input type='text' name='lastname'>
Gebruikersnaam<input type='text' name='username'>
E-mail<input type='text' name='mail'>
Wachtwoord<input type='password' name='password1'>
Herhaal wachtwoord<input type='password' name='password2'>
<p><button id='register'>REGISTREREN</button>
<form name='submit' method='post' action='/register'>
<input type='hidden' name='username'>
<input type='hidden' name='firstname'>
<input type='hidden' name='lastname'>
<input type='hidden' name='password'>
<input type='hidden' name='mail'>
</form>
</div><!-- /content -->
</div><!-- /page -->
</body>
</html>