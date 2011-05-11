package ursuppe;

import java.util.ArrayList;
import java.util.Collections;

import ursuppe.Game.Colour;

public class Player {
	private String name;
	private ArrayList<Amoeba> amoebas=new ArrayList<Amoeba>();
	private Colour colour;
	private Game game;
	private int score;
	private int biopoints;
	private Board board;
	private ArrayList<IGene> genes=new ArrayList<IGene>();
	private int divisionCost;
	
	public Player(Game game, Board board, String name, Colour colour){
		this.name=name;
		this.colour=colour;
		this.game=game;
		this.biopoints=4;
		this.board=board;
		this.divisionCost=6;
	}
	
	public void initAmoeba(){
		Integer[] position;
		do{
			position=setRandomPosition();
		}
		while(!(board.getSquare(position[0], position[1]).countAmoebas() == 0));
			ISquare square = board.getSquare(position[0], position[1]);
		amoebas.add(new Amoeba(game, this, colour, square, genes));
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

	public void addScore(int score) {
		this.score +=score;		
	}
	public int getScore(){
		return score;
	}

	public void moveAndFeedAmoebas(Die die) {
		for(Amoeba amoeba: amoebas){
			if(biopoints>0 && Math.random()<0.5){
				tryToMove(die, amoeba);
			}else{amoeba.drift();}
			amoeba.feed();
		}
		
	}

	public void tryToMove(Die die, Amoeba amoeba) {
		int roll=die.roll();
		if(roll<=4 || roll==6){
			amoeba.move(board.getRandomWindDirection());
		}
		biopoints--;
	}

	public void addBiopoints(int quantity) {
		biopoints += quantity;
	}

	public void divideAmoebas() {
		while(biopoints>=divisionCost && amoebas.size()<7){
			if(amoebas.size()<=1){
				initAmoeba();
				biopoints -=divisionCost;
			}else{
				ISquare newSquare=findGoodNeighbourSquare();
				amoebas.add(new Amoeba(game, this, colour, newSquare, genes));
				biopoints -=divisionCost;
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
			int amoebasOnSquare=0;
			for(Amoeba amoeba: amoebas){
				if(amoeba.getSquare().equals(square)){amoebasOnSquare++;}
			}
			if(amoebasOnSquare==0){goodNeighbourSquares.add(square);}
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
		int i=0;
		while(i<amoebas.size()){
			if(amoebas.get(i).getDamagePoints()>=amoebas.get(i).getFatalDamage()){
				amoebas.get(i).die();
				amoebas.remove(i);
			}else{i++;}
		}
	}
	
	public ArrayList<Amoeba> getAmoebas(){
		return new ArrayList<Amoeba>(amoebas);
	}
	public Colour getColour(){
		return colour;
	}
	public int getBioPoints(){
		return biopoints;
	}
	public String getName() {
		return name;
	}
	
	public void buyGenes(){
		ArrayList<IGene> genesInPriceRange=getGenesInPriceRange();
		while(genesInPriceRange.size()!=0){
			game.getGenePool().buyGene(genesInPriceRange.get(0), this);
			genesInPriceRange=getGenesInPriceRange();
		}
	}

	public ArrayList<IGene> getGenesInPriceRange() {
		ArrayList<IGene> availableGenes=game.getAvailableGenes();
		ArrayList<IGene> genesInPriceRange=new ArrayList<IGene>();
		for(IGene gene:availableGenes){
			if(gene.getPrice()<=this.biopoints)
				genesInPriceRange.add(gene);
		}
		return genesInPriceRange;
	}

	public void pay(int price) {
		assert this.biopoints>=price;
		
	}

	public void addGene(IGene gene) {
		genes.add(gene);
		gene.activateOnPlayer(this);
		for(Amoeba amoeba:amoebas){
			amoeba.activateGene(gene);
		}
		
	}
	public void setDivisionCost(int divisionCost) {
		this.divisionCost = divisionCost;
	}

	public int getDivisionCost() {
		return divisionCost;
	}

	public void removeGene(IGene gene) {
		genes.remove(gene);
	}
	
	public int getMutationPoints(){
		int result=0;
		for(IGene gene:genes){
			result+=gene.getMutationPoints();
		}
		return result;
	}

	public void payBack(int diff) {
		if (diff <= 0) return;
		
		if (diff <= biopoints)
			biopoints -= diff;
		else {
			IGene gene = genes.get(0);
			game.getGenePool().returnGene(gene, this);
			diff -= gene.getMutationPoints();
			payBack(diff);
		}
	}
}
