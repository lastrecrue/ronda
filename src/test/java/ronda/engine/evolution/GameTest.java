package ronda.engine.evolution;

import static org.junit.Assert.assertTrue;

import java.util.ArrayList;
import java.util.List;

import org.junit.Test;

import ronda.engine.elements.Card;
import ronda.engine.elements.Player;
import ronda.engine.elements.Team;

public class GameTest extends AbstractTest {

	@Test
	public void testShuffleInConstructor() {
		Game game1 = new Game();
		Game game2 = new Game();

		assertTrue(game1.heap.size() == 40);

		List<Card> heap1 = game1.heap;
		List<Card> heap2 = game2.heap;
		boolean sameList = false;
		for (int i = 0; i < 40; i++) {

			sameList = sameList || !heap1.get(i).equals(heap2.get(i));
		}
		assertTrue(sameList);

	}

	@Test
	public void selectNextDistributorTest() {
		List<Player> players = new ArrayList<Player>();
		Player player11 = new Player("player11");
		players.add(player11);
		Player player12 = new Player("player12");
		players.add(player12);
		Player player21 = new Player("player21");
		players.add(player21);
		Player player22 = new Player("player22");
		players.add(player22);
		Team team1 = new Team("team1", player11, player12);
		Team team2 = new Team("team2", player21, player22);
		Match match = new Match(team1, team2);
		for (int i = 0; i < 8; i++) {
			match.getDistributor().equals(players.get(i % 4));
			match.currentGame.selectNextDistributor();
		}
	}
}
