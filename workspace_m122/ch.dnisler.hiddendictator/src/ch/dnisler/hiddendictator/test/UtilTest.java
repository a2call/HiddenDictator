package ch.dnisler.hiddendictator.test;

import javax.servlet.http.HttpSession;

import org.junit.Assert;
import org.junit.Test;

import ch.dnisler.hiddendictator.Constants;
import ch.dnisler.hiddendictator.User;
import ch.dnisler.hiddendictator.Util;

public class UtilTest {

	@Test
	public void testValidChancellor() {
		User u = new User("User");
		u.setRole(Constants.ROLE_MEMBER);
		Assert.assertTrue(Util.validChancellor(u));
	}

	@Test
	public void testValidChancellorInvalid() {
		User u = new User("User");
		u.setRole(Constants.ROLE_FRM_CHANCELLOR);
		Assert.assertFalse(Util.validChancellor(u));
		u.setRole(Constants.ROLE_PRESIDENT);
		Assert.assertFalse(Util.validChancellor(u));
		u.setRole(Constants.ROLE_FRM_PRESIDENT);
		Assert.assertFalse(Util.validChancellor(u));
	}


}
