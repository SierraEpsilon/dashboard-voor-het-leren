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
<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?libraries=places&sensor=true"></script>
</head>
<body>
<div data-role="page">
<script>
$(document).bind("pageinit",function(){

	$("input[name='number']").change(function(){
		var given = $("input[name='number']").val();
		if(given<1){
			$("#msg2").text("Geef een geldig nummer op");
			$("input[name='number']").val('');
		}else{$("#msg2").text("");
		}
	});
	$("input[name='zip']").change(function(){
		var given = $("input[name='zip']").val();
		if(given<1000){
			$("#msg3").text("Geef een geldige postcode op");
			$("input[name='zip']").val('');
		}else{$("#msg3").text("");
		}
	});
	$("#opslaan").click(function(){
		var name = $("input[name='name']").val();
		var street = $("input[name='street']").val();
		var number = $("input[name='number']").val();
		var zip = $("input[name='zip']").val();
		var city = $("select[name='city']").val();
		var cont = true;
		cont = (name=="") ? false : cont;
		cont = (street=="") ? false : cont;
		cont = (number=="") ? false : cont;
		cont = (zip=="") ? false : cont;
		cont = (city=="") ? false : cont;
		if(!cont){
			$("#msg").text("Vul alle velden in!");
		}
		else if (number < 1){
			$("#msg2").text("Geef een geldig nummer op!");
			$("input[name='amount']").val('');
		}
		else if (zip < 1000){
			$("#msg3").text("Geef een geldige postcode op!");
			$("input[name='amount']").val('');
		}
		else {
			$("#msg").text("");$("#msg2").text("");$("#msg3").text("");
			obj = street + " " + number + " " + zip + " " + city;
			req = {address: obj};
			geocoder = new google.maps.Geocoder();
			geocoder.geocode(req,handleGoogleResp);
		}
	});
	
	function handleGoogleResp(res,stat){
		alert(stat);
		$("#msg").text(res[0].formatted_address);
	};
	
	
});
</script>
<div data-role="header" data-id='header' data-position="fixed">

	<h1>Locatie toevoegen</h1>
	<a href="/logout" data-role="button" data-icon="back" class="ui-btn-right">Afmelden</a>
	<a href="/jsp/menu.jsp" data-role="button" data-icon="grid" class="ui-btn-left">Menu</a>

</div><!-- /header -->
<div data-role="content">

<form id="LocationForm" method="post" action='/location'>

	<%
		String msg = (request.getParameter("msg")==null) ? "" : request.getParameter("msg");
	%>
	<p id='msg' style='color:red;'><%=msg%></p>
	<label for="name">Naam:</label>
 	<input type="text" name="name" id="name" value="" placeholder="Campus" />
 	<label for="street">Straat:</label>
 	<input type="text" name="street" id="street" value="" placeholder="Celestijnenlaan"/>
 	<label for="number">Nummer:</label>
 	<input type="number" name="number" id="number" value="" placeholder="200" />
 	<%
		String msg2 = (request.getParameter("msg2")==null) ? "" : request.getParameter("msg2");
	%>
	<p id='msg2' style='color:red;'><%=msg2%></p>
 	<label for="zip">Postcode:</label>
 	<input type="number" name="zip" id="zip" value="" placeholder="3001" />
 	<%
		String msg3 = (request.getParameter("msg3")==null) ? "" : request.getParameter("msg3");
	%>
	<p id='msg3' style='color:red;'><%=msg3%></p>
 	<label for="city">Gemeente:</label>
 	<input type="text" name="city" id="city" value="" placeholder="Heverlee" />
 	
 	<a href="" 	data-role="button" data-icon="check" data-theme="b" id="opslaan" data-inline="true">Opslaan</a></li>
	<a href="/jsp/menu.jsp" data-role="button" data-icon="delete" data-inline="true">Annuleren</a>
</form>
	
</div><!-- /content -->
</div><!-- /page -->
</body>
</html>