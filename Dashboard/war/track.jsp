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
<script>
$(document).ready(function(){
	start = <%= start %>;
	<% if(start==1) {System.out.println("start="+"start"+";");}
	%>
	setInterval(function(){
		str = "";
		start = new Date("<%= startTime%>");
		now = new Date();
		diff = (now.getTime() - start.getTime());
		diff = Math.round(diff/1000);
		sec = (diff%60);
		if(diff<60){
			str = sec + " seconden";
		}else{
			diff = ((diff-sec)/60);
			min = (diff%60);
			if(min<60){
				str = min + " minuten";
			}else{
				diff = ((diff-min)/60);
				hours = (diff%24);
				if(hours<24){
					str = hours + "h" + min + "m";
				}else{
					days = ((diff-hours)/24);
					str = days + " dagen en " + hours + " uren";
				}
			}
		}
		$('#timePast').text(str);
	},1000);
	
});

function setTimePast(){

}
</script>
</body>
</html>