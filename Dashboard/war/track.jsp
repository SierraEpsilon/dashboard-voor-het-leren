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
if(request.getParameter("mode")!=null){
	start = 1;
	startTime = (String) request.getSession().getAttribute("startTracking");
	String course = request.getParameter("course");
	out.println("<p>Course: " + course);
	out.println("<p><form action='/track' method='post'><input type='submit' value='STOP'>");
	out.println("<p><input type='hidden' name='amount' value='1'>");
	out.println("<p><input type='hidden' name='kind' value='Theory'>");
	out.println("</form>");
	out.println("<p id='timePast'>");	
}else{
	start = 0;
	startTime = "";
	out.println("<form action='/track' method='post'>");
	out.println("<p>Course: <select><option>Analyse</option><option>Mechanica</option></select>");
	out.println("<p><input type='submit' value='START'>");
	out.println("</form>");
}
%>
<script>
$(document).ready(function(){
	start = <%= start %>;
	<% if(start==1) {System.out.println("start="+"start"+";");}
	%>
	setTimePast();
	setInterval("setTimePast()",1000);
	
});

function setTimePast(){
	str = "";
	start = new Date("<%= startTime%>");
	now = new Date();
	diff = (now.getTime() - start.getTime());
	diff = Math.round(diff/1000);
	sec = (diff%60);
	if(diff<60){
		String name = (diff==1) ? "seconde" : "seconden";
		str = sec + " " + name;
	}else{
		diff = ((diff-sec)/60);
		min = (diff%60);
		if(diff<60){
			String name = (diff==1) ? "minuut" : "minuten";
			str = min + " " + name;
		}else{
			diff = ((diff-min)/60);
			str = hours + "uur" + min + "minuten";
		}
	}
	$('#timePast').text(str);
}
</script>
</body>
</html>