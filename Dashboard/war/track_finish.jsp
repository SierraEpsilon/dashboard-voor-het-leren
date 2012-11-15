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
	<form name="kind" method="post" action="/track">
		
	<fieldset data-role="controlgroup">

	     	<input type="radio" name="kind" id="radio-choice-1" value="Theorie" checked="checked" />
	     	<label for="radio-choice-1">Theorie</label>
	
	     	<input type="radio" name="kind" id="radio-choice-2" value="Oefeningen"  />
	     	<label for="radio-choice-2">Oefeningen</label>
	
	</fieldset>
	Hoeveelheid:
	<input type='text' name='amount'>
	<input type="submit" value='OPSLAAN'>
	<input type='hidden' name='submit' value='stop'>
	</form>

</div><!-- /content -->
</div><!-- /page -->
</body>
</html>