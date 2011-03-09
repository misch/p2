package snakes;

public class TrapDoor extends LargeSquare implements ISquare {
	private Player player;
	private int trapEnd;
	
	public TrapDoor(Game game, int position, int trapEnd){
		super(game, position);
		assert game.getSquare(trapEnd).isLargeSquare();
		this.trapEnd=trapEnd;
	}
	public boolean isOccupied() {
		return player != null;
	}
	public void enter(Player player) {
		assert this.player==null;
		this.player = player;
	}
		
	public void leave(Player player) {
		assert this.player==player;
		assert player != null;
		this.player=null;
	}

	public boolean isLargeSqaure() {
		return false;
	}
	
	/** 
	 * Sends player to trapEnd (not Home) if it's occupied
	 */
	public ISquare landHereOrGoHome() {
		if(this.isOccupied()){
			player.moveTo(trapEnd);
			return game.getSquare(trapEnd);
		}else
			return this;
	}
	protected String squareLabel(){
		return position+"==>"+trapEnd;
	}
	
	
}

