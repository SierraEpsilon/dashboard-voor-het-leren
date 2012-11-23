<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<!-- The HTML 4.01 Transitional DOCTYPE declaration-->
<!-- above set at the top of the file will set     -->
<!-- the browser's rendering engine into           -->
<!-- "Quirks Mode". Replacing this declaration     -->
<!-- with a "Standards Mode" doctype is supported, -->
<!-- but may lead to some differences in layout.   -->

<html>
<head>
<%@include file="inc/head.jsp"%>
<%@include file="inc/redirect.jsp"%>
<%@ page import="dashboard.model.*" %>
<%@ page import="java.util.*" %>
</head>
<body>
<div data-role="page">
<script>
$(document).bind("pageinit",function(){

	$("input[name='startdate']").change(function(){
		var start = $("input[name='startdate']").val();
		if($("input[name='enddate']").val() == ''){
		$("input[name='enddate']").val(start);}
		if($("input[name='enddate']").val() < start){
		$("input[name='enddate']").val(start);}
	});
	
	$("input[name='amount']").change(function(){
		var given = $("input[name='amount']").val();
		if(given<1){
			$("#msg3").text("Geef een geldig getal op");
			$("input[name='amount']").val('');
		}else{$("#msg3").text("");
		}
	});
	
	$("input[name='enddate']").change(function(){
		var start =  $("input[name='startdate']").val();
		var end = $("input[name='enddate']").val();
		if(end<start){
			$("#msg2").text("De eindtijd moet na de begintijd vallen!");
			$("input[name='enddate']").val(start);
		}else{$("#msg2").text("");
		}
	});
	
	$("#opslaan").click(function(){
		var startd = $("input[name='startdate']").val();
		var startt = $("input[name='starttime']").val();
		var endd = $("input[name='enddate']").val();
		var endt = $("input[name='endtime']").val();
		var course = $("input[name='course']").val();
		var amount = $("input[name='amount']").val();
		var cont = true;
		cont = (startd=="") ? false : cont;
		cont = (startt=="") ? false : cont;
		cont = (endd=="") ? false : cont;
		cont = (endt=="") ? false : cont;
		cont = (course=="") ? false : cont;
		cont = (amount=="") ? false : cont;
		if(!cont){
			$("#msg").text("Vul alle velden in!");
		}
		else if(startd == endd){
			if(!(startt<endt)){
				$("#msg2").text("De eindtijd moet na de begintijd vallen!");
				$("input[name='endtime']").val('');
			}
		}
		else if(endd < startd){
			$("#msg2").text("De eindtijd moet na de begintijd vallen!");
			$("input[name='endtime']").val('');
		}
		else if (amount < 1){
			$("#msg3").text("Geef een geldig getal op!");
			$("input[name='amount']").val('');
		}
		else {
			$("#msg").text("");$("#msg2").text("");$("#msg3").text("");
			$("#myFormm").submit();
		}
		
	});
});
</script>
<div data-role="header" data-id='header' data-position="fixed">

	<h1>Toevoegen van studiemoment</h1>
	<a href="/logout" data-role="button" data-icon="back" class="ui-btn-right">Afmelden</a>

</div><!-- /header -->
<div data-role="content">

<form id="myFormm" method="post" action='/manual'>
	
	<%
		String msg = (request.getParameter("msg")==null) ? "" : request.getParameter("msg");
	%>
	<p id='msg' style='color:red;'><%=msg%></p>
	<b>Starttijd</b>
	<p>
	         <label for="startdate">Datum:</label>
	         <input type="date" name="startdate" id="date" value="" />
	         <label for="starttime">Tijd:</label>
	         <input type="time" name="starttime" id="time" value="" />
	<p>
	<b>Eindtijd</b>
	<p>
	         <label for="enddate">Datum:</label>
	         <input type="date" name="enddate" id="date" value="" />
	         <label for="endtime">Tijd:</label>
	         <input type="time" name="endtime" id="time" value="" />
	         
 	<%
		String msg2 = (request.getParameter("msg2")==null) ? "" : request.getParameter("msg2");
	%>
	<p id='msg2' style='color:red;'><%=msg2%></p>
 
 	<b>Vak</b>
 	<%
 	out.println("<select name='course'>");
	
	Student student = (Student)session.getAttribute("student");
	ArrayList<CourseContract> ccs = student.getCourses();
	Iterator it = ccs.iterator();
	while(it.hasNext()){
		CourseContract courseC = (CourseContract)it.next();
		String name = courseC.getCourse().getName();
		out.println("<option value='" + name + "'>" + name + "</option>");
	}
	out.println("</select>");
	%>
 	<fieldset data-role="controlgroup">

	     	<input type="radio" name="kind" id="radio-choice-1" value="Theorie" checked="checked" />
	     	<label for="radio-choice-1">Theorie</label>
	
	     	<input type="radio" name="kind" id="radio-choice-2" value="Oefeningen"  />
	     	<label for="radio-choice-2">Oefeningen</label>
	
	</fieldset>
	<label for='amount'>Hoeveelheid:</label>
	<input type='number' name='amount'>
	<%
		String msg3 = (request.getParameter("msg3")==null) ? "" : request.getParameter("msg3");
	%>
	<p id='msg3' style='color:red;'><%=msg3%></p>

</form>
 
 
 
</div><!-- /content -->
<div data-role='footer' data-id="foo1" data-position="fixed">
	<div data-role="navbar"><ul>

		<li><a href="" data-role="button" data-icon="check" id="opslaan" >Opslaan</a></li>
		<li><a href="/menu.jsp" data-role="button" data-icon="delete">Annuleren</a></li>
	</ul></div>

</div>
</div><!-- /page -->
</body>
</html>