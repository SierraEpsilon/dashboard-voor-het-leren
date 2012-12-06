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
<link rel="stylesheet" href="http://jquerymobile.com/demos/1.0a4.1/experiments/ui-datepicker/jquery.ui.datepicker.mobile.css" /> 
<script src="http://jquerymobile.com/demos/1.0a4.1/experiments/ui-datepicker/jQuery.ui.datepicker.js"></script>
<script src="http://jquerymobile.com/demos/1.0a4.1/experiments/ui-datepicker/jquery.ui.datepicker.mobile.js"></script>
</head>
<body>
<div data-role="page" id="add_manual_jsp">
<script>
  //reset type=date inputs to text
$("div#add_manual_jsp").bind( "mobileinit", function(){
    
  });	
</script>	

<script>
$('#add_manual_jsp').bind("pageinit",function(){
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
		var courseinput = $("select[name='courseinput']").val();
		var amount = $("input[name='amount']").val();
		var cont = true;
		cont = (startd=="") ? false : cont;
		cont = (startt=="") ? false : cont;
		cont = (endd=="") ? false : cont;
		cont = (endt=="") ? false : cont;
		cont = (courseinput=="placeholder") ? false : cont;
		cont = (amount=="") ? false : cont;
		if(!cont){
			$("#msg").text("Vul alle velden in!");
		}
		else if(startd == endd){
			if(!(startt<endt)){
				$("#msg2").text("De eindtijd moet na de begintijd vallen!");
				$("input[name='endtime']").val('');
			}
			else {
			$("#msg").text("");$("#msg2").text("");$("#msg3").text("");
			$("#myFormm").submit();
			}
		}
		else if(endd < startd){
			$("#msg2").text("De eindtijd moet na de begintijd vallen!");
			$("input[name='enddate']").val('');
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
<div data-role="header" data-id='header' data-position='fixed'>
	<a href="/jsp/menu.jsp" data-role="button" data-icon="grid">Menu</a>
	<h1>Toevoegen van studiemoment</h1>
	<a href="/logout" data-role="button" data-icon="back" class="ui-btn-right">Afmelden</a>

</div><!-- /header -->

<div data-role="content">

<form id="myFormm" method="post" action='/add_manual'>
	
	<%
		String msg = (request.getParameter("msg")==null) ? "" : request.getParameter("msg");
	%>
	<p id='msg' style='color:red;'><%=msg%></p>
	<b>Starttijd</b>
	<p>
	         <label for="startdate">Datum:</label>
	         <input type="date" name="startdate" id="date" value="" placeholder="vb: 2012-04-20" />
	         <label for="starttime">Tijd:</label>
	         <input type="time" name="starttime" id="time" value="" placeholder="vb: 15:00"/>
	<p>
	<b>Eindtijd</b>
	<p>
	         <label for="enddate">Datum:</label>
	         <input type="date" name="enddate" id="date" value="" placeholder="vb: 2012-04-20" />
	         <label for="endtime">Tijd:</label>
	         <input type="time" name="endtime" id="time" value="" placeholder="vb: 16:00"/>
	         
 	<%
		String msg2 = (request.getParameter("msg2")==null) ? "" : request.getParameter("msg2");
	%>
	<p id='msg2' style='color:red;'><%=msg2%></p>
 
 	<b>Vak</b>
 	<%
	 	out.println("<select name='courseinput' data-native-menu='false' >");
	 	
	 		out.println("<option value='placeholder' data-placeholder='true'>Selecteer een vak: </option>");
	 	
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
	<input type='number' name='amount' placeholder='Getal'>
	<%
		String msg3 = (request.getParameter("msg3")==null) ? "" : request.getParameter("msg3");
	%>
	<p id='msg3' style='color:red;'><%=msg3%></p>


		<a href="" 	data-role="button" data-icon="check" data-theme="b" id="opslaan" data-inline="true">Opslaan</a></li>
		<a href="/jsp/menu.jsp" data-role="button" data-icon="delete" data-inline="true">Annuleren</a>
</form>
 
</div><!-- /content -->

</div><!-- /page -->
</body>
</html>