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
	private boolean ingame = false;
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
		return userList.add(Server.getUser(username));
	}

	public void startGame() {
		setUserRoles();
		setIngame(true);
		Random r = new Random();
		userList.get(r.nextInt(userList.size() - 1)).setRole(Constants.ROLE_PRESIDENT);
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
		for (int i = 0; i < unset.size(); i++) {
			User liberal = unset.get(i);
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
}
