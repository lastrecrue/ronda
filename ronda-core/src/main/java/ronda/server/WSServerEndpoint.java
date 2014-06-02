package ronda.server;

import java.io.IOException;
import java.util.logging.Logger;

import javax.websocket.CloseReason;
import javax.websocket.CloseReason.CloseCodes;
import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import ronda.engine.elements.Player;
import ronda.engine.evolution.Match;

/**
 * 
 * @author LAHMOURATE achraf
 * 
 */
@ServerEndpoint(value = "/ronda")
public class WSServerEndpoint {

	private Logger logger = Logger.getLogger(this.getClass().getName());

	@OnOpen
	public void onOpen(Session session) {
		logger.info("Connected ... " + session.getId());
	}

	@OnMessage
	public String onMessage(String unscrambledWord, Session session) {
		try {
			// TODO authentification et recuperation de l'action
			String name = null;

			switch (unscrambledWord) {
			case "start":
				logger.info("Starting the game!");

				Player player = new Player(name);
				Match match = getMatchOnConstruct();
				// TODO Add Player to match on Construction
				// match.addPlayer(player);
				session.getUserProperties().put(name, match);
				return "wait other player";

			case "quit":
				try {
					session.close(new CloseReason(CloseCodes.NORMAL_CLOSURE,
							"Game ended"));
				} catch (IOException e) {
					throw new RuntimeException(e);
				}

				break;
			}

		} catch (Exception e) {
			// TODO exception Actions
		}
		// TODO send action to
		return null;
	}

	private Match getMatchOnConstruct() {
		// TODO Auto-generated method stub
		return null;
	}

	@OnClose
	public void onClose(Session session, CloseReason closeReason) {
		logger.info(String.format("Session %s closed because of %s",
				session.getId(), closeReason));
	}

}
