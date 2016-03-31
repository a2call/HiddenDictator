"WebContent/includes/lobby.jsp"<%@page import="ch.dnisler.hiddendictator.Vote"%>
<%@page import="java.util.logging.Logger"%>
<%@page import="ch.dnisler.hiddendictator.Util"%>
<%@page import="ch.dnisler.hiddendictator.Constants"%>
<%@page import="ch.dnisler.hiddendictator.User"%>
<%@page import="ch.dnisler.hiddendictator.Server"%>
<%@page import="ch.dnisler.hiddendictator.Lobby"%>
<h1>
	<%
		out.print("Welcome to " + (String) session.getAttribute("lobby") + ", "
				+ session.getAttribute("user").toString());
	%>
</h1>
<%
	Lobby lobby = Server.getLobbyMap().get((String) session.getAttribute("lobby"));
	if (lobby.isIngame()) {
		User myuser = Server.getUser(session.getAttribute("user").toString());
		out.print("<img src='" + request.getContextPath() + "/img/" + myuser.getFaction()
				+ ".jpg' height='150px'>");
		out.print("<p><b>You are a " + myuser.getFaction() + "</b></p>");
	}
%>
<div class="table-responsive">
	<table class="table">
		<tr>
			<th>Name</th>
			<th>Ready</th>
			<th>Faction</th>
			<th>Role</th>
			<th>Last Vote</th>
			<th>Action</th>
		</tr>
		<%
			for (User u : lobby.getUserList()) {
				out.print("<tr><td>");
		%><form method="get" action="includes/action.jsp">
			<%
				if (u.isAdm()) {

						out.print("*ADMIN* ");
					}
					out.print(u.getName() + "</td>");
					out.print("<td>");
					if (u.isReady()) {
						out.print("Ready");
					} else {
						out.print("Not Ready");
					}
					out.print("</td>");
					if (lobby.isIngame()) {
						out.print("<td>");
						out.print(u.getFactionSecure(session.getAttribute("user").toString()));
						out.print("</td>");
						out.print("<td>");
						out.print(u.getRole());
						out.print("</td>");
						out.print("<td>");
						out.print(u.getLastVote());
						out.print("</td>");
						
						//Actions
						User myuser = Server.getUser(session.getAttribute("user").toString());
						if (!lobby.isInvote()) {
							if (myuser.getRole().equals(Constants.ROLE_PRESIDENT) && !myuser.equals(u)
									&& Util.validChancellor(u) && !lobby.isGovactive()) {
								out.print("<td><input type='hidden', name='nomuser', value='" + u.getName() + "'>");
								out.print("<input type='submit' class='form-control' name='nominate' value='Nominate "
										+ u.getName() + "'></td>");
							}
						} else {
							if (u.getRole().equals(Constants.ROLE_CANDIDATE)
									&& !lobby.getCurrVote().hasVoted(myuser.getName())) {
								out.print(
										"<td><input type='submit' class='form-control' name='vote' value='YES'><input type='submit' class='form-control' name='vote' value='NO'></td>");
							}
						}
					}
					out.print("</tr>");
				}
			%>

		</form>

	</table>
</div>
<form method="get" action="includes/action.jsp">
	<%
		if (lobby.isInvote()) {
			Vote currvote = lobby.getCurrVote();
			int nofvotes = currvote.getYes() + currvote.getNo();
			out.print("<h2>CURRENT NUMBER OF VOTES: " + nofvotes);
			if (nofvotes == lobby.getUserList().size()) {
				out.print("<input type='submit' class='form-control' name='closevote' value='Close Vote'></input>");

			}
		}
	%>
</form>
<form method="get" action="includes/util.jsp">
	<input type="submit" class="form-control" name="setready"
		value="READY / NOT READY"></input>
</form>
<form method="get" action="includes/util.jsp">
	<%
		if (Server.getUser(session.getAttribute("user").toString()).isAdm() && !lobby.isIngame()
				&& lobby.getUserList().size() > 4) {
			out.print("<input type='submit' class='form-control' name='startgame' value='Start Game'></input>");
		} else if (Server.getUser(session.getAttribute("user").toString()).isAdm() && lobby.isIngame()) {
			out.print("<input type='submit' class='form-control' name='nextturn' value='Next Turn'></input>");
		}
	%>
</form>