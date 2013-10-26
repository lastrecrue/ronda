package ronda.engine.evolution;

import static org.junit.Assert.*;

import java.util.List;

import javax.swing.plaf.basic.BasicScrollPaneUI.VSBChangeListener;

import org.junit.Test;

import ronda.engine.elements.Card;
import ronda.engine.elements.Player;
import ronda.engine.evolution.builder.MatchBuilder;

public class RoundTest extends AbstractTest {
	@Test
	public void distributeCardTestFo2Vs2() {
		logger.info("distributeCardTestFo2Vs2");
		Round round = new Round();
		Match match = MatchBuilder.match2Vs2Builder(player11, player12, player21, player22);
		distributionCardTest(round, match, true);
	}

	@Test
	public void distributeCardTestFo1Vs1() {
		logger.info("distributeCardTestFo1Vs1");
		Round round = new Round();
		Match match = MatchBuilder.match1Vs1Builder(player11, player21);
		distributionCardTest(round, match, false);
	}

	private void distributionCardTest(Round round, Match match, boolean is2Vs2) {
		int nbCard = 4;
		while (!match.getCurrentGame().getHeap().isEmpty()) {
			round.distributeCard(match);
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
