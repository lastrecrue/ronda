package ronda.engine.elements.player.builder;

import ronda.engine.elements.player.BasicIAPlayer;

public class BasicIAPlayerBuilder {
	static BasicIAPlayer player11 = new BasicIAPlayer("player11");
	static BasicIAPlayer player12 = new BasicIAPlayer("player12");
	static BasicIAPlayer player21 = new BasicIAPlayer("player21");
	static BasicIAPlayer player22 = new BasicIAPlayer("player22");

	public static BasicIAPlayer getPlayer11() {
		return player11;
	}

	public static BasicIAPlayer getPlayer12() {
		return player12;
	}

	public static BasicIAPlayer getPlayer21() {
		return player21;
	}

	public static BasicIAPlayer getPlayer22() {
		return player22;
	}

}
