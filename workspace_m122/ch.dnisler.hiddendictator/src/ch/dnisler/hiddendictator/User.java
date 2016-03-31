package ch.dnisler.hiddendictator;

/**
 * This class represents the User object
 * @author Dominik
 *
 */
public class User {
	private String name;
	private String status;
	private String faction;
	private String lobby;
	private String role;
	private String lastVote = "No vote yet";
	private boolean isAdm = false;
	private boolean isReady = false;

	/**
	 * Creates a new user with the given name
	 * 
	 * @param name
	 *            the name of the User
	 */
	public User(String name) {
		this.setName(name);
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public boolean isReady() {
		return isReady;
	}

	public void setReady(boolean isReady) {
		this.isReady = isReady;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getLobby() {
		return lobby;
	}

	public void setLobby(String lobby) {
		this.lobby = lobby;
	}

	/**
	 * Gets the secure faction name (so liberals can't see others faction) but
	 * returns the true faction for supporters
	 * 
	 * @param requester
	 *            the requesting user
	 * @return the Faction (out of Constants)
	 */
	public String getFactionSecure(String requester) {
		String reqFaction = Server.getUser(requester).getFaction();
		if (requester.equals(this.getName())) {
			return faction;
		} else {
			if (reqFaction.equals(Constants.FACTION_LIBERAL) || reqFaction.equals(Constants.FACTION_DICTATOR)) {
				return Constants.FACTION_HIDDEN;
			} else {
				return faction;
			}
		}
	}

	public String getFaction() {
		return faction;
	}

	public void setFaction(String faction) {
		this.faction = faction;
	}

	public boolean isAdm() {
		return isAdm;
	}

	public void setAdm(boolean isAdm) {
		this.isAdm = isAdm;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getRole() {
		return role;
	}

	public String getLastVote() {
		return lastVote;
	}

	public void setLastVote(String lastVote) {
		this.lastVote = lastVote;
	}
}
