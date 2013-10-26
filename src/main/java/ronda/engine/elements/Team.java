package ronda.engine.elements;

public class Team {
	private String identifier;
	private byte score;
	private Player player1;
	private Player player2;

	public Team(String identifier) {
		this.identifier = identifier;
	}

	public Team(String identifier, Player player1, Player player2) {
		super();
		this.identifier = identifier;
		this.player1 = player1;
		this.player2 = player2;
	}

	public Player getPlayer(int i) {
		assert (i == 1 || i == 2);
		return i == 1 ? player1 : player2;
	}
}
