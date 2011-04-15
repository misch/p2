package ursuppe;

import java.util.ArrayList;

public class Square {
	
	private ArrayList<Amoeba> amoebas=new ArrayList<Amoeba>();
	public enum FoodStuff { 
		green("g"),
		blue("b"),
		red("r"),
		yellow("y");
		private String representation; // string representation of value
		Token(String s) { this.representation = s; }
		public String toString() { return this.representation; }
	private Game game;
	private int horizontalPosition;
	private int verticalPosition;
	private int greenFood;
	private int blueFood;
	private int redFood;
	
	public boolean invariant(){
		return game !=null;
	}
	
	public Square (Game game, int horizontal, int vertical){
		
		this.game=game;
		this.horizontalPosition=horizontal;
		this.verticalPosition=vertical;
		this.initFood();
	}
	
	public void enter(Amoeba amoeba){
		amoebas.add(amoeba);
	}

	private void initFood() {
		assert greenFood==0;
		assert blueFood==0;
		assert redFood==0;
		greenFood=2;
		blueFood=2;
		redFood=2;
	}
	public String toString(){
		return " ["+amoebas+","+greenFood+"G "+blueFood+"B "+redFood+"R ] ";
	}
	
	public int countGreenFood(){
		return greenFood;
	}
	
	public int countBlueFood(){
		return blueFood;
	}
	
	public int countRedFood(){
		return redFood;
	}
	public int countTotalFood(){
		return greenFood+blueFood+redFood;
	}

}
