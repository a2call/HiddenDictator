<%@page import="ch.dnisler.hiddendictator.User"%>
<%@page import="ch.dnisler.hiddendictator.Server"%>
<%@page import="ch.dnisler.hiddendictator.Lobby"%>
<h1>
	<%
		out.print("Welcome to " + (String) session.getAttribute("lobby"));
	%>
</h1>
<ul>
	<%
		response.setIntHeader("Refresh", 5);
		Lobby lobby = Server.getLobbyMap().get((String) session.getAttribute("lobby"));
		for (User u : lobby.getUserList()) {
			out.print("<li>");
			if (u.isAdm()) {
				out.print("*ADMIN*");
			}
			out.print(u.getName());
			if (u.isReady()) {
				out.print(" [READY]");
			} else {
				out.print(" [NOT READY]");
			}
			out.print("</li>");
		}
	%>
	<form method="get" action="includes/util.jsp">
		<input type="submit" class="form-control" name="setready"
			value="READY / NOT READY"></input>
	</form>
	<form method="get" action="includes/util.jsp">
		<%
			if (Server.getUser(session.getAttribute("user").toString()).isAdm()) {
				out.print("<input type='submit' class='form-control' name='startgame' value='Start Game'");
			}
		%>
	</form>
</ul>