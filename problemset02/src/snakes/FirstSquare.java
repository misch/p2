package snakes;

import java.util.ArrayList;
import java.util.List;

/**
 * Square with position 1. Holds all {@link Player}s at the begining of the game.
 * There should only be one <code>FirstSquare</code> per <code>Game</code>. 
 * @author vita
 *
 */
public class FirstSquare extends Square {

	private List<Player> players;

	public FirstSquare(IGame game, int position) {
		super(game, position);
		players = new ArrayList<Player>();
	}

	public ISquare landHereOrGoHome() {
		return this;
	}

	@Override
	public boolean isOccupied() {
		return !players.isEmpty();
	}

	@Override
	public void enter(Player player) {
		assert !players.contains(player);
		players.add(player);
	}

	@Override
	public void leave(Player player) {
		assert players.contains(player);
		players.remove(player);
	}

	@Override
	public boolean isFirstSquare() {
		return true;
	}

	@Override
	protected String player() {
		StringBuffer buffer = new StringBuffer();
		for (Player player : players) {
			buffer.append("<" + player + ">");
		}
		return buffer.toString();
	}
}
