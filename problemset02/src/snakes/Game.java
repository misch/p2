package snakes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

/*DR Fix problemset02 for next week! Correct and improve your Javadoc and make the impression that
* you've read the style guide.
* Your TrapDoor and LargeSquare work as intended and you fully tested the functionality of them.
*/

/**
 * Sets up the board and initializes special <code>Square</code>s and manages the development of the game.
 * <p>Knows about the <code>Square</code>s order and the players in the game.
 */
public class Game implements IGame {
	private List<ISquare> squares;
	private int size;
	private Queue<Player> players;
	private Player winner;
	
	private boolean invariant() {
		return squares.size() > 3
			&& size == squares.size()
			&& players.size() > 1;
	}

	public Game(int size, Player[] initPlayers) {
		this.size = size;
		this.addSquares(size);
		this.addPlayers(initPlayers);
		assert invariant();
	}

	@Override
	public boolean isValidPosition(int position) {
		return position>=1 && position<=size;
	}

	public static void main(String args[]) {
		(new SimpleGameTest()).newGame().play(new Die());
	}

	@Override
	public void play(IDie die) {
		System.out.println("Initial state: " + this);
		while (this.notOver()) {
			int roll = die.roll();
			System.out.println(this.currentPlayer() + " rolls " + roll + ":  " + this);
			this.movePlayer(roll);
		}
		System.out.println("Final state:   " + this);
		System.out.println(this.winner() + " wins!");
	}

	@Override
	public boolean notOver() {
		return winner == null;
	}

	@Override
	public boolean isOver() {
		return !this.notOver();
	}

	@Override
	public Player currentPlayer() {
		return players.peek();
	}

	@Override
	public void movePlayer(int roll) {
		assert roll>=1 && roll<=6;
		Player currentPlayer = players.remove(); // from front of queue
		currentPlayer.moveForward(roll);
		players.add(currentPlayer); // to back of the queue
		if (currentPlayer.wins()) {
			winner = currentPlayer;
		}
		assert invariant();
	}

	@Override
	public void setSquare(int position, ISquare square) {
		// Do not change the type of the first or last square!
		assert !this.getSquare(position).isLastSquare()
			&& !this.getSquare(position).isFirstSquare();
		this.initSquare(position, square);
		assert invariant();
	}

	@Override
	public Player winner() {
		return winner;
	}

	@Override
	public ISquare firstSquare() {
		return squares.get(0);
	}

	@Override
	public ISquare getSquare(int position) {
		assert this.isValidPosition(position);
		return squares.get(position - 1);
	}
	

	@Override
	public String toString() {
		StringBuffer buffer = new StringBuffer();
		for (ISquare square : squares) {
			buffer.append(square.toString());
		}
		return buffer.toString();
	}

	private void addSquares(int size) {
		squares = new ArrayList<ISquare>();
		for(int i=0; i<size; i++) {
			Square square = new Square(this, i+1);
			squares.add(square);
		}
		this.initSquare(1, new FirstSquare(this, 1));
		this.initSquare(size, new LastSquare(this, size));
	}

	private void addPlayers(Player[] initPlayers) {
		players = new LinkedList<Player>();
		for (Player player : initPlayers) {
			player.joinGame(this);
			players.add(player);
		}
	}

	private void initSquare(int position, ISquare square) {
		assert this.isValidPosition(position) && square != null;
		squares.set(position-1, square);
	}

	@Override
	public void setSquareToLadder(int position, int transport) {
		this.setSquare(position, new Ladder(transport, this, position));
	}

	
	@Override
	public void setSquareToSnake(int position, int transport) {
		this.setSquare(position, new Snake(transport, this, position));
	}

	@Override
	public void setSquareToLargeSquare(int position) {
		this.setSquare(position, new LargeSquare(this, position));
	}

	@Override
	public void setSquareToTrapDoor(int position, int transport) {
		this.setSquare(position, new TrapDoor(this, position, transport+position));
	}


	@Override
	public ISquare findSquare(int position, int moves) {
		assert position + moves <= 2*size - 1; // can't go more than size-1 moves backwards past end
		int target = position + moves;
		if (target > size) { // reverse direction if we go past the end
			target = size - (target - size);
		}
		return this.getSquare(target);
	}

}
