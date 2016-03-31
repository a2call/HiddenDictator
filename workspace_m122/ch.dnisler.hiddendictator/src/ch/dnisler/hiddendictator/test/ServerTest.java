package ch.dnisler.hiddendictator.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ch.dnisler.hiddendictator.Server;
import ch.dnisler.hiddendictator.User;

public class ServerTest {
	User testUser = null;

	@Before
	public void setUp() {
		this.testUser = Server.addUser("Test 1");
	}

	@Test
	public void testAddUser() {
		Assert.assertEquals("Test 2", Server.addUser("Test 2").getName());
	}

	@Test
	public void testAddUserAlreadyExisting(){
		Assert.assertEquals(testUser, Server.addUser("Test 1"));
	}


}
