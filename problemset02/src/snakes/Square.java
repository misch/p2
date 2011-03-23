package snakes;

/**
 * Knows about his <code>position</code>, the {@link Game} it is part of and the {@link Player}s standing on it.
 * It tells if it can be landed on and if not, where the <code>player</code> has to land.
 *
 */
public class Square implements ISquare {

	protected int position;
	protected IGame game;
	private Player player;

	private boolean invariant() {
		return game != null
			&& game.isValidPosition(position);
	}

	public Square(IGame game, int position) {
		this.game = game;
		this.position = position;
		assert invariant();
	}

	public int position() {
		return this.position;
	}

	public ISquare moveAndLand(int moves) {
		assert moves >= 0;
		return game.findSquare(position, moves).landHereOrGoHome();
	}

	protected ISquare nextSquare() {
		return game.getSquare(position+1);
	}

	protected ISquare previousSquare() {
		return game.getSquare(position-1);
	}

	public ISquare landHereOrGoHome() {
		return this.isOccupied() ? game.firstSquare() : this;
	}

	public String toString() {
		return "[" + this.squareLabel() + this.player() + "]";
	}
	
	protected String squareLabel() {
		return Integer.toString(position);
	}
	
	public boolean isOccupied() {
		return player != null;
	}

	public void enter(Player player) {
		assert this.player == null;
		this.player = player;
	}

	public void leave(Player player) {
		assert this.player == player;
		this.player = null;
	}

	public boolean isFirstSquare() {
		return false;
	}

	public boolean isLastSquare() {
		return false;
	}

	protected String player() {
		return this.isOccupied() ? ("<" + this.player + ">") : "";
	}

	@Override
	public boolean isLargeSquare() {
		return false;
	}
}
