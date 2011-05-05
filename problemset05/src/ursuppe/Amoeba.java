package ursuppe;

import ursuppe.Game.Colour;

public class Amoeba {
	private Game game;
	private Colour colour;
	private Player player;
	private ISquare square;
	private int bioPoints;
	private int damagePoints;

	
	public Amoeba(Game game, Player player, Colour colour, ISquare square){
		this.game=game;
		this.player=player;
		this.colour=colour;
		enterSquare(square);
		bioPoints=36;
		damagePoints=0;
	}
	
	public void setOnSquare(int horizontal, int vertical){
		this.leaveSquare();
		enterSquare(game.getSquare(horizontal, vertical));
	}
	
	public void drift(){
		ISquare newSquare=game.getSquareInWindDirection(this.square);
		if(!newSquare.isNullSquare()){
			this.leaveSquare();
			this.enterSquare(newSquare);
		}
		
	}


	private void leaveSquare() {
		square.leave(this);
		square=null;
	}

	private void enterSquare(ISquare square) {
		this.square=square;
		this.square.enter(this);
	}
	public String toString(){
		return colour.toString();
	}

	public void feed() {
		if(enoughFoodAvailable()){
			square.eatFoodStuff(colour.toString());
			square.addFood(colour.toString(), 2);
		}else{this.damagePoints++;}
	}

	private boolean enoughFoodAvailable() {
		return (square.countTotalFood()-square.countFood(colour.toString()) >=3);
	}
	public ISquare getSquare(){
		return this.square;
	}

	public int countDamagePoints() {
		return damagePoints;
	}

	public void die() {
		this.leaveSquare();
	}
	public int getBioPoints() {
		return bioPoints;
	}
	
	public int getDamagePoints() {
		return damagePoints;
	}

}
