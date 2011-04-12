package snakes;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import com.google.inject.*;

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
	private IDie die;
	
	private boolean invariant() {
		return squares.size() > 3
			&& size == squares.size()
			&& players.size() > 1;
	}
	
	@Inject
	public Game(IDie die) {
		this.die=die;
		Player jack = new Player("Jack");
		Player jill = new Player("Jill");
		Player[] args = { jack, jill };
		setUp(12, args);
		this.setSquareToLadder(2, 4);
		this.setSquareToLadder(7, 2);
		this.setSquareToSnake(11, -6);
	}
	
	public Game(int size, Player[] initPlayers, IDie die) {
		setUp(size, initPlayers);
		this.die=die;
		assert invariant();
	}

	private void setUp(int size, Player[] initPlayers) {
		this.size = size;
		this.addSquares(size);
		this.addPlayers(initPlayers);
	}

	public boolean isValidPosition(int position) {
		return position>=1 && position<=size;
	}

	public static void main(String args[]) {
		Injector injector = Guice.createInjector(new SnakesAndLaddersModule());
		IGame game= injector.getInstance(Game.class);
		game.play();
	}
	
	public void play() {
		System.out.println("Initial state: " + this);
		while (this.notOver()) {
			int roll = this.die.roll();
			System.out.println(this.currentPlayer() + " rolls " + roll + ":  " + this);
			this.movePlayer(roll);
		}
		System.out.println("Final state:   " + this);
		System.out.println(this.winner() + " wins!");
	}

	@ForTestingOnly
	public boolean notOver() {
		return winner == null;
	}
	
	
	@ForTestingOnly
	public boolean isOver() {
		return !this.notOver();
	}
	
	@ForTestingOnly
	public Player currentPlayer() {
		return players.peek();
	}
	
	@ForTestingOnly
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

	private void setSquare(int position, ISquare square) {
		// Do not change the type of the first or last square!
		assert !this.getSquare(position).isLastSquare()
			&& !this.getSquare(position).isFirstSquare();
		this.initSquare(position, square);
		assert invariant();
	}
	
	@ForTestingOnly
	public Player winner() {
		return winner;
	}

	public ISquare firstSquare() {
		return squares.get(0);
	}

	public ISquare getSquare(int position) {
		assert this.isValidPosition(position);
		return squares.get(position - 1);
	}
	
	@ForTestingOnly
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
	
	@ForTestingOnly
	public void setSquareToLadder(int position, int transport) {
		this.setSquare(position, new Ladder(transport, this, position));
	}
	
	@ForTestingOnly
	public void setSquareToSnake(int position, int transport) {
		this.setSquare(position, new Snake(transport, this, position));
	}

	@ForTestingOnly
	public void setSquareToLargeSquare(int position) {
		this.setSquare(position, new LargeSquare(this, position));
	}

	@ForTestingOnly
	public void setSquareToTrapDoor(int position, int transport) {
		this.setSquare(position, new TrapDoor(this, position, transport+position));
	}

	public ISquare findSquare(int position, int moves) {
		assert position + moves <= 2*size - 1; // can't go more than size-1 moves backwards past end
		int target = position + moves;
		if (target > size) { // reverse direction if we go past the end
			target = size - (target - size);
		}
		return this.getSquare(target);
	}
	
	public List<Player> getPlayers(){
		return new ArrayList<Player>(players);
	}

}
