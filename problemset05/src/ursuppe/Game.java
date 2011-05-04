package ursuppe;

import java.util.*;

import ursuppe.Board.WindDirection;


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
		System.out.println(playerManager.getWinner().getName()+" wins!");
	}
	
	private void playSecondPhase6() {
		for(Player player: playerManager.getPlayersInAscendingOrder()){
			if(player.countAmoebas()<=4){
				playerManager.addScore(player, player.countAmoebas()-2);
			}else{playerManager.addScore(player, player.countAmoebas()-1);}
		}
	}

	private void playSecondPhase5() {
		for(Player player: playerManager.getPlayersInAscendingOrder()){
			player.removeDeadAmoebas();
		}
	}

	private void playSecondPhase4() {
		for(Player player: playerManager.getPlayersInAscendingOrder()){
			player.addBiopoints(10);
			player.divideAmoebas();
		}
	}

	private void playSecondPhase3() {
		
	}

	private void playSecondPhase2() {
		board.setWindDirection();
	}

	private void playSecondPhase1() {
		for(Player player: playerManager.getPlayersInAscendingOrder()){
			player.moveAndFeedAmoebas();
		}
		
	}

	private void playFirstPhase() {
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

}
