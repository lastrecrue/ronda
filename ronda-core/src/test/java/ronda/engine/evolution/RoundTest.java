package ronda.engine.evolution;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;

import ronda.engine.elements.Card;
import ronda.engine.elements.Player;
import ronda.engine.elements.player.builder.BasicIAPlayerBuilder;
import ronda.engine.evolution.builder.MatchBuilder;

public class RoundTest extends AbstractTest {
	@Test
	public void distributeCardTestFo2Vs2() {
		logger.info("distributeCardTestFo2Vs2");
		Match match = MatchBuilder.getMatch2Vs2Builder(BasicIAPlayerBuilder.getPlayer11(), BasicIAPlayerBuilder.getPlayer12(),
				BasicIAPlayerBuilder.getPlayer21(), BasicIAPlayerBuilder.getPlayer22());
		distributionCardTest(match, true);
	}

	@Test
	public void distributeCardTestFo1Vs1() {
		logger.info("distributeCardTestFo1Vs1");
		Match match = MatchBuilder.getMatch1Vs1Builder(BasicIAPlayerBuilder.getPlayer11(), BasicIAPlayerBuilder.getPlayer21());
		distributionCardTest(match, false);
	}

	private void distributionCardTest(Match match, boolean is2Vs2) {
		int nbCard = 4;
		while (!match.getCurrentGame().getHeap().isEmpty()) {
			Round round = new Round(match);
			round.run();
			List<Player> players = match.getPlayers();
			for (Player player : players) {
				logger.info(player);
				List<Card> handCardsPerRound = player.getHandCardsPerRound();
				assertTrue(handCardsPerRound.size() == nbCard);
				handCardsPerRound.clear();
			}
			if (is2Vs2) {
				nbCard = 3;
			}
		}
	}
}
