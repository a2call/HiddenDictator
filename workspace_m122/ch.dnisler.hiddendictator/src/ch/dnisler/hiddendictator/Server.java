package ch.dnisler.hiddendictator;

import java.util.HashMap;
import java.util.Map;
import java.util.logging.Logger;

public class Server {
	private static Map<String, User> userMap = new HashMap<>();
	private static Map<String, Lobby> lobbyMap = new HashMap<>();
	private static final Logger LOG = Logger.getLogger(Server.class.getName());

	public static User addUser(String username) {
		if (userMap.containsKey(username)) {
			LOG.warning("User " + username + " already exists");
			return userMap.get(username);
		} else {
			LOG.info("User " + username + " joined the server");
			return userMap.put(username, new User(username));
		}
	}

	public static Lobby addLobby(String lobbyname) {
		LOG.info("Lobby " + lobbyname + " was created");
		return lobbyMap.put(lobbyname, new Lobby(lobbyname));
	}

	public static Map<String, Lobby> getLobbyMap() {
		return lobbyMap;
	}

	public static User getUser(String username) {
		return userMap.get(username);
	}

	public static User remUser(String username) {
		return userMap.remove(username);
	}
}
