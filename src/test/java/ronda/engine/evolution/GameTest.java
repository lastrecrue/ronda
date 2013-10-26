package ronda.engine.evolution;

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
		Game game1 = new Game();
		Game game2 = new Game();

		assertTrue(game1.getHeap().size() == 40);

		List<Card> heap1 = game1.getHeap();
		List<Card> heap2 = game2.getHeap();
		boolean sameList = false;
		for (int i = 0; i < 40; i++) {

			sameList = sameList || !heap1.get(i).equals(heap2.get(i));
		}
		assertTrue(sameList);
		logger.info("tow heap of tow game is not same");
	}

	@Test
	public void selectNextDistributorTest() {

		Match match = MatchBuilder.match2Vs2Builder(player11, player12, player21, player22);

		List<Player> players = new ArrayList<Player>();
		players.add(player11);
		players.add(player21);
		players.add(player12);
		players.add(player22);

		for (int i = 0; i < 8; i++) {
			logger.info(match.getDistributor());
			assertTrue(match.getDistributor().equals(players.get(i % 4)));
			match.getCurrentGame().selectNextDistributor();
		}
	}

}
