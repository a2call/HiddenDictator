package ch.dnisler.hiddendictator;

public class User {
	private String name;
	private String status;
	private String faction;
	private String lobby;
	private String role;
	private boolean isAdm = false;
	private boolean isReady = false;

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
	public String getRole(){
		return role;
	}
}
