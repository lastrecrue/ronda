package ronda.engine.evolution;

import org.junit.Test;

import ronda.engine.elements.Player;
import ronda.engine.elements.player.BasicIAPlayer;
import ronda.engine.evolution.builder.MatchBuilder;

public class MatchTest extends AbstractTest {

	@Test
	public void testMatch() {
		Match match = MatchBuilder.match2Vs2Builder(player11, player12,
				player21, player22);
		for (Player player : match.getPlayers()) {
			((BasicIAPlayer) player).startPlaying();
		}
		match.run();
	}
}
