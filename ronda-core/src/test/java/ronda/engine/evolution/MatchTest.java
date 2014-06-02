package ronda.engine.evolution;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;

import ronda.engine.elements.Card;
import ronda.engine.elements.Player;
import ronda.engine.elements.player.BasicIAPlayer;
import ronda.engine.elements.player.ConsolePlayer;
import ronda.engine.elements.player.PlayerActions;
import ronda.engine.elements.player.builder.BasicIAPlayerBuilder;
import ronda.engine.evolution.builder.MatchBuilder;

public class MatchTest extends AbstractTest {

	// @Test
	public void testBasicIAVsBasicIAMatch() {
		Match match = MatchBuilder.getMatch2Vs2Builder(
				BasicIAPlayerBuilder.getPlayer11(),
				BasicIAPlayerBuilder.getPlayer12(),
				BasicIAPlayerBuilder.getPlayer21(),
				BasicIAPlayerBuilder.getPlayer22());
		for (Player player : match.getPlayers()) {
			((BasicIAPlayer) player).startPlaying();
		}
		match.run();
	}

	@Ignore
	@Test
	public void testBasicIAVsConsolePlayerMatch() {
		Match match = MatchBuilder.getMatch1Vs1Builder(BasicIAPlayerBuilder
				.getPlayer11(), new ConsolePlayer("console11"));
		for (Player player : match.getPlayers()) {
			List<Card> heap = match.getCurrentGame().getHeap();
			for (Card card : heap) {
				System.out.print(card + ", ");
			}
			System.out.println();
			((PlayerActions) player).startPlaying();
		}
		match.run();
	}
}
