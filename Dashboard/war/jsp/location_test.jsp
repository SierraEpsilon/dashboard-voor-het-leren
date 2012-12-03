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
	getLoc();	
});
function getLoc(){
	navigator.geolocation.getCurrentPosition(show_map);
}
function show_map(pos){
	obj = new google.maps.LatLng(pos.coords.latitude,pos.coords.longitude);
	req = {latLng: obj};
	alert(pos.coords.accuracy);
	geocoder = new google.maps.Geocoder();
	geocoder.geocode(req,handleResp);
}
function handleResp(res,stat){
	alert(res[0].formatted_address);
}
</script>
<div data-role="header" data-id='header' data-position="fixed">

	<h1>INSERT PAGE HEADER</h1>
	<a href="/logout" data-role="button" data-icon="back" class="ui-btn-right">Afmelden</a>

</div><!-- /header -->
<div data-role="content">

	INSERT PAGE CONTENT
	
	
	
	

</div><!-- /page -->
</body>
</html>