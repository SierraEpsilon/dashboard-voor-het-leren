<html>
	<head>
	</head>
	<body>
		<%
			String msg = (request.getParameter("msg")==null) ? "" : request.getParameter("msg");
		%>
		<p>Er is een fout opgetreden!</p>
		<p style='color:red;'><%= msg%></p>
		
	</body>
</html>