<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- The HTML 4.01 Transitional DOCTYPE declaration-->
<!-- above set at the top of the file will set     -->
<!-- the browser's rendering engine into           -->
<!-- "Quirks Mode". Replacing this declaration     -->
<!-- with a "Standards Mode" doctype is supported, -->
<!-- but may lead to some differences in layout.   -->

<html>
<head>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
</head>
<body>
<%
String page = "";
if(request.getParameter("start")!=null){
	int start = 1;
	String start = request.getParameter("start");
	String course = request.getParameter("course");
	page += ("<p>Course: " + course);
	page += ("<p><form action='' method='post'><input type='submit' value='STOP'>");
	page += ("<p id='timePast'>");	
}else{
	int start = 0;
	page += ("<form action='' method='post'>");
	page += ("<p>Course: <select><option>Analyse</option></select>");
	page += ("<p><input type='submit' value='START'>");
	page += ("</form>");
	
}
System.out.println(page);
%>
<script>
$(document).ready(function(){
	start = <%= start %>;
	<% if(start==1) System.out.println("start="+"start+";");
	else
	
});

setTimePast(){

}
</script>
<button><%= buttonText %></button>
</body>
</html>