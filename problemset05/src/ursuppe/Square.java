package ursuppe;

import java.util.ArrayList;

public class Square implements ISquare {
	
	public enum FoodStuff { 
		green("green"),
		blue("blue"),
		red("red");
		private String representation;
		FoodStuff(String s) { this.representation = s; }
		public String toString() { return this.representation; }
	}
	
	private ArrayList<Amoeba> amoebas=new ArrayList<Amoeba>();
	private Game game;
	//DR remove unused variables!
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
	@ForTestingOnly
	public void initFood() {
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
	
	public void addFood(String colour, int quantity){
		if(colour.equals("red"))
			addRedFood(quantity);
		else if(colour.equals("blue"))
			addBlueFood(quantity);
		else if(colour.equals("green"))
			addGreenFood(quantity);
	}
	
	public String toString(){
		return " ["+amoebas+","+countFood("green")+"G "+countFood("blue")+"B "+countFood("red")+"R ] ";
	}
	public int countFood(String colour){
		int count=0;
		for(FoodStuff f: food){
			if(f.toString().equals(colour))
				count++;
		}
		return count;
	}
	public int countTotalFood(){
		return food.size();
	}
	public void leave(Amoeba amoeba) {
		amoebas.remove(amoeba);
		
	}
	public boolean isNullSquare() {
		return false;
	}

	public int getHorizontalPosition() {
		return horizontalPosition;
	}

	public int getVerticalPosition() {
		return verticalPosition;
	}

	
	public int countAmoebas() {
		return amoebas.size();
	}

	public void eatFoodStuff(String colour) {
		int counter=0;
		while(counter<3){
			int index =(int) (food.size() * Math.random());
			if(!food.get(index).toString().equals(colour)){
				food.remove(index);
				counter++;
			}
		}
		
	}

}
