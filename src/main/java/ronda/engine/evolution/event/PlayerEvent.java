package ronda.engine.evolution.event;

import ronda.engine.elements.Player;
import ronda.engine.evolution.Match;

public class PlayerEvent {

	Player player;

	public PlayerEvent(Player nextPLayer) {
		this.player = nextPLayer;
	}

	public void waitEvent(Match match) {
		// wait the action of user
		// if player can report open the possibility of report
		// if action is play card
		// : if needed change the score
		// : else change player card and board card
		// else if action is report close the possibility of report change the score
		// and wait pley card event

	}

}
