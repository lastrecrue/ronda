package ronda.engine.evolution;

import ronda.engine.elements.Move;

public class InvalidMoveException extends Exception {

	private static final long serialVersionUID = -1914617516535880319L;

	private Move move;

	public InvalidMoveException() {
		super();
	}

	public InvalidMoveException(Move move, String message) {
		super(message);
		this.move = move;
	}

	public Move getMove() {
		return move;
	}

}
