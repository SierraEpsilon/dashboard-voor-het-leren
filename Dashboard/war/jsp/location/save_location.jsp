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
<div data-role="page" id="save_location_jsp">
<script>
$("div#save_location_jsp").bind("pageshow",function(){
	$("#opslaan").click(function(){
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
	<h3>Locatie opslaan</h3>
	Deze locatie werd gevonden:
	<ul data-role="listview" style="margin: 5px">
	<%
		String naam = (request.getParameter("naam")==null) ? "" : request.getParameter("naam");
		String lon = (request.getParameter("lon")==null) ? "" : request.getParameter("lon");
		String lat = (request.getParameter("lat")==null) ? "" : request.getParameter("lat");
		String adres = (request.getParameter("adres")==null) ? "" : request.getParameter("adres");
		String straat = (request.getParameter("straat")==null) ? "" : request.getParameter("straat");
		String num = (request.getParameter("num")==null) ? "" : request.getParameter("num");
		String zip = (request.getParameter("zip")==null) ? "" : request.getParameter("zip");
		String city = (request.getParameter("city")==null) ? "" : request.getParameter("city");
		String backurl = "/jsp/location/add_location.jsp?naam=" + naam + "&&straat=" + straat + "&&num=" + num + "&&zip=" + zip + "&&city=" + city;
		if(adres.endsWith("Belgi")){
			adres = adres + "ë";}
		out.println("<li>" + naam + " : " + adres + "</li>");
	%>
	</ul>
	<a href="" 	data-role="button" data-icon="check" data-theme="b" id="opslaan" data-inline="true">Opslaan</a></li>
	<a href= "<%=backurl%>" data-role="button" id="back" data-inline="true">Terug</a>
	
	
	<form id="LocationForm" method="post" action='/add_location'>
		<input type="hidden" name="longitude" 	id="longitude" 	value="<%=lon%>" data-inline="true"/>
	 	<input type="hidden" name="latitude" 	id="latitude" 	value="<%=lat%>" data-inline="true"/>
	 	<input type="hidden" name="namesend"	id="namesend"	value="<%=naam%>" data-inline="true"/>
	 	<input type="hidden" name="adres" 		id="adres" 		value="<%=adres%>" data-inline="true"/>
	</form>
	
	
</div><!-- /content -->
</div><!-- /page -->
</body>
</html>