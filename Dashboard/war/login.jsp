<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- The HTML 4.01 Transitional DOCTYPE declaration-->
<!-- above set at the top of the file will set     -->
<!-- the browser's rendering engine into           -->
<!-- "Quirks Mode". Replacing this declaration     -->
<!-- with a "Standards Mode" doctype is supported, -->
<!-- but may lead to some differences in layout.   -->

<html>
<head>
</head>
<body>
<%
String msg = (request.getParameter('msg')==null) ? "" : request.getParameter('msg');
%>
<form>
<table>
<p><%= msg%>
<tr><td>Gebruikersnaam</td><td><input type='text' name='username'></td></tr>
<tr><td>Wachtwoord</td><td><input type='password' name='password'></td></tr>
</table>
<p><input type='submit' value='AANMELDEN'>
<p><a href='register.jsp'>Nieuw account maken</a>
</form>
</body>
</html>