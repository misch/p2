package snakes;

/**
 * A special type of <code>Square</code>s, which sends {@link Player}s
 * wanting to land on it backwards to another Square, which is <code>transport</code> squares back.
 *
 */

public class Snake extends Ladder {

	public Snake(int transport, IGame game, int position) {
		super(transport, game, position);
	}

	@Override
	protected String squareLabel() {
		return this.destination().position() + "<-" + position;
	}

}
