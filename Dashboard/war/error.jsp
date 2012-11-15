<html>
	<head>
	</head>
	<body>
		<%
			String msg = (request.getParameter("msg")==null) ? "" : request.getParameter("msg");
		%>
		<p><%= msg%></p>
		<p>Er is een fout opgetreden!</p>
	</body>
</html>