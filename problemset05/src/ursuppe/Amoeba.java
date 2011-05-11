package ursuppe;

import java.util.ArrayList;

import ursuppe.Board.WindDirection;
import ursuppe.Game.Colour;

public class Amoeba {
	private Game game;
	private Colour colour;
	private Player player;
	private ISquare square;
	private int damagePoints=0;
	private int fatalDamage;
	private int neededFood;
	

	
	public Amoeba(Game game, Player player, Colour colour, ISquare square, ArrayList<IGene> genes){
		this.game=game;
		this.player=player;
		this.colour=colour;
		enterSquare(square);
		this.setFatalDamage(2);
		this.activateGenes(genes);
		this.neededFood=3;
		
	}
	
	private void activateGenes(ArrayList<IGene> genes) {
		for(IGene gene:genes){
			this.activateGene(gene);
		}
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
			square.eatFoodStuff(colour.toString(), neededFood);
			square.addFood(colour.toString(), 2);
		}else{this.damagePoints++;}
	}

	private boolean enoughFoodAvailable() {
		return (square.countTotalFood()-square.countFood(colour.toString()) >=neededFood);
	}
	public ISquare getSquare(){
		return this.square;
	}

	public void die() {
		this.leaveSquare();
	}
	
	public int getDamagePoints() {
		return damagePoints;
	}

	public void activateGene(IGene gene) {
		gene.activate(this);
	}
	
	public void setFatalDamage(int i){
		this.fatalDamage = i;
	}

	public int getFatalDamage() {
		return fatalDamage;
	}
	
	public int getNeededFood(){
		return neededFood;
	}
	
	public void setNeedeFood(int i){
		neededFood=i;
	}

	public void move(WindDirection direction) {
		ISquare newSquare=game.getSquareInDirection(this.square,direction);
		if(!newSquare.isNullSquare()){
			this.leaveSquare();
			this.enterSquare(newSquare);
		}
	}



}
