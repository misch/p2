package snakes;

/**
 * Knows about his <code>name</code> the {@link Game} and the {@link Square} it is in.
 * Can join a game and move in it. The <code>Player</code> is the one who knows if he wins.
 */

public class Player {
	
	private String name;
	private ISquare square;
	private IGame game;

	private boolean invariant() {
		return name != null
			&& square != null;
	}

	public Player(String name) {
		this.name = name;
		// invariant holds only after joining a game
	}

	public void joinGame(IGame game) {
		this.game=game;
		square = game.firstSquare();
		square.enter(this);	
		assert invariant();
	}

	public int position() {
		assert invariant();
		return square.position();
	}

	public void moveForward(int moves) {
		assert moves > 0;
		square.leave(this);
		square = square.moveAndLand(moves);
		square.enter(this);
	}
	public void moveTo(int position){
		square.leave(this);
		square = game.getSquare(position);
		square.enter(this);
	}
	
	public String toString() {
		return name;
	}

	public ISquare square() {
		return square;
	}

	public boolean wins() {
		return square.isLastSquare();
	}

}
