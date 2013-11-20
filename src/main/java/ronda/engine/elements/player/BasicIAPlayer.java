/**
 * 
 */
package ronda.engine.elements.player;

import ronda.engine.elements.Announcement;
import ronda.engine.elements.Card;
import ronda.engine.elements.Move;
import ronda.engine.elements.Player;

/**
 * @author mehdi
 * 
 */
public class BasicIAPlayer extends Player implements PlayerActions, Runnable {

	public BasicIAPlayer(String id) {
		super(id);
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see ronda.engine.elements.player.PlayerActions#announce()
	 */
	public Move announce() {
		return new Move(this, null, Announcement.singleRondaAnnounced);
	}

	public Move playCard() {
		Card playedCard = this.getHandCardsPerRound().remove(0);
		logger.debug(getIdentifier() + " played : " + playedCard);
		return new Move(this, playedCard);
	}

	public void startPlaying() {
		logger.debug(getIdentifier() + " starts playing.");
		new Thread(this).start();
	}

	public void run() {

		while (true) {
			try {
				Thread.sleep(50);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			if (isActivePlayer()) {
				setCurrentPlayerMove(playCard());
				setActivePlayer(false);
			}
		}
	}
}
