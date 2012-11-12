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
String startTime;
int start;
if(request.getParameter("start")!=null){
	start = 1;
	startTime = request.getParameter("start");
	String course = request.getParameter("course");
	out.println("<p>Course: " + course);
	out.println("<p><form action='' method='post'><input type='submit' value='STOP'>");
	out.println("<p id='timePast'>");	
}else{
	start = 0;
	startTime = "";
	out.println("<form action='' method='post'>");
	out.println("<p>Course: <select><option>Analyse</option></select>");
	out.println("<p><input type='submit' value='START'>");
	out.println("</form>");
	
}
%>
</body>
</html>