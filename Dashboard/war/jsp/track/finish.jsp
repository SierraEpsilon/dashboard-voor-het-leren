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
<style type='text/css'>
.containing-element .ui-slider-switch { width: 20em }
</style>
</head>
<body>

<div data-role="page">
<script>
$(document).bind("pageinit",function(){
	$("#myForm").submit(function(){
		var amount = $("input[name='amount']").val();
		var patt=/^[0-9][0-9]*$/;
		if (patt.test(amount)&&amount!=0) {
			return true;
		}else{
			$("#msg").text("Geef een geldig getal op");
			return false;
		}
	});
	$("input[name='radio_name']:checked").change(function(){
		if($(this).val()=="Oefeningen")
			$("label[for='amount']").text("Aantal gemaakt:");
		else
			$("label[for='amount']").text("Bladzijden gestudeerd:");
	});
});
</script>
<div data-role="header" data-id='header' data-position="fixed">
		<h1>Learnalyzer</h1>
		<a href="/jsp/track/stop.jsp" data-role="button" data-icon="back" class="ui-btn-left">Terug</a>
</div><!-- /header -->
<div data-role="content">
	<form id="myForm" method="post" action="/track">
		
	<fieldset data-role="controlgroup">

	     	<input type="radio" name="kind" id="radio-choice-1" value="Theorie" checked="checked" />
	     	<label for="radio-choice-1">Theorie</label>
	
	     	<input type="radio" name="kind" id="radio-choice-2" value="Oefeningen"  />
	     	<label for="radio-choice-2">Oefeningen</label>
	
	</fieldset>
	<label for='amount'>Hoeveelheid:</label>
	<input type='text' name='amount'>
	<%
		String msg = (request.getParameter("msg")==null) ? "" : request.getParameter("msg");
	%>
	<p id='msg' style='color:red;'><%=msg%></p>
	<input type="submit" value='Opslaan'>
	<input type='hidden' name='submit' value='Stop'>
	</form>
</div><!-- /content -->
</div><!-- /page -->
</body>
</html>