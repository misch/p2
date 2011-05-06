package ursuppe;

import java.util.*;

import ursuppe.Board.WindDirection;

/*DR Accepted!
 * 
 * All in all it's a pretty good solution
 * Just redo your toString and add some more tests 
 * (as a general rule one test class for each class and one test for each non-trivial public method)
 * 
 */
public class Game {
	
	public enum Colour { 
		green("green"),
		blue("blue"),
		red("red");
		private String representation;
		Colour(String s) { this.representation = s; }
		public String toString() { return this.representation; }
	}
	
	private Board board;
	private ArrayList<Player> players = new ArrayList<Player>();
	private Die die;
	private PlayerManager playerManager;
	private String winner;
	
	public Game() {
		board=new Board(this);
		initPlayers();
		die= new Die();
	}

	public void play() {
		System.out.println(this);
		playFirstPhase();
		while(playerManager.getWinner()==null){
			System.out.println(this);
			playSecondPhase1();
			System.out.println(this);
			playSecondPhase2();
			System.out.println(this);
			playSecondPhase3();
			System.out.println(this);
			playSecondPhase4();
			System.out.println(this);
			playSecondPhase5();
			System.out.println(this);
			playSecondPhase6();
		}
		setWinner();
		System.out.println(winner + " wins!");
	}
	@ForTestingOnly
	public void playSecondPhase6() {
		for(Player player: playerManager.getPlayersInAscendingOrder()){
			if(player.countAmoebas()<=4){
				playerManager.addScore(player, player.countAmoebas()-2);
			}else{playerManager.addScore(player, player.countAmoebas()-1);}
		}
	}
	@ForTestingOnly
	public void playSecondPhase5() {
		for(Player player: playerManager.getPlayersInAscendingOrder()){
			player.removeDeadAmoebas();
		}
	}
	
	@ForTestingOnly
	public void playSecondPhase4() {
		for(Player player: playerManager.getPlayersInAscendingOrder()){
			player.addBiopoints(10);
			player.divideAmoebas();
		}
	}

	@ForTestingOnly
	public void playSecondPhase3() {
		
	}
	
	@ForTestingOnly
	public void playSecondPhase2() {
		board.setWindDirection();
	}
	
	@ForTestingOnly
	public void playSecondPhase1() {
		for(Player player: playerManager.getPlayersInAscendingOrder()){
			player.moveAndFeedAmoebas();
		}
		
	}
	
	@ForTestingOnly
	public void playFirstPhase() {
		for(Player player: playerManager.getPlayersInAscendingOrder()){
			player.initAmoeba();
		}
		for(Player player: playerManager.getPlayersInDescendingOrder()){
			player.initAmoeba();
		}
	}
	
	
	private void initPlayers() {
		players.add(new Player(this, board, "Hans", Colour.blue));
		players.add(new Player(this, board, "Fritz", Colour.green));
		players.add(new Player(this, board, "Nathanael",Colour.red));
		this.playerManager=new PlayerManager(this.players);
	}
	
	public static void main(String[] args){
		Game game=new Game();
		game.play();
	}
	
	public ISquare getSquare(int horizontal, int vertical) {
		return board.getSquare(horizontal, vertical);
	}
	
	public ISquare getSquareInWindDirection(ISquare square) {
		return board.getSquareInWindDirection(square);
	}
	public ISquare getSquareInDirection(ISquare square, WindDirection direction){
		return board.getSquareInDirection(square, direction);
	}
	
	public Queue<Player> getPlayers(){
		return new LinkedList<Player>(players);
	}
	
	public String toString(){
		return board.toString()+"\n"+playerManager.scoresToString()+"\n";
	}
	public Board getBoard(){
		return board;
	}
	
	private void setWinner(){
		this.winner = playerManager.getWinner().getName();
	}
	
	public String getWinner(){
		return winner;
	}
}
