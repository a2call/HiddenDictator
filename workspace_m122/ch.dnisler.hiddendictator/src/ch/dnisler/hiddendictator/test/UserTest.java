package ch.dnisler.hiddendictator.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ch.dnisler.hiddendictator.Constants;
import ch.dnisler.hiddendictator.Server;
import ch.dnisler.hiddendictator.User;

public class UserTest {
	User testUser = null;

	@Before
	public void setUp() {
		this.testUser = Server.addUser("Test 1");
		this.testUser.setFaction(Constants.FACTION_DICTATOR);
	}

	@Test
	public void testGetFactionSecure() {
		User u1 = Server.addUser("Liberal");
		u1.setFaction(Constants.FACTION_LIBERAL);
		Assert.assertEquals(Constants.FACTION_HIDDEN, testUser.getFactionSecure("Liberal"));
		User u2 = Server.addUser("Supporter");
		u2.setFaction(Constants.FACTION_SUPPORTER);
		Assert.assertEquals(Constants.FACTION_DICTATOR, testUser.getFactionSecure("Supporter"));
		User u3 = Server.addUser("Dictator");
		u3.setFaction(Constants.FACTION_DICTATOR);
		Assert.assertEquals(Constants.FACTION_HIDDEN, testUser.getFactionSecure("Dictator"));
		Assert.assertEquals(Constants.FACTION_DICTATOR, testUser.getFactionSecure(this.testUser.getName()));
	}

}
