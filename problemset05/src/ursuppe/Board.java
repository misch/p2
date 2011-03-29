package ursuppe;

public class Board {
	
	private Square[][] squares= new Square[5][5];
	private Game game;
	private String windDirection;
	
	public Board(Game game){
		this.game=game;
		initSquares();
		setWindDirection();
	}
	
	private void setWindDirection() {
		int dir = 1 + (int) (4 * Math.random());
		assert dir >= 1 && dir <= 4;
		if(dir==1){
			windDirection="south";
		}
		if(dir==2){
			windDirection="west";
		}
		if(dir==3){
			windDirection="nord";
		}
		if(dir==4){
			windDirection="east";
		}
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
				}else{squares[horizontal][vertical]=null;}
			}
		}
		
	}
	
	public String toString(){
		String result="";
		for(int horizontal=0; horizontal<5;horizontal++){
			for(int vertical=0; vertical<5;vertical++){
				if(isInBoardRange(horizontal,vertical)){
					result+= squares[horizontal][vertical].toString();
				}
				else{
					if(horizontal==2 && vertical==2){
						result+= " [ ~ "+windDirection+" ~ ] ";
					}else{ result+=" |____________| ";}
				}
			}
			result+="\n";
		}
		return result;
	}

	public Square getSquare(int horizontal, int vertical) {
		return squares[horizontal][vertical];
	}

}
