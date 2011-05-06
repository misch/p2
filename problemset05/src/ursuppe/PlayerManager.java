package ursuppe;

import java.util.ArrayList;
import java.util.Collections;

public class PlayerManager {
	
	private ArrayList<Player> players;
	private Player[] scores= new Player[51];
	private final int WINNING=42;
	private Player winner=null;
	
	public PlayerManager(ArrayList<Player> players){
		this.players=players;
		Collections.shuffle(this.players);
		setStartingScores();
	}
	private void setStartingScores() {
		for(int i=3; i>0;i--){
			scores[i-1]=players.get(3-i);
			players.get(3-i).addScore(i);
		}
	}
	public ArrayList<Player> getPlayersInAscendingOrder(){
		return this.players;
	}
	
	public ArrayList<Player> getPlayersInDescendingOrder(){
		ArrayList<Player> playersInverted=new ArrayList<Player>();
		for(Player p:players)
			playersInverted.add(0, p);
		return playersInverted;
	}
	public void addScore(Player player, int score){
		int initialScore=player.getScore();
		int addedScore=0;
		while(score>addedScore){
			if (scores[initialScore+addedScore]!=null){
				score++;
			}
			addedScore++;
		}
		scores[initialScore-1]=null;
		scores[initialScore+addedScore-1]=player;
		player.addScore(addedScore);
		
	}
	private void checkWinner() {
		Player potentialWinner=players.get(0);
		for(Player player: players){
			if(player.getScore()>potentialWinner.getScore()){
				potentialWinner=player;
			}
		}
		if(potentialWinner.getScore()>=this.WINNING){
			this.winner=potentialWinner;
		}
	}
	public Player getWinner() {
		checkWinner();
		return winner;
	}
	
	public String scoresToString(){
		String result="|";
		for(Player player:scores){
			if(player!=null){
				result+=player.getName()+"|";
			}else{result+="  |";}
		}
		return result;
	}
	
}
