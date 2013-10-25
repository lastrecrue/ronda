package ronda.engine.evolution;

import ronda.engine.elements.Player;
import ronda.engine.elements.Team;

public class Match {
	Team	team1;
	Team	team2;
	Game	currentGame;
	
	public Match(Team team1, Team team2)
	{
		this.team1 = team1;
		this.team2 = team2;
		
		currentGame = new Game();
	}
	
	Player getDistributor()
	{
		Team team = currentGame.getDistributorTeamIndex() == 1 ? team1 : team2;
		return team.getPlayer(currentGame.getDistributorPlayerIndex() == 1 ? 1 : 2);
	}
}
