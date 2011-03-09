package snakes;

public class Player {
	
	private String name;
	private ISquare square;
	private Game game;

	private boolean invariant() {
		return name != null
			&& square != null;
	}

	public Player(String name) {
		this.name = name;
		// invariant holds only after joining a game
	}

	public void joinGame(Game game) {
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
