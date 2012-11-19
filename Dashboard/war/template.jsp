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
<%@include file="inc/redirect.jsp"%>
</head>
<body>
<div data-role="page">
<script>
$(document).bind("pageinit",function(){

	//javascript code that needs to access the DOM goes here
	
});
</script>
<div data-role="header" data-id='header' data-position="fixed">

	<h1>INSERT PAGE HEADER</h1>
	<a href="/logout" data-role="button" data-icon="back" class="ui-btn-right">BUTTON EXAMPLE</a>

</div><!-- /header -->
<div data-role="content">

	INSERT PAGE CONTENT
	
</div><!-- /content -->
<div data-role='footer' data-id="foo1" data-position="fixed">

	INSERT FOOTER CONTENT
	<div data-role="navbar"><ul>
		<li><a href="#" data-role="button" data-icon="delete">BUTTON1</a></li>
		<li><a href="#" data-role="button" data-icon="check">BUTTON2</a></li>
	</ul></div>

</div>
</div><!-- /page -->
</body>
</html>