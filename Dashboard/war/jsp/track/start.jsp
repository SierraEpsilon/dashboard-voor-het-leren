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
<%@ page import="java.util.*" %>
</head>
<body>
<div data-role="page" id="track_start_jsp">
<script>
var canceled = 0;
var location;
var locRetr = 0;
$("div#track_start_jsp").bind("pageshow",function(){
	getLoc();	
});
function getLoc(){
	navigator.geolocation.getCurrentPosition(handleNavResp);
}
function handleNavResp(pos){
	if(!canceled){
		location.latitude = pos.coords.latitude;
		location.longitude = pos.coords.longitude;
		location.accuracy = pos.coords.accuracy;
		obj = new google.maps.LatLng(pos.coords.latitude,pos.coords.longitude);
		req = {latLng: obj};
		geocoder = new google.maps.Geocoder();
		geocoder.geocode(req,handleGoogleResp);
	}
}
function handleGoogleResp(res,stat){
	if(!canceled){
		location.adrString = res[0].formatted_address;
		locRetr = 1;
		buttonFinishedState(res[0].formatted_address);
	}
}
function buttonFinishedState(adres){
	$("#locBut .ui-btn-text").html(adres);
	$("#locBut").click(buttonCancelState);
}
function buttonCancelState(){
	canceled = 1;
	locRetr = 0;
	$("#locBut .ui-btn-text").html("Voeg je locatie toe");
	$("#locBut").buttonMarkup({icon: "plus"});
	$("#locBut").unbind("click");
	$("#locBut").click(buttonLoadState);
}
function buttonLoadState(){
	canceled = 0;
	$("#locBut .ui-btn-text").html("Je locatie wordt bepaald...");
	$("#locBut").buttonMarkup({icon: "delete"});
	$("#locBut").unbind("click");
	$("#locBut").click(buttonCancelState);
	getLoc();
}
function submitForm(){
	if(!canceled&&locRetr){
		$("form[name='startForm']").append("<input type='hidden' name='accuracy' value='"+location.accuracy+"'>");
		$("form[name='startForm']").append("<input type='hidden' name='longitude' value='"+location.longitude+"'>");
		$("form[name='startForm']").append("<input type='hidden' name='latitude' value='"+location.latitude+"'>");
		$("form[name='startForm']").append("<input type='hidden' name='adres' value='"+location.adrString+"'>");
	}
	return false;
}
</script>
<div data-role="header" data-id='header' data-position="fixed">
	<h1>Learnalyzer</h1>
	<a href="/logout" data-role="button" data-icon="back" class="ui-btn-right">Afmelden</a>
	<a href="/jsp/menu.jsp" data-role="button" data-icon="grid" class="ui-btn-left">Menu</a>
</div><!-- /header -->
<div data-role="content">
<%
	String course = request.getParameter("course");
	if(course==null){
		out.println("<a href='/jsp/util/course_select.jsp?returl=/jsp/track/start.jsp' data-role='button'>Kies een vak</a>");
		out.println("<p><a class='ui-disabled' data-role='button'>Start</a>");
	}else{
		out.println("<form name='startForm' action='/track' method='post'>");
		out.println("<a href='/jsp/util/course_select.jsp?returl=/jsp/track/start.jsp' data-role='button'>"+course+"</a>");
		out.println("<p><input type='hidden' name='courseinput' value='"+course+"'>");
		out.println("<a id='locBut' data-role='button' data-icon='delete'>Je locatie wordt bepaald...</a>");
		out.println("<p><input type='submit' onclick='submitForm()' name='submit' value='Start'>");
		out.println("</form>");
	}
%>

</div><!-- /content -->
</div><!-- /page -->
</body>
</html>
