package ch.dnisler.hiddendictator.test;

import java.util.List;
import java.util.NoSuchElementException;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ch.dnisler.hiddendictator.Constants;
import ch.dnisler.hiddendictator.Lobby;
import ch.dnisler.hiddendictator.Server;
import ch.dnisler.hiddendictator.User;

public class LobbyTest {
	private Lobby lobby;

	@Before
	public void setUp() {
		Server.addUser("Test 1");
		Server.addUser("Test 2");
		Server.addUser("Test 3");
		Server.addUser("Test 4");
		Server.addUser("Test 5");
		this.lobby = new Lobby("TestLobby");

	}

	@Test
	public void testAddUser() {
		Assert.assertTrue(lobby.addUser("Test 1", true));
		Assert.assertFalse(lobby.addUser("Test 1", true)); // False because user
															// already added
	}

	@Test(expected = NoSuchElementException.class)
	public void testAddUserNotExisting() {
		lobby.addUser("Test 6", true);

	}

	@Test
	public void testStartGameFive() {
		setUpLobby();
		lobby.startGame();
		List<User> testUserList = lobby.getUserList();
		Assert.assertEquals(1, getNofDictator(testUserList));
		Assert.assertEquals(1, getNofSupporters(testUserList));
		Assert.assertEquals(3, getNofLiberals(testUserList));
	}

	@Test
	public void testStartGameSeven() {
		setUpLobby();
		Server.addUser("Test 6");
		Server.addUser("Test 7");
		lobby.addUser("Test 6", false);
		lobby.addUser("Test 7", false);
		lobby.startGame();
		List<User> testUserList = lobby.getUserList();
		Assert.assertEquals(1, getNofDictator(testUserList));
		Assert.assertEquals(2, getNofSupporters(testUserList));
		Assert.assertEquals(4, getNofLiberals(testUserList));
		Server.remUser("Test 6");
		Server.remUser("Test 7");
	}

	@Test
	public void testStartGameNine() {
		setUpLobby();
		Server.addUser("Test 6");
		Server.addUser("Test 7");
		Server.addUser("Test 8");
		Server.addUser("Test 9");
		lobby.addUser("Test 6", false);
		lobby.addUser("Test 7", false);
		lobby.addUser("Test 8", false);
		lobby.addUser("Test 9", false);
		lobby.startGame();
		List<User> testUserList = lobby.getUserList();
		Assert.assertEquals(1, getNofDictator(testUserList));
		Assert.assertEquals(3, getNofSupporters(testUserList));
		Assert.assertEquals(5, getNofLiberals(testUserList));
		Server.remUser("Test 6");
		Server.remUser("Test 7");
		Server.remUser("Test 8");
		Server.remUser("Test 9");

	}
	
	@Test
	public void testNextTurn() {
		setUpLobby();
		Server.getUser("Test 1").setRole(Constants.ROLE_PRESIDENT);
		Assert.assertEquals(Server.getUser("Test 2"), lobby.nextTurn(Server.getUser("Test 1")));
	}

	@Test
	public void testNextTurnLastElementOfList() {
		setUpLobby();
		Server.getUser("Test 5").setRole(Constants.ROLE_PRESIDENT);
		Assert.assertEquals(Server.getUser("Test 1"), lobby.nextTurn(Server.getUser("Test 5")));
	}

	@After
	public void tearDown() {
		Server.remUser("Test 1");
		Server.remUser("Test 2");
		Server.remUser("Test 3");
		Server.remUser("Test 4");
		Server.remUser("Test 5");
	}

	private void setUpLobby() {
		lobby.addUser("Test 1", true);
		lobby.addUser("Test 2", false);
		lobby.addUser("Test 3", false);
		lobby.addUser("Test 4", false);
		lobby.addUser("Test 5", false);
	}

	private int getNofDictator(List<User> userList) {
		int dictatorCnt = 0;
		for (User u : userList) {
			if (u.getFaction().equals(Constants.FACTION_DICTATOR)) {
				dictatorCnt++;
			}
		}
		return dictatorCnt;
	}

	private int getNofSupporters(List<User> userList) {
		int supporterCnt = 0;
		for (User u : userList) {
			if (u.getFaction().equals(Constants.FACTION_SUPPORTER)) {
				supporterCnt++;
			}
		}
		return supporterCnt;
	}

	private int getNofLiberals(List<User> userList) {
		int liberalCnt = 0;
		for (User u : userList) {
			if (u.getFaction().equals(Constants.FACTION_LIBERAL)) {
				liberalCnt++;
			}
		}
		return liberalCnt;
	}
}
