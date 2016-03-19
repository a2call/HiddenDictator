<%@page import="ch.dnisler.hiddendictator.Lobby"%>
<%@page import="ch.dnisler.hiddendictator.Server"%>
<%@page import="ch.dnisler.hiddendictator.Util"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<%
	response.setIntHeader("Refresh", 5);
%>

<!-- Latest compiled and minified CSS -->
<link rel="stylesheet" href="bootstrap-3.3.6/dist/css/bootstrap.min.css">

<!-- Optional theme -->
<link rel="stylesheet"
	href="bootstrap-3.3.6/dist/css/bootstrap-theme.min.css">

<!-- Latest compiled and minified JavaScript -->
<script src="bootstrap-3.3.6/dist/js/bootstrap.min.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hidden Dictator</title>
</head>
<body>
	<%
		session.setMaxInactiveInterval(-1);
		String p = Util.getPage(session);
	%>
	<jsp:include page='<%="includes/" + p + ".jsp"%>' />
	<%
		
	%>
</body>
</html>