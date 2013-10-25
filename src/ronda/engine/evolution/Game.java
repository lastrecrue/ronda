package ronda.engine.evolution;

import java.util.ArrayList;
import java.util.List;

import ronda.engine.elements.Card;
import ronda.engine.elements.CardSymbol;
import ronda.engine.elements.CardValue;

public class Game {
	List<Card>	heap = new ArrayList<Card>();
	List<Card>	board = new ArrayList<Card>();
	
	boolean		isDistributorTeam1 = true;
	boolean		isDistributorPlayer1 = true;
	
	Round		currentRound;

	public Game() {
		initializeHeap();
		shuffleHeap();
		
		selectNextDistributor();
		
		currentRound = new Round();
	}
	
	void initializeHeap() {
		for (CardSymbol symbol : CardSymbol.values()) {
			CardValue currentCardValue = new CardValue((byte)1);
			do {
				heap.add(new Card(symbol, currentCardValue));
				currentCardValue = currentCardValue.getNext();
			} while (currentCardValue != null);
		}
	}
	
	void shuffleHeap() {
	}
	
	void selectNextDistributor() {
		isDistributorTeam1 = !isDistributorTeam1;
		isDistributorPlayer1 = !isDistributorPlayer1;
	}
	
	byte getDistributorTeamIndex() {
		return (byte)(isDistributorTeam1 ? 1 : 2);
	}
	
	byte getDistributorPlayerIndex() {
		return (byte)(isDistributorPlayer1 ? 1 : 2);
	}
}
