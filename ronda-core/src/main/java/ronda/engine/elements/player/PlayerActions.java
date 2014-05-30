/**
 * 
 */
package ronda.engine.elements.player;

import ronda.engine.elements.Move;
import ronda.engine.evolution.QuitGameException;

/**
 * @author mehdi
 * 
 */
public interface PlayerActions {

	Move playCard() throws QuitGameException;

	Move announce();

	public void startPlaying();
}
