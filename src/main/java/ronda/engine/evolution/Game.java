package ronda.engine.evolution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import ronda.engine.elements.Card;
import ronda.engine.elements.CardSymbol;
import ronda.engine.elements.CardValue;

public class Game {
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
		}
	}

}
