package ch.dnisler.hiddendictator.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.junit.runners.Suite.SuiteClasses;

@RunWith(Suite.class)
@SuiteClasses({LobbyTest.class, ServerTest.class, UserTest.class, UtilTest.class, VoteTest.class})
public class AllTests {

}
