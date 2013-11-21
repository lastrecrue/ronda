package ronda.engine.evolution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.apache.log4j.Logger;

import ronda.engine.elements.Card;
import ronda.engine.elements.CardSymbol;
import ronda.engine.elements.CardValue;
import ronda.engine.elements.Player;

public class Game {

	protected Logger logger = Logger.getLogger(Game.class);
	private final Match currentMatch;
	private final Round currentRound;
	private final List<Card> heap = new ArrayList<Card>();
	private final List<Card> board = new ArrayList<Card>();

	private boolean isDistributorTeam1 = false;
	private boolean isDistributorPlayer1 = false;

	public Game(Match currentMatch) {
		this.currentMatch = currentMatch;
		currentRound = new Round(currentMatch);
	}

	protected void initializeHeap() {
		assert (heap.size() == 0 && board.size() == 0);

		for (CardSymbol symbol : CardSymbol.values()) {
			CardValue currentCardValue = new CardValue((byte) 1);
			do {
				heap.add(new Card(symbol, currentCardValue));
				currentCardValue = currentCardValue.getNext();
			} while (currentCardValue != null);
		}
		Collections.shuffle(heap);
	}

	protected void selectNextDistributor() {
		// Rule:
		// distributorPlayer(g) != distributorPlayer(g+1) &&
		// distributorPlayer(g).team != distributorPlayer(g+1).team
		isDistributorTeam1 = !isDistributorTeam1;
		if (isDistributorTeam1 && currentMatch.twoPlayersVersusTwoPlayersGame()) {
			isDistributorPlayer1 = !isDistributorPlayer1;
		} else { // case of 1vs1 game
			if (!currentMatch.twoPlayersVersusTwoPlayersGame()) {
				isDistributorPlayer1 = true;
			}
		}
	}

	protected byte getDistributorTeamIndex() {
		return (byte) (isDistributorTeam1 ? 1 : 2);
	}

	public List<Card> getBoard() {
		return board;
	}

	protected byte getDistributorPlayerIndex() {
		return (byte) (isDistributorPlayer1 ? 1 : 2);
	}

	public List<Card> getHeap() {
		return heap;
	}

	public void run() {
		initializeHeap();
		selectNextDistributor();

		while (!currentMatch.matchEnded()) {
			currentRound.run();
			logger.debug("end of round.");
			if (heap.isEmpty()) {
				resolveWonCardsPerTeam();
				logger.debug("End of game. score (team1#team2) : "
						+ currentMatch.getOverallScore());
				initializeHeap();
				selectNextDistributor();
			}
		}
	}

	private void resolveWonCardsPerTeam() {

		if (currentMatch.twoPlayersVersusTwoPlayersGame()) {
			// 2vs2
			byte team1WonCards = (byte) (currentMatch.getTeam1().getPlayer(1)
					.getWonCardsPerHeap().size() + currentMatch.getTeam1()
					.getPlayer(2).getWonCardsPerHeap().size());
			logger.debug("team1 won cards : " + team1WonCards);
			byte team2WonCards = (byte) (currentMatch.getTeam2().getPlayer(1)
					.getWonCardsPerHeap().size() + currentMatch.getTeam2()
					.getPlayer(2).getWonCardsPerHeap().size());
			logger.debug("team2 won cards : " + team2WonCards);
			if (team1WonCards >= team2WonCards) {
				currentMatch.incrementScore(currentMatch.getTeam1(),
						(byte) (team1WonCards - 20));
			} else {
				currentMatch.incrementScore(currentMatch.getTeam2(),
						(byte) (team2WonCards - 20));
			}
		} else {
			// 1vs1
			byte team1WonCards = (byte) currentMatch.getTeam1().getPlayer(1)
					.getWonCardsPerHeap().size();
			byte team2WonCards = (byte) currentMatch.getTeam2().getPlayer(1)
					.getWonCardsPerHeap().size();
			if (team1WonCards >= team2WonCards) {
				currentMatch.incrementScore(currentMatch.getTeam1(),
						(byte) (team1WonCards - 20));
			} else {
				currentMatch.incrementScore(currentMatch.getTeam2(),
						(byte) (team2WonCards - 20));
			}
		}

		// wipe won cards heap
		for (Player player : currentMatch.getPlayers()) {
			player.getWonCardsPerHeap().clear();
		}
	}
}
