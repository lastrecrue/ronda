package ronda.engine.evolution;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Stack;

import ronda.engine.elements.Card;
import ronda.engine.elements.CardSymbol;
import ronda.engine.elements.CardValue;
import ronda.engine.evolution.excpetion.WinnerException;

public class Game {
	private Stack<Card> heap = new Stack<Card>();
	private List<Card> board = new ArrayList<Card>();

	private boolean isDistributorTeam1 = false;
	private boolean isDistributorPlayer1 = false;

	private Round currentRound;

	public Game() {
		initializeHeap();

		selectNextDistributor();

		currentRound = new Round();
	}

	protected void initializeHeap() {
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
		// distributorPlayer(g) != distributorPlayer(g+1) && distributorPlayer(g).team != distributorPlayer(g+1).team
		isDistributorTeam1 = !isDistributorTeam1;
		if (isDistributorTeam1) {
			isDistributorPlayer1 = !isDistributorPlayer1;
		}
	}

	protected byte getDistributorTeamIndex() {
		return (byte) (isDistributorTeam1 ? 1 : 2);
	}

	protected byte getDistributorPlayerIndex() {
		return (byte) (isDistributorPlayer1 ? 1 : 2);
	}

	public Stack<Card> getHeap() {
		return heap;
	}

	public void startGame(Match match) throws WinnerException {
		while (!heap.isEmpty()) {
			initializeHeap();
			selectNextDistributor();
			currentRound.startRound(match);
		}

	}

}
