package ursuppe;

import java.util.*;

import ursuppe.Board.WindDirection;

/*DR Accepted! You passed problemset 5.2!
 * 
 * Everything looks ok: 
 * all the different phases are in your code and you made a good decision for your genecards (a separate class ^^)
 * It would be nice to interact (really play the game) instead of just watching but thats another point
 * But you should at least show if a player buys a gene or decides to move instead of drift!
 * Also I realy like your test coverage it's just awesome!
 * Keep up the good work, can't wait for a GUI (and some sketches)
 */
public class Game {
	
	public enum Colour { 
		green("G"),
		blue("B"),
		red("R");
		private String representation;
		Colour(String s) { this.representation = s; }
		public String toString() { return this.representation; }
	}
	
	private Board board;
	private ArrayList<Player> players = new ArrayList<Player>();
	private Die die;
	private PlayerManager playerManager;
	private String winner;
	private GenePool pool;
	
	public Game() {
		board=new Board(this);
		initPlayers();
		die= new Die();
		pool=new GenePool(this);
	}

	//DR probably you can add the general system out and each phase knows his number?
	public void play() {
		System.out.println(this);
		playFirstPhase();
		boolean firstRound=true;
		while(playerManager.getWinner()==null){
			playSecondPhase1();
			System.out.println("Phase 1 \n"+this);
			playSecondPhase2(firstRound);
			System.out.println("Phase 2 \n"+this);
			playSecondPhase3();
			System.out.println("Phase 3 \n"+this);
			playSecondPhase4();
			System.out.println("Phase 4 \n"+this);
			playSecondPhase5();
			System.out.println("Phase 5 \n"+this);
			playSecondPhase6();
			System.out.println("Phase 6 \n"+this);
			firstRound=false;
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
		for(Player player: playerManager.getPlayersInDescendingOrder()){
			player.buyGenes();
		}
		
	}
	
	@ForTestingOnly
	public void playSecondPhase2(boolean firstRound) {
		board.setEnvironment();
		if(!firstRound){
			for(Player player: playerManager.getPlayersInAscendingOrder()){
				int diff = player.getMutationPoints() - board.getOzoneLayer();
				if(diff > 0){
					player.payBack(diff);
				}
				
			}
		}
		
	}
	
	@ForTestingOnly
	public void playSecondPhase1() {
		for(Player player: playerManager.getPlayersInAscendingOrder()){
			player.moveAndFeedAmoebas(die);
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
		return board.toString()+"\n"+playerManager.scoresToString()+"\n \n";
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

	public ArrayList<IGene> getAvailableGenes() {
		return pool.getAvailableGenes();
	}
	public GenePool getGenePool(){
		return pool;
	}
}
