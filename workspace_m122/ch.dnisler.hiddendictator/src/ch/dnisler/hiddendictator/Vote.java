package ch.dnisler.hiddendictator;

import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;

public class Vote {
	private User nominee;
	private int yes = 0;
	private int no = 0;
	private Map<User, String> votermap;

	public Vote(String nominee) {
		this.nominee = Server.getUser(nominee);
		this.nominee.setRole(Constants.ROLE_CANDIDATE);
		this.votermap = new HashMap<>();

	}

	public User getNominee() {
		return nominee;
	}

	public void setNominee(User nominee) {
		this.nominee = nominee;
	}

	public int getYes() {
		return yes;
	}

	public void voteYes(String user) {
		this.votermap.put(Server.getUser(user), "yes");
		this.yes++;
	}

	public int getNo() {
		return no;
	}

	public void voteNo(String user) {
		this.votermap.put(Server.getUser(user), "no");
		this.no++;
	}

	public boolean isClosed() {
		return closed;
	}

	public void setClosed(boolean closed) {
		this.closed = closed;
	}

	public boolean hasVoted(String user) {
		return this.votermap.containsKey(Server.getUser(user));
	}

	public void close() {
		for (Entry<User, String> entry : votermap.entrySet()) {
			entry.getKey().setLastVote(entry.getValue());
		}
	}

	private boolean closed = false;

}
