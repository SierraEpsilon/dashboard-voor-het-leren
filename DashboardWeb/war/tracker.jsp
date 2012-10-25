<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="dashboard.uttility.*" %>
<%@ page import="dashboard.DataObjects.*" %>
<%@ page import="java.util.List" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreServiceFactory" %>
<%@ page import="com.google.appengine.api.datastore.DatastoreService" %>
<%@ page import="com.google.appengine.api.datastore.Query" %>
<%@ page import="com.google.appengine.api.datastore.Entity" %>
<%@ page import="com.google.appengine.api.datastore.FetchOptions" %>
<%@ page import="com.google.appengine.api.datastore.Key" %>
<%@ page import="com.google.appengine.api.datastore.KeyFactory" %>

<html>
    <head>
     	<link type="text/css" rel="stylesheet" href="/stylesheets/main.css" />
  	</head>
  	<body>
  	<%
    	String trackListName = request.getParameter("trackListName");
    	if (trackListName == null) {
        	trackListName = "default";
    	}
   		pageContext.setAttribute("trackListName", trackListName);
	%>
	<p>Hello, welcome to our tracker. </p>
  	<%
   		DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
  	 	Key trackKey = KeyFactory.createKey("trackingList",trackListName);
    	Query query = new Query("tracking", trackKey).addSort("start", Query.SortDirection.DESCENDING);
    	List<Entity> trackings = datastore.prepare(query).asList(FetchOptions.Builder.withLimit(5));
    	if (trackings.isEmpty()) {
    %>
    	<p> no trackings yet </p>
    <% } else { 
    		for(Entity tracking:trackings){ 
    %>
    		<p>  You started studying <%=tracking.getProperty("vak")%>
    		at <%=tracking.getProperty("start").toString()%> </p>
    <% 		}
    	} 
    %>
  		<form action="/tracker" method="post">
  			<select name="vak">
  				<% Data data = new Data();
  				for(Vak vak : data.getVakken()) { 
  					pageContext.setAttribute("vak", vak.getNaam()); %>
  					<option value="<% vak.getNaam();%>"><%=vak.getNaam()%></option>
  				<% } %>
  			</select>
     	 	<div><input type="submit" value="start" /></div>
     	 	<input type="hidden" name="trackListName" value="<%=trackListName%>"/>
   		</form>
  	</body>
</html>