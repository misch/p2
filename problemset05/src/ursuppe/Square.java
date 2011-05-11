package ursuppe;

import java.util.ArrayList;

public class Square implements ISquare {
	
	public enum FoodStuff { 
		green("G"),
		blue("B"),
		red("R");
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
		if(colour.equals("R"))
			addRedFood(quantity);
		else if(colour.equals("B"))
			addBlueFood(quantity);
		else if(colour.equals("G"))
			addGreenFood(quantity);
	}
	
	public String toString(){
		String format="[ %1$-20s, %2$sG %3$sB %4$sR  ] ";
		return String.format(format, amoebas, countFood("G"),countFood("B"),countFood("R"));
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

	public void eatFoodStuff(String colour, int amount) {
		int counter=0;
		while(counter<amount){
			int index =(int) (food.size() * Math.random());
			if(!food.get(index).toString().equals(colour)){
				food.remove(index);
				counter++;
			}
		}
		
	}

}
