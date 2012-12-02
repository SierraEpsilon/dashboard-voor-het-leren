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
<%@include file="/WEB-INF/inc/redirect.jsp"%>
</head>
<body>
<div data-role="page">
<script>
$(document).bind("pageinit",function(){

	//javascript code that needs to access the DOM goes here
	
});
</script>
<div data-role="header" data-id='header' data-position="fixed">

	<h1>Menu</h1>
	<a href="/logout" data-role="button" data-icon="back" class="ui-btn-right">Afmelden</a>

</div><!-- /header -->
<div data-role="content">

	<ul data-role="listview">
	<li><a href="/stats_all.jsp">Statistieken</a></li>
	<li><a href="/track">Tracken</a></li>
	<li><a href="/achievements">Achievements</a></li>
	<li><a href="/settings">Instellingen</a></li>
	<li><a href="/add_manual">Toevoegen van studiemoment</a></li>
	<li><a href="/friends">Vrienden</a></li>
	
</ul>
	
	
</div><!-- /content -->
</div><!-- /page -->
</body>
</html>