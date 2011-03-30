package ursuppe;

import java.util.ArrayList;

public class Player {
	private String name;
	private ArrayList<Amoeba> amoebas=new ArrayList<Amoeba>();
	private String colour;
	private Game game;
	
	public Player(Game game, String name, String colour){
		this.name=name;
		this.colour=colour;
		this.game=game;
		initAmoeba();
		initAmoeba();
	}
	
	private void initAmoeba(){
		Integer[] position=setRandomPosition();
		Square square = game.getSquare(position[0], position[1]);
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
		}while(game.getSquare(horizontal, vertical)==null);
		Integer[] point={horizontal, vertical};
		return point;
		
		
	}

	public int countAmoebas() {
		return amoebas.size();
	}
	
	

}
