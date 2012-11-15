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
<style type='text/css'>
.containing-element .ui-slider-switch { width: 20em }
</style>
</head>
<body>
<script>
function submitKind(){
	
}

</script>
<div data-role="page">
<div data-role="header">
		<h1>Learnalyzer</h1>
</div><!-- /header -->
<div data-role="content">
	Kies een vak:
	<ul data-role="listview">
<%
HttpSession session = request.getSession();
Student student = (Student)session.getAttribute("student");

%>	
	</ul>
</div><!-- /content -->
</div><!-- /page -->
</body>
</html>