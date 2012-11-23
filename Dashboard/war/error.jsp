
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
</head>
<body>
<div data-role="dialog" id="dialog_login_warning" class="app-dialog">
<div data-role="header" data-id='header' data-position="fixed">
</div><!-- /header -->
<div data-role="content">
		<%
			String msg = (request.getParameter("msg")==null) ? "" : request.getParameter("msg");
		%>
		<p>Er is een fout opgetreden!</p>
		<p style='color:red;'><%= msg%></p>
		<from><input type="button" value="ok" data-inline="true" data-icon="back" onClick="history.go(-1);return true;"></form>
</div><!-- /content -->
</div><!-- /dialog -->
</body>
</html>