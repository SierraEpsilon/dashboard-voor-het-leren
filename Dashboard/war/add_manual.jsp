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

	$("input[name='startdate']").change(function(){
		var start = $("input[name='startdate']").val();
		if($("input[name='enddate']").val() == ''){
		$("input[name='enddate']").val(start);}
		if($("input[name='enddate']").val() < start){
		$("input[name='enddate']").val(start);}
	});
	
	$("input[name='amount']").change(function(){
		var given = $("input[name='amount']").val();
		if(given<1){
			$("#msg3").text("Geef een geldig getal op");
			$("input[name='amount']").val('');
		}else{$("#msg3").text("");
		}
	});
	
	$("input[name='enddate']").change(function(){
		var start =  $("input[name='startdate']").val();
		var end = $("input[name='enddate']").val();
		if(end<start){
			$("#msg2").text("De einddatum valt na de begindatum!");
			$("input[name='enddate']").val(start);
		}else{$("#msg2").text("");
		}
	});
	
	$("#opslaan").click(function(){
		$("#myFormm").submit(function(){
		
			var startd = $("input[name='startdate']").val();
			var startt = $("input[name='starttime']").val();
			var endd = $("input[name='enddate']").val();
			var endt = $("input[name='endtime']").val();
			var amount = $("input[name='amount']").val();
		alert("check");
			var cont = true;
			cont = (startd=="") ? false : cont;
			cont = (startt=="") ? false : cont;
			cont = (endd=="") ? false : cont;
			cont = (endt=="") ? false : cont;
			cont = (amount=="") ? false : cont;
			if(!cont){
				$("#msg").text("Vul alle velden in!");
				return false;}
			else if(startd == endd){
					if(!(startt<endt)){
						$("#msg2").text("De einddatum valt na de begindatum!");
						$("input[name='endtime']").val('');
						return false;}
					}
			else if(endd < startd){
					$("#msg2").text("De einddatum valt na de begindatum!");
					$("input[name='endtime']").val('');
					return false;
					}
			else if (amount < 1){
					$("#msg3").text("Geef een geldig getal op!");
					$("input[name='amount']").val('');
					return false;
					}
			else {
				$("#msg").text("");$("#msg2").text("");$("#msg3").text("");
				return true;}
		});
	});
});
</script>
<div data-role="header" data-id='header' data-position="fixed">

	<h1>Toevoegen van studiemoment</h1>
	<a href="/logout" data-role="button" data-icon="back" class="ui-btn-right">Logout</a>

</div><!-- /header -->
<div data-role="content">

<form id="myFormm" method="post" action='/manual'>
	
	<p id='msg' style='color:red;'></p>
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
	         
 	<p id='msg2' style='color:red;'></p>
 
 	<b>Vak</b>
 	
 	<fieldset data-role="controlgroup">

	     	<input type="radio" name="kind" id="radio-choice-1" value="Theorie" checked="checked" />
	     	<label for="radio-choice-1">Theorie</label>
	
	     	<input type="radio" name="kind" id="radio-choice-2" value="Oefeningen"  />
	     	<label for="radio-choice-2">Oefeningen</label>
	
	</fieldset>
	<label for='amount'>Hoeveelheid:</label>
	<input type='number' name='amount'>
	<p id='msg3' style='color:red;'></p>

</form>
 
 
 
</div><!-- /content -->
<div data-role='footer' data-id="foo1" data-position="fixed">
	<div data-role="navbar"><ul>

		<li><a href="#" data-role="button" data-icon="check" id="opslaan" >Opslaan</a></li>
		<li><a href="/menu.jsp" data-role="button" data-icon="delete">Annuleren</a></li>
	</ul></div>

</div>
</div><!-- /page -->
</body>
</html>