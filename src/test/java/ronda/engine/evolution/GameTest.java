package ronda.engine.evolution;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ronda.engine.elements.Card;
import ronda.engine.elements.Player;
import ronda.engine.evolution.builder.MatchBuilder;

public class GameTest extends AbstractTest {

	@Test
	public void testShuffleInConstructor() {
		Match match = MatchBuilder.match2Vs2Builder(player11, player12,
				player21, player22);
		Game game1 = new Game(match);
		game1.initializeHeap();
		Game game2 = new Game(match);
		game2.initializeHeap();

		assertEquals(40, game1.getHeap().size());

		List<Card> heap1 = game1.getHeap();
		List<Card> heap2 = game2.getHeap();
		boolean sameList = false;
		for (int i = 0; i < 40; i++) {
			sameList = sameList || !heap1.get(i).equals(heap2.get(i));
		}
		assertTrue(sameList);
		logger.info("game1 and game2 heaps are not the same");
	}

	// @Test
	public void selectNextDistributorTest() {

		Match match = MatchBuilder.match2Vs2Builder(player11, player12,
				player21, player22);
		match.run();

		List<Player> players = new ArrayList<Player>();
		players.add(player11);
		players.add(player21);
		players.add(player12);
		players.add(player22);

		assertEquals(player11, match.getDistributor());
		assertEquals(40, match.getCurrentGame().getHeap().size());
	}

}
