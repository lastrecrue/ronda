package ronda.engine.evolution;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import ronda.engine.elements.Card;
import ronda.engine.elements.player.builder.BasicIAPlayerBuilder;
import ronda.engine.evolution.builder.MatchBuilder;

public class GameTest extends AbstractTest {

	@Test
	public void testShuffleInConstructor() {
		Match match = MatchBuilder.getMatch2Vs2Builder(
				BasicIAPlayerBuilder.getPlayer11(),
				BasicIAPlayerBuilder.getPlayer12(),
				BasicIAPlayerBuilder.getPlayer21(),
				BasicIAPlayerBuilder.getPlayer22());
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

		Match match = MatchBuilder.getMatch2Vs2Builder(
				BasicIAPlayerBuilder.getPlayer11(),
				BasicIAPlayerBuilder.getPlayer12(),
				BasicIAPlayerBuilder.getPlayer21(),
				BasicIAPlayerBuilder.getPlayer22());

		match.getCurrentGame().selectNextDistributor();

		assertEquals(BasicIAPlayerBuilder.getPlayer11(), match.getDistributor());
	}

	@Test
	public void checkDistributorTurns1vs1() {

		Match match = MatchBuilder.getMatch1Vs1Builder(
				BasicIAPlayerBuilder.getPlayer11(),
				BasicIAPlayerBuilder.getPlayer21());

		// first distributor
		match.getCurrentGame().selectNextDistributor();

		assertEquals(BasicIAPlayerBuilder.getPlayer11(), match.getDistributor());

		// second distributor
		match.getCurrentGame().selectNextDistributor();

		assertEquals(BasicIAPlayerBuilder.getPlayer21(), match.getDistributor());

		// back to the first
		match.getCurrentGame().selectNextDistributor();

		assertEquals(BasicIAPlayerBuilder.getPlayer11(), match.getDistributor());
	}

	@Test
	public void checkDistributorTurns2vs2() {

		Match match = MatchBuilder.getMatch2Vs2Builder(
				BasicIAPlayerBuilder.getPlayer11(),
				BasicIAPlayerBuilder.getPlayer12(),
				BasicIAPlayerBuilder.getPlayer21(),
				BasicIAPlayerBuilder.getPlayer22());

		// first distributor
		match.getCurrentGame().selectNextDistributor();

		assertEquals(BasicIAPlayerBuilder.getPlayer11(), match.getDistributor());

		// second distributor
		match.getCurrentGame().selectNextDistributor();

		assertEquals(BasicIAPlayerBuilder.getPlayer21(), match.getDistributor());

		// third distributor
		match.getCurrentGame().selectNextDistributor();

		assertEquals(BasicIAPlayerBuilder.getPlayer12(), match.getDistributor());

		// last distributor
		match.getCurrentGame().selectNextDistributor();

		assertEquals(BasicIAPlayerBuilder.getPlayer22(), match.getDistributor());

		// back to first distributor
		match.getCurrentGame().selectNextDistributor();

		assertEquals(BasicIAPlayerBuilder.getPlayer11(), match.getDistributor());
	}

	@Test
	public void checkPlayerTurns() {

		Match match = MatchBuilder.getMatch2Vs2Builder(
				BasicIAPlayerBuilder.getPlayer11(),
				BasicIAPlayerBuilder.getPlayer12(),
				BasicIAPlayerBuilder.getPlayer21(),
				BasicIAPlayerBuilder.getPlayer22());

		match.getCurrentGame().selectNextDistributor();

		assertEquals(BasicIAPlayerBuilder.getPlayer11(), match.getDistributor());
		assertEquals(BasicIAPlayerBuilder.getPlayer21(),
				match.getNextPlayer(BasicIAPlayerBuilder.getPlayer11()));
		assertEquals(BasicIAPlayerBuilder.getPlayer12(),
				match.getNextPlayer(BasicIAPlayerBuilder.getPlayer21()));
		assertEquals(BasicIAPlayerBuilder.getPlayer22(),
				match.getNextPlayer(BasicIAPlayerBuilder.getPlayer12()));
		assertEquals(BasicIAPlayerBuilder.getPlayer11(),
				match.getNextPlayer(BasicIAPlayerBuilder.getPlayer22()));
	}
}
