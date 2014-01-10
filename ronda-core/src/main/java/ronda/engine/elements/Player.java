package ronda.engine.elements;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

public class Player {

	protected Logger logger = Logger.getLogger(Player.class);
	private final String identifier;
	private List<Card> wonCardsPerHeap = new ArrayList<Card>();
	private List<Card> handCardsPerRound = new ArrayList<Card>();
	private List<Card> initialHandCardsPerRound = new ArrayList<Card>();
	private Announcement announcementPerRound = Announcement.nothingAnnounced;
	private boolean isActivePlayer;
	private Move currentPlayerMove;

	public Player(String id) {
		assert (id != null);

		identifier = id;
	}

	/**
	 * @return the isActivePlayer
	 */
	public synchronized boolean isActivePlayer() {
		return isActivePlayer;
	}

	/**
	 * @param isActivePlayer
	 *            the isActivePlayer to set
	 */
	public synchronized void setActivePlayer(boolean isActivePlayer) {
		// logger.debug("player : " + identifier + " is active : "
		// + isActivePlayer);
		this.isActivePlayer = isActivePlayer;
	}

	/**
	 * @return the currentPlayerMove
	 */
	public synchronized Move getCurrentPlayerMove() {
		return currentPlayerMove;
	}

	/**
	 * @param currentPlayerMove
	 *            the currentPlayerMove to set
	 */
	public synchronized void setCurrentPlayerMove(Move currentPlayerMove) {
		this.currentPlayerMove = currentPlayerMove;
	}

	public List<Card> getWonCardsPerHeap() {
		return wonCardsPerHeap;
	}

	public void setWonCardsPerHeap(List<Card> wonCardsPerHeap) {
		this.wonCardsPerHeap = wonCardsPerHeap;
	}

	public List<Card> getHandCardsPerRound() {
		return handCardsPerRound;
	}

	public void setHandCardsPerRound(List<Card> handCardsPerRound) {
		this.handCardsPerRound = handCardsPerRound;
	}

	public List<Card> getInitialHandCardsPerRound() {
		return initialHandCardsPerRound;
	}

	public void setInitialHandCardsPerRound(List<Card> initialHandCardsPerRound) {
		this.initialHandCardsPerRound = initialHandCardsPerRound;
	}

	public Announcement getAnnouncementPerRound() {
		return announcementPerRound;
	}

	public void setAnnouncementPerRound(Announcement announcementPerRound) {
		this.announcementPerRound = announcementPerRound;
	}

	public String getIdentifier() {
		return identifier;
	}

	@Override
	public String toString() {
		return identifier;
	}
}
