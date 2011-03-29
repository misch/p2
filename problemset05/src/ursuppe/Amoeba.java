package ursuppe;

public class Amoeba {
	private Game game;
	private String colour;
	private Player player;
	private Square square;
	private int bioPoints;
	private int damagePoints;

	
	public Amoeba(Game game, Player player, String colour, Square square){
		this.game=game;
		this.player=player;
		this.colour=colour;
		enterSquare(square);
		bioPoints=36;
		damagePoints=0;
	}


	private void enterSquare(Square square) {
		this.square=square;
		this.square.enter(this);
	}
	public String toString(){
		return colour;
	}

}
