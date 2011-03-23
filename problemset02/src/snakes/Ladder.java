package snakes;

/**
 * A special type of <code>Square</code>s, which sends {@link Player}s
 * wanting to land on it forwards to another Square, which is <code>transport</code> squares ahead.
 *
 */

public class Ladder extends Square {

	private int transport;

	private boolean invariant() {
		return isValidTransport(transport);
	}

	private boolean isValidTransport(int transport) {
		return transport != 0 && game.isValidPosition(position + transport);
	}

	public Ladder(int transport, IGame game, int position) {
		super(game, position);
		assert isValidTransport(transport);
		this.transport = transport;
		assert invariant();
	}
	
	@Override
	protected String squareLabel() {
		return position + "->" + this.destination().position();
	}

	@Override
	public ISquare landHereOrGoHome() {
		return this.destination().landHereOrGoHome();
	}

	protected ISquare destination() {
		return game.getSquare(position+transport);
	}
}
