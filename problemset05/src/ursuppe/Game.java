package ursuppe;

import java.util.LinkedList;
import java.util.Queue;


/*DR Accepted!
 * 
 * All in all it's a pretty good solution
 * Just redo your toString and add some more tests 
 * (as a general rule one test class for each class and one test for each non-trivial public method)
 * 
 */
public class Game {
	
	private Board board;
	private Queue<Player> players= new LinkedList<Player>();
	
	public Game() {
		board=new Board(this);
		initPlayers();
	}
	private void initPlayers() {
		players.add(new Player(this, "Hans", "blue"));
		players.add(new Player(this, "Fritz", "green"));
		players.add(new Player(this, "Nathanael","red"));
	}
	public static void main(String[] args){
		Game game=new Game();
		System.out.println(game);
	}
	
	public Square getSquare(int horizontal, int vertical) {
		return board.getSquare(horizontal, vertical);
	}
	public Queue<Player> getPlayers(){
		return new LinkedList<Player>(players);
	}
	public String toString(){
		return board.toString();
	}

}
