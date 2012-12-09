<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<head>
<%@include file="/inc/head.jsp"%>
<%@include file="/inc/redirect.jsp"%>
</head>
<body>
<div data-role="page" id="stats_course_jsp">
	<!--[if lt IE 9]><script language="javascript" type="text/javascript" src="/inc/jQPlot/excanvas.js"></script><![endif]-->
	<script language="javascript" type="text/javascript" src="/inc/jQPlot/jquery.jqplot.min.js"></script>
	<link rel="stylesheet" type="text/css" href="/inc/jQPlot/jquery.jqplot.css" />
	<script type="text/javascript" src="/inc/jQPlot/plugins/jqplot.pieRenderer.min.js"></script>
	<script type="text/javascript" src="/inc/jQPlot/plugins/jqplot.donutRenderer.min.js"></script>
	<script type="text/javascript" src="/inc/jQPlot/plugins/jqplot.barRenderer.min.js"></script>
	<script type="text/javascript" src="/inc/jQPlot/plugins/jqplot.categoryAxisRenderer.min.js"></script>
	<script type="text/javascript" src="/inc/jQPlot/plugins/jqplot.pointLabels.min.js"></script>
	<script src="/inc/plot.js"></script>
	<script>
	$("div#stats_course_jsp").bind("pagebeforeshow",function(){
		$("div#stats_course_jsp").find("div[name='preLoadWrap']").show();
		$("div#stats_course_jsp").find("div[name='loadWrap']").hide();
	});
	<% String course = (request.getParameter("course")==null) ? "" : request.getParameter("course"); %>
	$("div#stats_course_jsp").bind("pageshow",function(){
		
		getData("/stats?course=<%=course%>","#statc_catButton  .ui-btn-text","#statc_graphButton  .ui-btn-text","statc_graphDiv","stats_course_jsp","statc_desc");
	});
	$(window).resize(function(){drawGraph();});
	</script>
<div data-role="header" data-id="header" data-position="fixed">
	<h1>Statistieken van <%=course%></h1>
	<a href="/logout" data-role="button" data-icon="back" class="ui-btn-right">Afmelden</a>
	<a href="/jsp/menu.jsp" data-role="button" data-icon="grid" class="ui-btn-left">Menu</a>
	<div data-role="navbar">
		<ul>
			<li><a href="/jsp/stats/all.jsp">Algemeen</a></li>
			<li><a href="/jsp/util/course_select.jsp?returl=/jsp/stats/course.jsp" class="ui-btn-active ui-state-persist">Per vak</a></li>
		</ul>
	</div><!-- /navbar -->

</div><!-- /header -->
<div data-role="content">
<div name='preLoadWrap'>Geweldige statistieken in de maak...</div>
<div data-role='fieldcontain' name='loadWrap'>
		<a  id='statc_catButton' onclick='changeCat()' data-role="button">Loading</a> 
		<a  id='statc_graphButton' onclick='changeGraph()' data-role="button">Loading</a>
		<div id='statc_graphDiv'></div>
		<div data-role="collapsible" data-content-theme="c">
	   		<h3>Uitleg</h3>
	   		<p id='statc_desc'></p>
		</div>
</div>
</div><!-- /content -->
<div data-role='footer' data-id="foo1" data-position="fixed">
<a href="/jsp/menu.jsp" data-role="button" data-icon="grid">Menu</a>
</div>

</div><!-- /page -->
</body>
</html>