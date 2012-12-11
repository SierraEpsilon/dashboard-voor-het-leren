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
<div data-role="page" id="add_location_jsp">
<script>
$("div#add_location_jsp").bind("pageshow",function(){
	$("#saveButton").hide();
	$("#number").change(function(){
		var given = $("#number").val();
		if(given<1){
			$("#msg2").text("Geef een geldig nummer op");
			$("#number").val('');
		}else{
			$("#msg2").text("");
		}
	});
	$("#zip").change(function(){
		var given = $("#zip").val();
		if(given<1000){
			$("#msg3").text("Geef een geldige postcode op");
			$("#zip").val('');
		}else{
			$("#msg3").text("");
		}
	});
	$("#searchButton").click(function(){
		var name = $("#name").val();
		var street = $("#street").val();
		var number = $("#number").val();
		var zip = $("#zip").val();
		var city = $("#city").val();
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
			$("#number").val('');
		}
		else if (zip < 1000){
			$("#msg3").text("Geef een geldige postcode op!");
			$("#zip").val('');
		}
		else {
			$("#msg").text("");$("#msg2").text("");$("#msg3").text("");$("#msg4").text("");$("#msg5").text("");
			obj = number + " " + street + ", " + zip + " " + city;
			req = {address: obj};
			geocoder = new google.maps.Geocoder();
			geocoder.geocode(req,handleGoogleResp);
		}
	});
	function handleGoogleResp(res,stat){
		if(stat=="OK"){
				$("#adres").val(res[0].formatted_address);
				$("#namesend").val($("#name").val());
				$("#longitude").val(res[0].geometry.location.lng());
				$("#latitude").val(res[0].geometry.location.lat());
				var url = "/jsp/location/save_location.jsp?naam=" + $("#name").val() + "&&adres=" + res[0].formatted_address + "&&lon=" + res[0].geometry.location.lng() + "&&lat=" + res[0].geometry.location.lat() + "&&straat=" + $("#street").val() + "&&num=" + $("#number").val() + "&&zip=" + $("#zip").val() + "&&city=" + $("#city").val();
				alert(url);
				window.location.href = url;
			}
			else{
				$("#msg4").text("Er werd geen locatie gevonden.");
				$("#searchButton .ui-btn-text").html("Opnieuw zoeken");
			}
	};
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
		String naam = (request.getParameter("naam")==null) ? "" : request.getParameter("naam");
		String straat = (request.getParameter("straat")==null) ? "" : request.getParameter("straat");
		String num = (request.getParameter("num")==null) ? "" : request.getParameter("num");
		String zip = (request.getParameter("zip")==null) ? "" : request.getParameter("zip");
		String city = (request.getParameter("city")==null) ? "" : request.getParameter("city");
	%>
	<p id='msg' style='color:red;'><%=msg%></p>
	<label for="name">Naam:</label>
 	<input type="text" name="name" id="name" value="<%=naam%>" placeholder="Campus" />
 	<label for="street">Straat:</label>
 	<input type="text" name="street" id="street" value="<%=straat%>" placeholder="Celestijnenlaan"/>
 	<label for="number">Nummer:</label>
 	<input type="number" name="number" id="number" value="<%=num%>" placeholder="200" />
 	<%
		String msg2 = (request.getParameter("msg2")==null) ? "" : request.getParameter("msg2");
	%>
	<p id='msg2' style='color:red;'><%=msg2%></p>
 	<label for="zip">Postcode:</label>
 	<input type="number" name="zip" id="zip" value="<%=zip%>" placeholder="3001" />
 	<%
		String msg3 = (request.getParameter("msg3")==null) ? "" : request.getParameter("msg3");
	%>
	<p id='msg3' style='color:red;'><%=msg3%></p>
 	<label for="city">Gemeente:</label>
 	<input type="text" name="city" id="city" value="<%=city%>" placeholder="Heverlee" />
 	<%
		String msg4 = (request.getParameter("msg4")==null) ? "" : request.getParameter("msg4");
	%>
	<p id='msg4'><%=msg4%></p>
 	<a href="" 	data-role="button" data-icon="check" data-theme="b" id="searchButton" data-inline="true">Zoeken</a></li>
	<a href="/jsp/menu.jsp" data-role="button" data-icon="delete" data-inline="true">Annuleren</a>
	<p>

</div><!-- /content -->
</div><!-- /page -->
</body>
</html>