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
<%@ page import="dashboard.model.*" %>
<%@ page import="dashboard.registry.*" %>
<%@ page import="java.util.*" %>
<script type="text/javascript" src="http://maps.googleapis.com/maps/api/js?libraries=places&sensor=true"></script>
</head>
<body>
<div data-role="page">
<script>
$(document).bind("pageinit",function(){
	$("#saveButton").hide();
	$("input[name='number']").change(function(){
		var given = $("input[name='number']").val();
		if(given<1){
			$("#msg2").text("Geef een geldig nummer op");
			$("input[name='number']").val('');
		}else{
			$("#msg2").text("");
		}
	});
	$("input[name='zip']").change(function(){
		var given = $("input[name='zip']").val();
		if(given<1000){
			$("#msg3").text("Geef een geldige postcode op");
			$("input[name='zip']").val('');
		}else{
			$("#msg3").text("");
		}
	});
	$("#searchButton").click(function(){
		var name = $("input[name='name']").val();
		var street = $("input[name='street']").val();
		var number = $("input[name='number']").val();
		var zip = $("input[name='zip']").val();
		var city = $("input[name='city']").val();
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
			$("#msg").text("");$("#msg2").text("");$("#msg3").text("");$("#msg4").text("");$("#msg5").text("");
			obj = number + " " + street + ", " + zip + " " + city;
			req = {address: obj};
			geocoder = new google.maps.Geocoder();
			geocoder.geocode(req,handleGoogleResp);
			buttonFinishedState();
		}
	});
	function handleGoogleResp(res,stat){
		if(stat=="OK"){
				$("#msg4").text("Dit adres werd gevonden:");
				$("#msg5").text(res[0].formatted_address);
				$("#adres").text(res[0].formatted_address);
				$("input[name='namesend']").val($("input[name='name']").val());
				$("input[name='longitude']").val(res[0].geometry.location.lng());
				$("input[name='latitude']").val(res[0].geometry.location.lat());
				$("#saveButton").show();
			}
			else{
				$("#msg4").text("Er werd geen locatie gevonden.");
			}
	};
	function buttonFinishedState(){
		$("#searchButton .ui-btn-text").html("Opnieuw zoeken");
	};
	$("#saveButton").click(function(){
			$("#LocationForm").submit();
	});
});

</script>
<div data-role="header" data-id='header' data-position="fixed">
	<%
		String std = (request.getParameter("std")==null) ? "" : request.getParameter("std");
		Student student = StudentRegistry.getUserByUserName(std);
	%>
	<h1>Locatie</h1>
	<a href="/logout" data-role="button" data-icon="back" class="ui-btn-right">Afmelden</a>
	<a href="/jsp/menu.jsp" data-role="button" data-icon="grid" class="ui-btn-left">Menu</a>
	<div data-role="navbar">
		<ul>
			<li><a href="/jsp/location/add_location.jsp?std=<%=std%>">Locatie toevoegen</a></li>
			<li><a href="/jsp/location/browse_locations.jsp?std=<%=std%>">Bestaande locaties</a></li>
		</ul>
	</div><!-- /navbar -->
</div><!-- /header -->
<div data-role="content">
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
 	<%
		String msg4 = (request.getParameter("msg4")==null) ? "" : request.getParameter("msg4");
	%>
	<p id='msg4'><%=msg4%></p>
	<%
		String msg5 = (request.getParameter("msg5")==null) ? "" : request.getParameter("msg5");
	%>
	<p id='msg5'><%=msg5%></p>
 	<a href="" 	data-role="button" data-icon="check" data-theme="b" id="searchButton" data-inline="true">Zoeken</a></li>
	<a href="/jsp/menu.jsp" data-role="button" data-icon="delete" data-inline="true">Annuleren</a>
	<p>
<form id="LocationForm" method="post" action='/add_location'>
	<a href="" 	data-role="button" data-icon="check" data-theme="b" id="saveButton" data-inline="true">Opslaan</a></li>
	<input type="text" name="longitude" id="longitude" value="" data-inline="true" style="visibility:hidden"/>
 	<input type="text" name="latitude" id="latitude" value="" data-inline="true" style="visibility:hidden"/>
 	<input type="text" name="namesend" id="namesend" value="" data-inline="true" style="visibility:hidden"/>
 	<input type="text" name="adres" id="adres" value="" data-inline="true" style="visibility:hidden"/>

</form>
</div><!-- /content -->
</div><!-- /page -->
</body>
</html>