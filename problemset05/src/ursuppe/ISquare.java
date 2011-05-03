package ursuppe;

public interface ISquare {

	public void enter(Amoeba amoeba);

	public String toString();

	public int countFood(String colour);

	public int countTotalFood();

	public void leave(Amoeba amoeba);
	
	public boolean isNullSquare();

	public int countAmoebas();

	public int getHorizontalPosition();

	public int getVerticalPosition();

	public void eatFoodStuff(String string);

	public void addFood(String colour, int quantity);

}