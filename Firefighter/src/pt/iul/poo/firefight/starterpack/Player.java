package pt.iul.poo.firefight.starterpack;

import java.util.Comparator;

public class Player {
	private String username;
	private int score = 0;

	public Player(String user, int score) {
		this.username = user;
		this.score = score;
	}

	public String getUsername() {
		return username;
	}

	public int getScore() {
		return score;
	}
}
