package ronda.engine.evolution;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.log4j.Logger;

import ronda.engine.elements.Card;
import ronda.engine.elements.CardValue;
import ronda.engine.elements.Move;
import ronda.engine.elements.Player;

public class Round {

	protected Logger logger = Logger.getLogger(Round.class);
	private final Match currentMatch;
	private boolean maximumScoreReached;
	private Player lastEatingPlayer = null;

	public Round(Match currentMatch) {
		this.currentMatch = currentMatch;
	}

	public void run() {
		maximumScoreReached = false;
		distribute();
		Player nextPlayer = currentMatch.getDistributor();

		while (!roundEnded()) {
			logger.debug("board state : "
					+ currentMatch.getCurrentGame().getBoard());
			nextPlayer = currentMatch.getNextPlayer(nextPlayer);
			nextPlayer.setActivePlayer(true);
			while (nextPlayer.isActivePlayer()) {
				// while player is active, sleep
				try {
					Thread.sleep(50);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
			// when player is not active anymore, get his move
			Move move = nextPlayer.getCurrentPlayerMove();
			boolean moveApplied;
			do {
				try {
					applyMove(move);
					moveApplied = true;
				} catch (InvalidMoveException e) {
					moveApplied = false;
				}
			} while (!moveApplied);

			// case of the last move in the round
			if (currentMatch.getDistributor().equals(nextPlayer)
					&& currentMatch.getCurrentGame().getHeap().isEmpty()
					&& nextPlayer.getHandCardsPerRound().isEmpty()) {
				for (Card card : currentMatch.getCurrentGame().getBoard()) {
					lastEatingPlayer.getWonCardsPerHeap().add(card);
				}
				logger.debug("last eating player is : "
						+ lastEatingPlayer.getIdentifier());
				currentMatch.getCurrentGame().getBoard().clear();
			}
		}
	}

	private void applyMove(Move move) throws InvalidMoveException {
		if (move == null || !move.isValid()) {
			throw new InvalidMoveException(move, "Invalid move");
		}

		// Move the card to the board
		List<Card> board = currentMatch.getCurrentGame().getBoard();
		Player player = move.getPlayer();
		Card movedCard = move.getCardMoved();
		// player.getHandCardsPerRound().remove(movedCard);
		board.add(movedCard);

		// Determine cards won by this move
		if (board.size() == 1) {
			return; // Nothing to do
		}

		Map<CardValue, List<Card>> duplicatedCardValueMap = getDuplicateCardOnBoardAndSuccessors(board);
		if (duplicatedCardValueMap.size() == 0) {
			return; // Nothing to do
		}

		// Remove the card from the board, and remove its duplicate, and
		// successors
		for (Entry<CardValue, List<Card>> entry : duplicatedCardValueMap
				.entrySet()) {
			for (Card card : entry.getValue()) {
				board.remove(card);
				player.getWonCardsPerHeap().add(card);
			}
		}
		lastEatingPlayer = player;

		if (board.isEmpty()) {
			// Missa
			logger.debug(player.getIdentifier() + " did missa.");
			currentMatch.incrementScore(currentMatch.getPlayerTeam(player),
					(byte) 1);
		}
	}

	private static Map<CardValue, List<Card>> getFrequencyOfOccurencesInCardList(
			List<Card> list) {
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

	private Map<CardValue, List<Card>> getDuplicateCardOnBoardAndSuccessors(
			List<Card> list) {
		Map<CardValue, List<Card>> resultComplete = getFrequencyOfOccurencesInCardList(list);
		boolean duplicateFound = false;

		Map<CardValue, List<Card>> result = new HashMap<CardValue, List<Card>>();
		Iterator<Entry<CardValue, List<Card>>> iter = resultComplete.entrySet()
				.iterator();

		Entry<CardValue, List<Card>> entry = null;
		CardValue previousCardValue = null;
		while (iter.hasNext()) {
			entry = iter.next();
			assert (entry.getValue().size() <= 2);
			if (entry.getValue().size() == 2) {
				result.put(entry.getKey(), entry.getValue());
				duplicateFound = true;
				previousCardValue = entry.getKey();
			} else {
				if (duplicateFound) {
					assert (entry.getValue().size() == 1);
					if (previousCardValue.isNext(entry.getKey())) {
						result.put(entry.getKey(), entry.getValue());
						previousCardValue = entry.getKey();
					} else {
						break;
					}
				}
			}
		}

		return result;
	}

	/**
	 * Distribute cards to players in this round.
	 */
	private void distribute() {
		byte cardsCount = 4;

		// if we are not in the first round, and the game is a 2vs2 game, then
		// we distribute only 3 cards
		if (currentMatch.getCurrentGame().getHeap().size() < 40) {
			if (currentMatch.twoPlayersVersusTwoPlayersGame()) {
				cardsCount = 3;
			}
		}

		List<Player> players = currentMatch.getPlayers();

		// wipe initial hand cards of previous round and won cards heap
		for (Player player : players) {
			player.getInitialHandCardsPerRound().clear();
		}

		Player distibutorPlayer = currentMatch.getDistributor();
		logger.debug("player : " + distibutorPlayer + " is distributing.");
		int distributorIndex = players.indexOf(distibutorPlayer);

		// distribute cards from the heap, beginning with the player next to the
		// distributor
		List<Card> heap = currentMatch.getCurrentGame().getHeap();
		int indexOfPlayerToDistributeTo = distributorIndex + 1;
		indexOfPlayerToDistributeTo %= players.size();

		while (distibutorPlayer.getHandCardsPerRound().size() < cardsCount) {
			Player cardReceivingPlayer = currentMatch.getPlayers().get(
					indexOfPlayerToDistributeTo);
			Card distributedCard = heap.remove(0);
			cardReceivingPlayer.getHandCardsPerRound().add(distributedCard);
			cardReceivingPlayer.getInitialHandCardsPerRound().add(
					distributedCard);
			indexOfPlayerToDistributeTo++;
			indexOfPlayerToDistributeTo %= players.size();
		}
	}

	private boolean roundEnded() {
		return currentMatch.getDistributor().getHandCardsPerRound().isEmpty()
				|| maximumScoreReached;
	}
}