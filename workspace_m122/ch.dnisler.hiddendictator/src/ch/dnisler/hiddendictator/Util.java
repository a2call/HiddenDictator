package ch.dnisler.hiddendictator;

import javax.servlet.http.HttpSession;

/**
 * This class represents the Util package, providing functionality
 * 
 * @author Dominik
 *
 */
public final class Util {
	/**
	 * Gets which page is being shown to the user
	 * 
	 * @param session
	 *            the HTTP Session of the client
	 * @return the page name
	 */
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

	/**
	 * Checks if the given user is a valid choice for chancellor
	 * 
	 * @param u
	 *            the {@link User} to be checked
	 * @return true if the user is a valid choice
	 */
	public static boolean validChancellor(User u) {
		boolean valid = false;
		if (u.getRole() != null) {
			valid = u.getRole().equals(Constants.ROLE_FRM_CHANCELLOR)
					|| u.getRole().equals(Constants.ROLE_FRM_PRESIDENT) || u.getRole().equals(Constants.ROLE_PRESIDENT);
		} else {
			valid = false;
		}
		return !valid;
	}

	private Util() {
	}
}
