<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd"> 
<html>
<head>
<%@include file="inc/head.jsp"%>
<%@include file="inc/redirect.jsp"%>
<!--[if lt IE 9]><script language="javascript" type="text/javascript" src="inc/jQPlot/excanvas.js"></script><![endif]-->
<script language="javascript" type="text/javascript" src="inc/jQPlot/jquery.jqplot.min.js"></script>
<link rel="stylesheet" type="text/css" href="inc/jQPlot/jquery.jqplot.css" />
<script type="text/javascript" src="inc/jQPlot/plugins/jqplot.pieRenderer.min.js"></script>
<script type="text/javascript" src="inc/jQPlot/plugins/jqplot.donutRenderer.min.js"></script>
<script src="inc/plot.js"></script>
</head>
<body>
<div data-role="page" id="stats_all_jsp">
<script>
$("div#stats_all_jsp").bind("pageshow",function(){
getData("test.json","#stata_catButton  .ui-btn-text","#stata_graphButton  .ui-btn-text","stata_graphDiv");
});
$(window).resize(function(){drawGraph();});
</script>
<div data-role="header" data-id="header" data-position="fixed">
<h1>Statistieken</h1>
<div data-role="navbar">
	<ul>
		<li><a href="stats_all.jsp" class="ui-btn-active ui-state-persist">Algemeen</a></li>
		<li><a href="stats_course.jsp">Per vak</a></li>
	</ul>
</div><!-- /navbar -->

</div><!-- /header -->
<div data-role="content">
		<a  id='stata_catButton' onclick='changeCat()' data-role="button">Loading</a> 
		<a  id='stata_graphButton' onclick='changeGraph()' data-role="button">Loading</a>
		<div id='stata_graphDiv'></div>
</div><!-- /content -->
<div data-role='footer' data-id="foo1" data-position="fixed">
<a href="/menu.jsp" data-role="button" data-icon="grid">Menu</a>
</div>

</div><!-- /page -->
</body>
</html>