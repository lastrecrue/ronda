package ronda.engine.elements;

/**
 * Class that represents a player move. A move will have at least a card played,
 * and an optional announcement.
 * 
 * @author mehdi
 * 
 */
public class Move {
	private final Player player;
	private final Card cardMoved;
	private Announcement announcement = Announcement.nothingAnnounced;

	public Move(Player player, Card cardMoved, Announcement announcement) {
		this.player = player;
		this.cardMoved = cardMoved;
		this.announcement = announcement;
	}

	public Move(Player player, Card cardMoved) {
		this.player = player;
		this.cardMoved = cardMoved;
	}

	public Player getPlayer() {
		return player;
	}

	public Card getCardMoved() {
		return cardMoved;
	}

	public Announcement getAnnouncement() {
		return announcement;
	}

	/**
	 * Checks if the player has the played card in his hand.
	 * 
	 * @return
	 */
	public boolean isValid() {
		if (player.getInitialHandCardsPerRound().indexOf(cardMoved) == -1)
			return false;
		return true;
	}
}
