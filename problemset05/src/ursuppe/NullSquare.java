package ursuppe;

public class NullSquare implements ISquare {
	private int horizontalPosition;
	private int verticalPosition;
	
	public NullSquare(int horizontal, int vertical){
		this.horizontalPosition=horizontal;
		this.verticalPosition=vertical;
	}
	public NullSquare(){
		
	}

	public void enter(Amoeba amoeba) {
	
	}

	public int countFood(String colour) {
		return 0;
	}

	public int countTotalFood() {
		return 0;
	}

	public void leave(Amoeba amoeba) {
	}

	public boolean isNullSquare() {
		return true;
	}
	
	public String toString(){
		return "|_________________________________| ";
	}

	public int countAmoebas() {
		return 0;
	}

	public int getHorizontalPosition() {
		return this.horizontalPosition;
	}

	public int getVerticalPosition() {
		return this.verticalPosition;
	}
	public void addGreenFood(int quantity) {
	}
	public void addBlueFood(int quantity) {
	}
	public void addRedFood(int quantity) {
	}
	public void addFood(String colour, int quantity) {
	}
	public void eatFoodStuff(String string, int amount) {
	}

}
