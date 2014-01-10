package ronda.engine.evolution.builder;

import ronda.engine.elements.Player;
import ronda.engine.elements.Team;
import ronda.engine.evolution.Match;

public class MatchBuilder {
	public static Match match2Vs2Builder(Player player11, Player player12, Player player21, Player player22) {
		Team team1 = new Team("team1", player11, player12);
		Team team2 = new Team("team2", player21, player22);
		Match match = new Match(team1, team2);
		return match;
	}

	public static Match match1Vs1Builder(Player player11, Player player21) {
		Team team1 = new Team("team1", player11);
		Team team2 = new Team("team2", player21);
		Match match = new Match(team1, team2);
		return match;
	}
}
