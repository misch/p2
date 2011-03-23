package snakes;

/**
 * Tells the {@link Player} that it is the <code>LastSquare</code>, so the player knows if he wins.
 * There should only be one <code>LastSquare</code> per {@link Game}.
 *
 */

public class LastSquare extends Square {

	public LastSquare(IGame game, int position) {
		super(game, position);
	}

	@Override
	public boolean isLastSquare() {
		return true;
	}
}
