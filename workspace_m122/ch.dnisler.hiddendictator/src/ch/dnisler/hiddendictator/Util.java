package ch.dnisler.hiddendictator;

import javax.servlet.http.HttpSession;

public class Util {
	public static String getPage(HttpSession session) {
		if (session.getAttribute("status") != null) {
			if (session.getAttribute("status").equals("registered")) {
				return "lobbylist";
			} else if (session.getAttribute("status").equals("inlobby")) {
				return "lobby";
			} else {
				return "home";
			}
		} else {
			return "home";
		}
	}

	public static boolean validChancellor(User u) {
		boolean valid = false;
		if (u.getRole() != null) {
			valid = u.getRole().equals(Constants.ROLE_FRM_CHANCELLOR)
					&& u.getRole().equals(Constants.ROLE_FRM_CHANCELLOR);
		} else {
			valid = false;
		}
		return !valid;
	}
}
