package ronda.engine.elements.player;

import java.util.List;
import java.util.Scanner;

import ronda.engine.elements.Announcement;
import ronda.engine.elements.Card;
import ronda.engine.elements.Move;
import ronda.engine.elements.Player;
import ronda.engine.evolution.QuitGameException;

public class ConsolePlayer extends Player implements PlayerActions, Runnable {

	public ConsolePlayer(String id) {
		super(id);
		// TODO Auto-generated constructor stub
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

		List<Card> handCardsPerRound = this.getHandCardsPerRound();
		int i = 0;
		for (Card card : handCardsPerRound) {
			System.out.println(i + " : " + card);
			i++;
		}

		Card playedCard = chooseCard(handCardsPerRound);

		logger.debug(getIdentifier() + " played : " + playedCard);
		return new Move(this, playedCard);
	}

	private Card chooseCard(List<Card> handCardsPerRound) {
		int indexOfCard = -1;
		while (indexOfCard < 0 || indexOfCard >= handCardsPerRound.size()) {
			Scanner sc = new Scanner(System.in);
			System.out.println("Select card :");
			String str = sc.nextLine();
			try {
				indexOfCard = (int) Integer.valueOf(str);
			} catch (NumberFormatException nfe) {
				if (str.equals("q")) {
					// TODO end game
				}
			}
		}
		Card playedCard = handCardsPerRound.remove(indexOfCard);
		return playedCard;
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
