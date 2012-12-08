<%

if(request.getSession().getAttribute("student")==null){
	response.sendRedirect("/jsp/login/login.jsp?msg=Beveiligde pagina");
}

%>