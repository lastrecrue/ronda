package ronda.engine.evolution;

import java.util.List;
import java.util.Stack;

import ronda.engine.elements.Card;
import ronda.engine.elements.Player;
import ronda.engine.evolution.excpetion.WinnerException;

public class Round {

	public void startRound(Match match) throws WinnerException {
		distributeCard(match);
		while (!match.getDistributor().getHandCardsPerRound().isEmpty()) {
			play(match.getNextPLayer());
		}
	}

	private void play(Player nextPLayer) {
		// TODO Auto-generated method stub

	}

	protected void distributeCard(Match match) {
		List<Player> players = match.getPlayers();
		Game currentGame = match.getCurrentGame();
		Stack<Card> heap = currentGame.getHeap();
		if (heap.size() == 40) {
			distribute4CardPerPlayer(players, heap);
		} else {
			if (match.is2Vs2Game()) {
				distribute3CardPerPlayer(players, heap);
			} else {
				distribute4CardPerPlayer(players, heap);
			}
		}
	}

	private void distribute4CardPerPlayer(List<Player> players, Stack<Card> heap) {
		for (int i = 0; i < 4; i++) {
			for (Player player : players) {
				Card pop = heap.pop();
				player.getHandCardsPerRound().add(pop);
			}
		}
	}

	private void distribute3CardPerPlayer(List<Player> players, Stack<Card> heap) {
		for (int i = 0; i < 3; i++) {
			for (Player player : players) {
				Card pop = heap.pop();
				player.getHandCardsPerRound().add(pop);
			}
		}
	}
}