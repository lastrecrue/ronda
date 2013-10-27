package ronda.engine.elements;

public class Move {
	private Player			player;
	private Card			cardMoved;
	private Announcement	announcement = Announcement.nothingAnnounced;
	
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
	
	public boolean isValid() {
		if (player.getHandCardsPerRound().indexOf(cardMoved) == -1)
			return false;
		return true;
	}
}
