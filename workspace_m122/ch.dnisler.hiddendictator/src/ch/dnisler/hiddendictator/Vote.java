package ch.dnisler.hiddendictator;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
/**
 * This class represents the Vote object
 * @author Dominik
 *
 */
public class Vote {
	private User nominee;
	private int yes = 0;
	private int no = 0;
	private Map<User, String> votermap;

	/**
	 * Creates a new vote with the given candidate
	 * 
	 * @param nominee
	 *            the nominated User
	 */
	public Vote(String nominee) {
		this.nominee = Server.getUser(nominee);
		this.nominee.setRole(Constants.ROLE_CANDIDATE);
		this.votermap = new HashMap<>();

	}

	/**
	 * Get the nominee of the vote
	 * 
	 * @return the nominee
	 */
	public User getNominee() {
		return nominee;
	}

	/**
	 * Get the yes votes
	 * 
	 * @return the yes votes
	 */
	public int getYes() {
		return yes;
	}

	/**
	 * Votes yes for a nominee
	 * 
	 * @param user
	 *            the user who votes
	 */
	public void voteYes(String user) {
		this.votermap.put(Server.getUser(user), "yes");
		this.yes++;
	}

	/**
	 * Get the nof no votes
	 * 
	 * @return the nof no votes
	 */
	public int getNo() {
		return no;
	}

	/**
	 * Votes no for the nominee
	 * 
	 * @param user
	 *            the user who votes no
	 */
	public void voteNo(String user) {
		this.votermap.put(Server.getUser(user), "no");
		this.no++;
	}

	/**
	 * Gets if the vote is already closed
	 * 
	 * @return
	 */
	public boolean isClosed() {
		return closed;
	}

	/**
	 * Closes the vote
	 * @param closed
	 */
	public void setClosed(boolean closed) {
		this.closed = closed;
	}
	
	/**
	 * Checks if the given user already voted on this vote
	 * @param the user who votes
	 * @return true if the user already voted
	 */
	public boolean hasVoted(String user) {
		return this.votermap.containsKey(Server.getUser(user));
	}

	/**
	 * Closes the vote
	 */
	public void close() {
		for (Entry<User, String> entry : votermap.entrySet()) {
			entry.getKey().setLastVote(entry.getValue());
		}
	}

	private boolean closed = false;

}
