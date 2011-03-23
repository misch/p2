package snakes;

import java.util.ArrayList;

/**
 * A special {@link Square} that can hold several {@link Player}s at the same time
 * instead of sending them back to the {@link FirstSquare}.
 *
 */

public class LargeSquare extends Square implements ISquare {
	
	private ArrayList<Player> players= new ArrayList<Player>();

	public LargeSquare(IGame game, int position) {
		super(game, position);
	}
	
	public ISquare landHereOrGoHome() {
		return this;
	}
	
	//DR you don't have to override this method it's enough to override the representation of the player and the label!
	public String toString() {
		return "[" + this.squareLabel() + this.players() + "]";
	}
	
	public void enter(Player player) {
		players.add(player);
	}

	public void leave(Player player) {
		assert players.contains(player);
		players.remove(player);
	}
	private String players(){
		String thePlayers= "";
		for(Player p : players){thePlayers+= "<"+p.toString()+">";}
		return thePlayers;
	}
	
    @Override
    public boolean isLargeSquare() {
    	return true;
    }
    protected String squareLabel(){
    	return "|"+position+"|";
    }
	

}
