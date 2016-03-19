package ch.dnisler.hiddendictator;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.logging.Logger;

/**
 * This Class represents a lobby object
 * 
 * @author Dominik
 *
 */
public class Lobby {
	private static final Logger LOG = Logger.getLogger(Lobby.class.getName());
	private List<User> userList = new ArrayList<>();
	private boolean invote = false;
	private boolean ingame = false;
	private boolean govactive = false;
	private Vote currVote = null;
	private String name;

	/**
	 * Creates a new instance of the Lobby object
	 * 
	 * @param lobbyName
	 *            name of the lobby to be created
	 */
	public Lobby(String lobbyName) {
		this.name = lobbyName;
	}

	/**
	 * Adds an user to the Lobby
	 * 
	 * @param username
	 * @param isAdm
	 * @return
	 */
	public boolean addUser(String username, boolean isAdm) {
		Server.getUser(username).setAdm(isAdm);
		User user = Server.getUser(username);
		if (userList.contains(user)) {
			return false;
		}
		LOG.info("User " + username + " entered a Lobby");
		user.setRole(Constants.ROLE_MEMBER);
		return userList.add(user);
	}

	public void startGame() {
		setUserRoles();
		setIngame(true);
		Random r = new Random();
		userList.get(r.nextInt(userList.size() - 1)).setRole(Constants.ROLE_PRESIDENT);

	}

	public void nextTurn(User president) {
		if (userList.indexOf(president) > userList.size()) {
			userList.get(0).setRole(Constants.ROLE_PRESIDENT);
		} else {
			userList.get(userList.indexOf(president) + 1).setRole(Constants.ROLE_PRESIDENT);

		}
	}

	private void setUserRoles() {
		Random r = new Random();
		List<User> unset = new ArrayList<>(userList);
		User dictator = unset.get(r.nextInt(unset.size() - 1));
		dictator.setFaction(Constants.FACTION_DICTATOR);
		LOG.info("Dictator: " + dictator.getName());
		unset.remove(dictator);
		int nofSupporters = 0;
		if (unset.size() >= 4 && unset.size() <= 5) {
			nofSupporters = 1;
		} else if (unset.size() >= 6 && unset.size() <= 7) {
			nofSupporters = 2;
		} else if (unset.size() > 7) {
			nofSupporters = 3;
		}
		for (int i = 0; i < nofSupporters; i++) {
			User supporter = unset.get(r.nextInt(unset.size() - 1));
			supporter.setFaction(Constants.FACTION_SUPPORTER);
			LOG.info("Supporter: " + supporter.getName());
			unset.remove(supporter);
		}
		for (User liberal : unset) {
			LOG.info("Liberal: " + liberal.getName());
			liberal.setFaction(Constants.FACTION_LIBERAL);
		}
	}

	public void nextTurn() {

	}

	public List<User> getUserList() {
		return userList;
	}

	public boolean isIngame() {
		return ingame;
	}

	public void setIngame(boolean ingame) {
		this.ingame = ingame;
	}

	public boolean isInvote() {
		return invote;
	}

	public void setInvote(boolean invote) {
		this.invote = invote;
	}

	public Vote getCurrVote() {
		return currVote;
	}

	public Vote startVote(String nominee) {
		this.currVote = new Vote(nominee);
		return currVote;
	}

	public boolean isGovactive() {
		return govactive;
	}

	public void setGovactive(boolean govactive) {
		this.govactive = govactive;
	}
}
