package ronda.engine.evolution;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import ronda.engine.elements.Card;
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

	@Test
	public void selectFirstDistributorTest() {

		Match match = MatchBuilder.match2Vs2Builder(player11, player12,
				player21, player22);

		match.getCurrentGame().selectNextDistributor();

		assertEquals(player11, match.getDistributor());
	}

	@Test
	public void checkDistributorTurns1vs1() {

		Match match = MatchBuilder.match1Vs1Builder(player11, player21);

		// first distributor
		match.getCurrentGame().selectNextDistributor();

		assertEquals(player11, match.getDistributor());

		// second distributor
		match.getCurrentGame().selectNextDistributor();

		assertEquals(player21, match.getDistributor());

		// back to the first
		match.getCurrentGame().selectNextDistributor();

		assertEquals(player11, match.getDistributor());
	}

	@Test
	public void checkDistributorTurns2vs2() {

		Match match = MatchBuilder.match2Vs2Builder(player11, player12,
				player21, player22);

		// first distributor
		match.getCurrentGame().selectNextDistributor();

		assertEquals(player11, match.getDistributor());

		// second distributor
		match.getCurrentGame().selectNextDistributor();

		assertEquals(player21, match.getDistributor());

		// third distributor
		match.getCurrentGame().selectNextDistributor();

		assertEquals(player12, match.getDistributor());

		// last distributor
		match.getCurrentGame().selectNextDistributor();

		assertEquals(player22, match.getDistributor());

		// back to first distributor
		match.getCurrentGame().selectNextDistributor();

		assertEquals(player11, match.getDistributor());
	}

	@Test
	public void checkPlayerTurns() {

		Match match = MatchBuilder.match2Vs2Builder(player11, player12,
				player21, player22);

		match.getCurrentGame().selectNextDistributor();

		assertEquals(player11, match.getDistributor());
		assertEquals(player21, match.getNextPlayer());
		assertEquals(player12, match.getNextPlayer());
		assertEquals(player22, match.getNextPlayer());
		assertEquals(player11, match.getNextPlayer());
	}
}
