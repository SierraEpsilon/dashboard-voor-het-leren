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
<%
String msg = (request.getParameter("msg")==null) ? "" : request.getParameter("msg");
%>
<form method='post' action='/login'>
<p><%= msg%>
<p>Gebruikersnaam</td><td><input type='text' name='username'>
Wachtwoord</td><td><input type='password' name='password'>
</table>
<p><input type='submit' value='AANMELDEN'>
<p><a href='register.jsp'>Nieuw account maken</a>
</form>
</div><!-- /content -->
</div><!-- /page -->
</body>
</html>