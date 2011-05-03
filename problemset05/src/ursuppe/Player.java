package ursuppe;

import java.util.ArrayList;
import java.util.Collections;

import ursuppe.Game.Colour;

public class Player {
	private String name;
	private ArrayList<Amoeba> amoebas=new ArrayList<Amoeba>();
	private Colour colour;
	private Game game;
	private int lastRoll;
	private int score;
	private int biopoints;
	private Board board;
	
	public Player(Game game, Board board, String name, Colour colour){
		this.name=name;
		this.colour=colour;
		this.game=game;
		this.biopoints=0;
		this.board=board;
	}
	
	public void initAmoeba(){
		Integer[] position;
		do{
		position=setRandomPosition();
		}while(!(board.getSquare(position[0], position[1]).countAmoebas() == 0));
		ISquare square = board.getSquare(position[0], position[1]);
		amoebas.add(new Amoeba(game, this, colour, square));
	}

	private Integer[] setRandomPosition() {
		int horizontal=0;
		int vertical=0;
		do{
			horizontal=(int) (4 * Math.random());
			assert horizontal >= 0 && horizontal <= 4;
			vertical=(int) (3 * Math.random());
			assert vertical >= 0 && vertical <= 3;
		}while(board.getSquare(horizontal, vertical).isNullSquare());
		Integer[] point={horizontal, vertical};
		return point;
		
		
	}

	public int countAmoebas() {
		return amoebas.size();
	}
	
	public void setLastRoll(int roll){
		this.lastRoll=roll;
	}
	public int getLastRoll(){
		return this.lastRoll;
	}

	public void addScore(int score) {
		this.score +=score;		
	}
	public int getScore(){
		return score;
	}

	public void moveAndFeedAmoebas() {
		for(Amoeba amoeba: amoebas){
			amoeba.drift();
			amoeba.feed();
		}
		
	}

	public void addBiopoints(int quantity) {
		biopoints += quantity;
	}

	public void divideAmoebas() {
		while(biopoints>=6 && amoebas.size()<7){
			if(amoebas.size()<=1){
				initAmoeba();
				biopoints -=6;
			}else{
				ISquare newSquare=findGoodNeighbourSquare();
				amoebas.add(new Amoeba(game, this, colour, newSquare));
				biopoints -=6;
			}
		}
	}

	private ISquare findGoodNeighbourSquare() {
		ArrayList<ISquare> goodNeighbourSquares=findAllGoodNeighbourSquare();
		Collections.shuffle(goodNeighbourSquares);
		return goodNeighbourSquares.get(0);
	}

	private ArrayList<ISquare> findAllGoodNeighbourSquare() {
		ArrayList<ISquare> neighbourSquares= getNeighbourSquares();
		ArrayList<ISquare> goodNeighbourSquares= new ArrayList<ISquare>();
		for(ISquare square: neighbourSquares){
			for(Amoeba amoeba: amoebas){
				if(amoeba.getSquare().equals(square)){
					goodNeighbourSquares.add(square);
				}
			}
		}
		return goodNeighbourSquares;
	}

	private ArrayList<ISquare> getNeighbourSquares() {
		ArrayList<ISquare> neighbourSquares=new ArrayList<ISquare>();
		for(Amoeba amoeba:amoebas){
			neighbourSquares.addAll(board.getNeighbourSquares(amoeba.getSquare()));
		}
		return neighbourSquares;
	}

	public void removeDeadAmoebas() {
		for(Amoeba amoeba: amoebas){
			if(amoeba.countDamagePoints()>=2){
				amoeba.die();
				amoebas.remove(amoeba);
			}
		}
	}
}
