package ch.dnisler.hiddendictator.test;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ch.dnisler.hiddendictator.Lobby;
import ch.dnisler.hiddendictator.Server;

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
		Assert.assertFalse(lobby.addUser("Test 6", true));
	}

	@After
	public void tearDown() {
		Server.remUser("Test 1");
		Server.remUser("Test 2");
		Server.remUser("Test 3");
		Server.remUser("Test 4");
		Server.remUser("Test 5");
	}
}
