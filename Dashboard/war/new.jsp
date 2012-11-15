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
<script>
$(document).ready(function(){
	$("#myForm").submit(function(){
		return false;
	});
});
</script>
<body>
<form id='myForm' method='post' action='track.jsp?mode=stop'>
<p><input type='text' name='start' value='Tue Oct 01 00:00:00 EDT 2002'>
<p><input type='submit' value='TESTEN'>
</form>
</body>
</html>
