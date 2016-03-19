<%@page import="java.util.logging.Logger"%>
<%@page import="ch.dnisler.hiddendictator.Constants"%>
<%@page import="com.sun.xml.internal.bind.v2.runtime.reflect.opt.Const"%>
<%@page import="ch.dnisler.hiddendictator.Server"%>
<%@page import="ch.dnisler.hiddendictator.Lobby"%>
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>UTIL_ACTIONS</title>
</head>
<body>
	<%
		Lobby l = Server.getLobbyMap().get(session.getAttribute("lobby"));
		if (request.getParameter("nominate") != null) {
			String nomuser = request.getParameter("nomuser");
			Logger.getLogger("action.jsp").info(nomuser + " nominated!");
			l.startVote(nomuser);
			l.setInvote(true);
		} else if (request.getParameter("vote") != null) {
			switch (request.getParameter("vote")) {
			case "YES":
				l.getCurrVote().voteYes(session.getAttribute("user").toString());
				break;
			case "NO":
				l.getCurrVote().voteNo(session.getAttribute("user").toString());
				break;
			}
		} else if (request.getParameter("closevote") != null) {
			l.getCurrVote().close();
			if (l.getCurrVote().getYes() >= l.getCurrVote().getNo()) {
				l.getCurrVote().getNominee().setRole(Constants.ROLE_CHANCELLOR);
				l.setInvote(false);
				l.setGovactive(true);
			} else {
				l.setInvote(false);
				l.setGovactive(false);
			}
		}

		response.sendRedirect("../index.jsp");
	%>
</body>
</html>