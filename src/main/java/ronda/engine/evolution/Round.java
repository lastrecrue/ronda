package ronda.engine.evolution;

import java.util.List;
import java.util.Stack;

import ronda.engine.elements.Card;
import ronda.engine.elements.Player;
import ronda.engine.evolution.event.PlayerEvent;
import ronda.engine.evolution.excpetion.WinnerException;

public abstract class Round {

	public static void startRound(Match match) throws WinnerException {
		distributeCard(match);
		while (!match.getDistributor().getHandCardsPerRound().isEmpty()) {
			PlayerEvent playerEvent = new PlayerEvent(match.getNextPLayer());
			playerEvent.waitEvent(match);

		}
	}

	protected static void distributeCard(Match match) {
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

	private static void distribute4CardPerPlayer(List<Player> players, Stack<Card> heap) {
		for (int i = 0; i < 4; i++) {
			for (Player player : players) {
				Card pop = heap.pop();
				player.getHandCardsPerRound().add(pop);
			}
		}
	}

	private static void distribute3CardPerPlayer(List<Player> players, Stack<Card> heap) {
		for (int i = 0; i < 3; i++) {
			for (Player player : players) {
				Card pop = heap.pop();
				player.getHandCardsPerRound().add(pop);
			}
		}
	}
}