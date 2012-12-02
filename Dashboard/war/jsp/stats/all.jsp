<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<head>
<%@include file="/WEB-INF/inc/head.jsp"%>
<%@include file="/WEB-INF/inc/redirect.jsp"%>
</head>
<body>
<div data-role="page" id="stats_all_jsp">
	<!--[if lt IE 9]><script language="javascript" type="text/javascript" src="/inc/jQPlot/excanvas.js"></script><![endif]-->
	<script language="javascript" type="text/javascript" src="/inc/jQPlot/jquery.jqplot.min.js"></script>
	<link rel="stylesheet" type="text/css" href="/inc/jQPlot/jquery.jqplot.css" />
	<script type="text/javascript" src="/inc/jQPlot/plugins/jqplot.pieRenderer.min.js"></script>
	<script type="text/javascript" src="/inc/jQPlot/plugins/jqplot.donutRenderer.min.js"></script>
	<script type="text/javascript" src="/inc/jQPlot/plugins/jqplot.barRenderer.min.js"></script>
	<script type="text/javascript" src="/inc/jQPlot/plugins/jqplot.categoryAxisRenderer.min.js"></script>
	<script type="text/javascript" src="/inc/jQPlot/plugins/jqplot.pointLabels.min.js"></script>
	<script src="inc/plot.js"></script>
	<script>
	$("div#stats_all_jsp").bind("pagebeforeshow",function(){
		$("div#stats_all_jsp").find("div[name='preLoadWrap']").show();
		$("div#stats_all_jsp").find("div[name='loadWrap']").hide();
	});
	$("div#stats_all_jsp").bind("pageshow",function(){
		getData("/stats","#stata_catButton  .ui-btn-text","#stata_graphButton  .ui-btn-text","stata_graphDiv","stats_all_jsp");
	});
	$(window).resize(function(){drawGraph();});
	</script>
<div data-role="header" data-id="header" data-position="fixed">
	<h1>Statistieken</h1>
	<a href="/logout" data-role="button" data-icon="back" class="ui-btn-right">Afmelden</a>
	<a href="/jsp/menu.jsp" data-role="button" data-icon="grid" class="ui-btn-left">Menu</a>
	<div data-role="navbar">
		<ul>
			<li><a href="all.jsp" class="ui-btn-active ui-state-persist">Algemeen</a></li>
			<li><a href="course_select.jsp?returl=stats_course.jsp">Per vak</a></li>
		</ul>
	</div><!-- /navbar -->

</div><!-- /header -->
<div data-role="content">
<div name='preLoadWrap'>Geweldige statistieken in de maak...</div>
<div data-role='fieldcontain' name='loadWrap'>
		<a  id='stata_catButton' onclick='changeCat()' data-role="button">Loading</a> 
		<a  id='stata_graphButton' onclick='changeGraph()' data-role="button">Loading</a>
		<div id='stata_graphDiv' style="width:100%;">
		<img src="http://www.prelovac.com/vladimir/wp-content/uploads/2008/03/example.jpg" />
		</div>
</div>
</div><!-- /content -->
</div><!-- /page -->
</body>
</html>