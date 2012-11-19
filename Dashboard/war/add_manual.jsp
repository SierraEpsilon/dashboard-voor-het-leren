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

	$("label[name='startdate']").change(function(){
	 $("label[name='enddate']").attr("value")=$("label[name='startdate']").attr("value");
	});
	
	
});
</script>
<div data-role="header" data-id='header' data-position="fixed">

	<h1>Toevoegen van studiemoment</h1>
	<a href="/logout" data-role="button" data-icon="back" class="ui-btn-right">Logout</a>

</div><!-- /header -->
<div data-role="content">

<form id="myForm" method="post" action="/manual">


	<b>Starttijd</b>
	<p>
	         <label for="startdate">Datum:</label>
	         <input type="date" name="startdate" id="date" value="" />
	         <label for="starttime">Tijd:</label>
	         <input type="time" name="starttime" id="time" value="" />
	<p>
	<b>Eindtijd</b>
	<p>
	         <label for="enddate">Datum:</label>
	         <input type="date" name="enddate" id="date" value="" />
	         <label for="endtime">Tijd:</label>
	         <input type="time" name="endtime" id="time" value="" />
	         
 	<p id='msg' style='color:red;'></p>
 
 
</div><!-- /content -->
<div data-role='footer' data-id="foo1" data-position="fixed">

	
	<div data-role="navbar"><ul>
		<li><a href="#" data-role="button" data-icon="check">Opslaan</a></li>
		<li><a href="#" data-role="button" data-icon="delete">Annuleren</a></li>
	</ul></div>

</div>
</div><!-- /page -->
</body>
</html>