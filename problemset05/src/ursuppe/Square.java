package ursuppe;

import java.util.ArrayList;

public class Square {
	
	public enum FoodStuff { 
		green("g"),
		blue("b"),
		red("r"),
		yellow("y");
		private String representation;
		FoodStuff(String s) { this.representation = s; }
		public String toString() { return this.representation; }
	}
	private ArrayList<Amoeba> amoebas=new ArrayList<Amoeba>();
	
	private Game game;
	private int horizontalPosition;
	private int verticalPosition;
	private ArrayList<FoodStuff> food =new ArrayList<FoodStuff>();
	
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
		assert food.isEmpty();
		this.addGreenFood(2);
		this.addBlueFood(2);
		this.addRedFood(2);
	}
	private void addRedFood(int quantity) {
		for(int i=1; i<=quantity; i++)
			food.add(FoodStuff.red);		
	}

	private void addBlueFood(int quantity) {
		for(int i=1; i<=quantity; i++)
			food.add(FoodStuff.blue);		
	}

	private void addGreenFood(int quantity) {
		for(int i=1; i<=quantity; i++)
			food.add(FoodStuff.green);
	}

	public String toString(){
		return " ["+amoebas+","+countFood("g")+"G "+countFood("b")+"B "+countFood("r")+"R ] ";
	}
	
	public int countFood(String colour){
		int count=0;
		for(FoodStuff f: food){
			if(f.toString().equals(colour))
				count++;
		}
		return count;
	}
	
/*	public int countBlueFood(){
		return blueFood;
	}
	
	public int countRedFood(){
		return redFood;
	}*/
	public int countTotalFood(){
		return food.size();
	}

}
