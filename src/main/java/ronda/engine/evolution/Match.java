package ronda.engine.evolution;

import java.util.ArrayList;
import java.util.List;

import ronda.engine.elements.Player;
import ronda.engine.elements.Team;

public class Match {
	private Team team1;
	private Team team2;
	private Game currentGame = new Game(this);
	private List<Player> players = new ArrayList<Player>();
	private byte playerIndex;

	public Match(Team team1, Team team2) {
		this.team1 = team1;
		this.team2 = team2;
		initializePlayers(team1, team2);
	}

	private void initializePlayers(Team team1, Team team2) {
		players.add(team1.getPlayer(1));
		players.add(team2.getPlayer(1));
		if (twoPlayersVersusTwoPlayersGame()) {
			players.add(team1.getPlayer(2));
			players.add(team2.getPlayer(2));
		}
	}

	public boolean twoPlayersVersusTwoPlayersGame() {
		return team1.getPlayer(2) != null & team2.getPlayer(2) != null;
	}

	protected Player getDistributor() {
		Team team = currentGame.getDistributorTeamIndex() == 1 ? team1 : team2;
		return team.getPlayer(currentGame.getDistributorPlayerIndex() == 1 ? 1 : 2);
	}

	protected Player getNextPlayer() {
		playerIndex = (byte) (++playerIndex % 4);
		return players.get(playerIndex);
	}

	protected Game getCurrentGame() {
		return currentGame;
	}

	public void run() {
		while (!matchEnded()) {
			currentGame.run();
		}
	}
	
	public boolean matchEnded()
	{
		return false;
	}
/*
	public void startGame(Match match) {
		while (!currentGame.getHeap().isEmpty()) {
			currentGame.initializeHeap();
			currentGame.selectNextDistributor();
			Round.startRound(match);
		}

	}
*/
	public Team getWinnerTeam() {
		if (team1.getScore() > 20) {
			return team1;
		} else if (team2.getScore() > 20) {
			return team2;
		} else {
			return null;
		}
	}

	public List<Player> getPlayers() {
		return players;
	}

}
