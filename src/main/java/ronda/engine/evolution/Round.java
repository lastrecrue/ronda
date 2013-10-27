package ronda.engine.evolution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import ronda.engine.elements.Card;
import ronda.engine.elements.CardValue;
import ronda.engine.elements.Move;
import ronda.engine.elements.Player;

public class Round {
	private Match currentMatch;
	private boolean maximumScoreReached;

	public Round(Match currentMatch) {
		this.currentMatch = currentMatch;
	}

	public void run() {
		maximumScoreReached = false;
		distribute();
		
		while (!roundEnded()) {
			Player nextPlayer = currentMatch.getNextPlayer();
			Move move = nextPlayer.play();
			boolean moveApplied;
			do {
				try {
					applyMove(move);
					moveApplied = true;
				} catch (InvalidMoveException e) {
					moveApplied = false;
				}
			} while (!moveApplied);
		}
	}

	private void applyMove(Move move) throws InvalidMoveException {
		if (!move.isValid()) {
			throw new InvalidMoveException(move, "Invalid move");
		}
		
		// Move the card to the board
		List<Card> board = currentMatch.getCurrentGame().getBoard();
		Player player = move.getPlayer();
		Card movedCard = move.getCardMoved();
		player.getHandCardsPerRound().remove(movedCard);	
		board.add(movedCard);
		
		// Determine cards won by this move
		if (board.size() == 1) {
			return;	// Nothing to do
		}
		
		Map<CardValue, List<Card>> duplicatedCardValueMap = getDuplicateCardOnBoardAndSuccessors(board);
		if (duplicatedCardValueMap.size() == 0) {
			return;	// Nothing to do
		}
				
		// Remove the card from the board, and remove its duplicate, and successors
		for (Entry<CardValue, List<Card>> entry : duplicatedCardValueMap.entrySet()) {
			for (Card card : entry.getValue()) {
				board.remove(card);
				player.getWonCardsPerHeap().add(card);
			}
		}
		
		if (board.isEmpty()) {
			// Missa
		}
	}
	
	private void incrementScore(Player player, byte increment) {
		
	}

	private static Map<CardValue, List<Card>> getFrequencyOfOccurencesInCardList(List<Card> list) {
		Map<CardValue, List<Card>> result = new HashMap<CardValue, List<Card>>();

		for (Card card : list) {
			CardValue cardValue = card.getValue();
			List<Card> cardsOfValue = result.get(cardValue);
			if (cardsOfValue == null) {
				cardsOfValue = new ArrayList<Card>();
				result.put(cardValue, cardsOfValue);
			}
			cardsOfValue.add(card);
		}
		
		return result;
	}

	private Map<CardValue, List<Card>> getDuplicateCardOnBoardAndSuccessors(List<Card> list) {
		Map<CardValue, List<Card>> resultComplete = getFrequencyOfOccurencesInCardList(list);
		
		Map<CardValue, List<Card>> result = new HashMap<CardValue, List<Card>>();
		Iterator<Entry<CardValue, List<Card>>> iter = resultComplete.entrySet().iterator();
		
		Entry<CardValue, List<Card>> entry = null;
		while (iter.hasNext()) {
			entry = iter.next();
			assert(entry.getValue().size() <= 2);
			if (entry.getValue().size() == 2) {
				result.put(entry.getKey(), entry.getValue());
				break;
			}
		}
		
		if (result.size() == 0) return result;
		
		CardValue previousCardValue = entry.getKey();
		while (iter.hasNext()) {
			entry = iter.next();
			assert(entry.getValue().size() == 1);
						
			if (!entry.getKey().isNext(previousCardValue)) {
				break;
			}

			result.put(entry.getKey(), entry.getValue());
			previousCardValue = entry.getKey();
		}
		
		return result;
	}

	private void distribute() {
		byte cardsCount = 4;
		
		if (currentMatch.getCurrentGame().getHeap().size() < 40) {
			if (currentMatch.twoPlayersVersusTwoPlayersGame()) {
				cardsCount = 3;
			}
		}
		
		List<Player> players = currentMatch.getPlayers();
		Player distibutorPlayer = currentMatch.getDistributor();
		int distributorIndex = players.indexOf(distibutorPlayer);
		
		List<Card> heap = currentMatch.getCurrentGame().getHeap();
		for (byte i = 0; i < cardsCount; i++) {
			for (int j = distributorIndex + 1; j != distributorIndex; j = (++j) % players.size()) {
				Player cardReceivingPlayer = currentMatch.getPlayers().get(j);
				cardReceivingPlayer.getHandCardsPerRound().add(heap.remove(0));
			}
		}
	}
	
	private boolean roundEnded() {
		return currentMatch.getDistributor().getHandCardsPerRound().isEmpty() ||
			maximumScoreReached;
	}
}