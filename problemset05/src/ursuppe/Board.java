package ursuppe;

import java.util.*;

public class Board {
	
	public enum WindDirection { 
		nord("nord"),
		east("east"),
		south("south"),
		west("west");
		private String representation;
		WindDirection(String s) { this.representation = s; }
		public String toString() { return this.representation; }
	}
	
	private ISquare[][] squares= new ISquare[5][5];
	private Game game;
	private WindDirection windDirection;
	
	public Board(Game game){
		this.game=game;
		initSquares();
		setWindDirection();
	}
	
	public void setWindDirection() {
		windDirection=getRandomWindDirection();
	}

	public static WindDirection getRandomWindDirection() {
		WindDirection direction = null;
		int dir = 1 + (int) (4 * Math.random());
		assert dir >= 1 && dir <= 4;
		switch(dir){
		case(1):
			direction=WindDirection.nord;
			break;
		case(2):
			direction=WindDirection.east;
			break;
		case(3):
			direction=WindDirection.south;
			
		case(4):
			direction=WindDirection.west;
		}
		return direction;
	}
	
	private boolean isInBoardRange(int horizontal, int vertical){
		
		if((horizontal==0 && vertical==0) || (horizontal==2 && vertical ==2) || (horizontal==4 && vertical !=3)){
			return false;}
		else{ return true;}	
	}
	
	private void initSquares(){
		for(int horizontal=0; horizontal<5;horizontal++){
			for(int vertical=0; vertical<5;vertical++){
				if(isInBoardRange(horizontal,vertical)){
					squares[horizontal][vertical]=new Square(game, horizontal, vertical);
				}else{squares[horizontal][vertical]=new NullSquare(horizontal, vertical);}
			}
		}
		
	}
	
	//DR you have to redo your toString method it should look nice but it's ok for Iteration 1^^
	public String toString(){
		String result="";
		for(int horizontal=0; horizontal<5;horizontal++){
			for(int vertical=0; vertical<5;vertical++){
				if(horizontal==2 && vertical==2){
					result+= " [ ~ "+windDirection.toString()+" ~ ] ";
				}else{result+= squares[horizontal][vertical].toString();}
			}
			result+="\n";
		}
		return result;
	}

	public ISquare getSquare(int horizontal, int vertical) {
		return squares[horizontal][vertical];
	}
	
	public ISquare getSquareInWindDirection(ISquare square){
		return getSquareInDirection(square, windDirection);
	}

	public ISquare getSquareInDirection(ISquare square, WindDirection direction) {
		if(direction==WindDirection.nord && square.getHorizontalPosition()>0){
			return squares[square.getHorizontalPosition()-1][square.getVerticalPosition()];
		}else{if(direction==WindDirection.east && square.getVerticalPosition()<4){
			return squares[square.getHorizontalPosition()][square.getVerticalPosition()+1];
		}else{if(direction==WindDirection.south && square.getHorizontalPosition()<4){
			return squares[square.getHorizontalPosition()+1][square.getVerticalPosition()];
		}else{if(direction==WindDirection.west && square.getVerticalPosition()>0){
			return squares[square.getHorizontalPosition()][square.getVerticalPosition()-1];
		}}}}
		return new NullSquare();
	}

	public ArrayList<ISquare> getNeighbourSquares(ISquare square) {
		ArrayList<ISquare> neighbourSquares= new ArrayList<ISquare>();
		for(WindDirection direction: WindDirection.values()){
			if(!getSquareInDirection(square, direction).isNullSquare())
				neighbourSquares.add(getSquareInDirection(square, direction));
		}
		
		return neighbourSquares;
	}
	
	public WindDirection getWindDirection(){
		return windDirection;
	}

}
