package ronda.engine.elements;

public class Team {
	String	identifier;
	byte	score;
	Player	player1;
	Player	player2;
	
	public Team(String identifier) {
		this.identifier = identifier;
	}
	

	public Player getPlayer(int i) {
		assert (i == 1 || i == 2);
		return i == 1 ? player1 : player2;
	}
}
