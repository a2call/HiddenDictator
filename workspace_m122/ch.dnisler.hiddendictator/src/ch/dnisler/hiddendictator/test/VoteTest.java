package ch.dnisler.hiddendictator.test;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import ch.dnisler.hiddendictator.Server;
import ch.dnisler.hiddendictator.User;
import ch.dnisler.hiddendictator.Vote;

public class VoteTest {

	private Vote voteToTest;

	@Before
	public void setUp() {
		Server.addUser("User");
		voteToTest = new Vote("User");

	}

	@Test
	public void testVoteYes() {
		User u = Server.addUser("Test");
		voteToTest.voteYes("Test");
		voteToTest.close();
		Assert.assertEquals("yes", u.getLastVote());
	}

	@Test
	public void testVoteNo() {
		User u = Server.addUser("Test");
		voteToTest.voteNo("Test");
		voteToTest.close();
		Assert.assertEquals("no", u.getLastVote());
	}
}
