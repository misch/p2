package snakes;
/*DR Take a closer look at http://www.oracle.com/technetwork/java/javase/documentation/index-137868.html ,
* (I know it starts boring but the rest is very interesting and important)
*
* It will tell you to start comments  “with a short summary of what the method/class does
* containing a concise but complete description of the API item”. It also has examples of well-written API documentation.
*
* Write how some methods are connected and have a look @see also try to use tags like <i> </i> and <code> </code> and so on.
*/


/*DR you have to make JavaDoc for the class itself too and try to point out how this class is used, what is important 
 * maybe you can even use {@link } to link to other classes using the Squares and so on
 */
public interface ISquare {
	
	//DR see below
	/**
	 * Returns the position of the square in the game.
	 * The value is �1 and � the size of the game.
	 * @return
	 */
	public int position();
	
	//DR don't talk about Interfaces, use <i> moves </i> for params in the description, try to get auto spelling correction for Eclipse
	/**
	 * Returns the ISquare witch is moves forward.
	 * If it isn't possible to enter this Square, it returns the ISquare witch is to enter (by game rules).
	 * @param moves
	 * @return
	 */
	public ISquare moveAndLand(int moves);
	
	//DR don't talk about Interfaces, use the @return, you tell why this method is important but more details would be nice
	/**
	 * Returns true if the ISquare is the first square of the game.
	 * The first square has the position 1.
	 * @return
	 */
	public boolean isFirstSquare();
	
	//DR don't talk about Interfaces, use the @return, you tell that a player wins and why this square is important that's good
	/**
	 * Returns true if the ISquare is the last square of the game.
	 * If a player lands on the last square, he wins the game.
	 * @return
	 */
	public boolean isLastSquare();
	
	/*DR you used @param but write down which player is submitted, try to point out connections between other methods (with @see)
	 * and write down special cases.
	 */
	/**
	 * Makes the player enter the square.
	 * If the ISquare is occupied (and isn't a LargeSquare) it cannot be entered.
	 * @param player
	 */
	public void enter(Player player);
	 
	/**
	 * Removes the player from the square.
	 * Sets player to null (or removes the player from the players list, if the ISquare is a LargeSquare).
	 * The player has to be on the Square to be removed.
	 * @param player
	 */
	public void leave(Player player);
	
	/*DR write down what the method returns behind @return and above write what the method does, 
	 * how it's used and how it communicates with other methods and classes
	 */
	/**
	 * Returns true if there is a player on the square.
	 * In this case it cannot be entered, except for LargeSquares.
	 * @return
	 */
	public boolean isOccupied();
	
	/*DR don't talk about ISquares just talk about Squares
	 * but well separated how the different types of squares return different things
	 * again what does this method return?
	 */
	/**
	 * Returns the ISquare where the player has to go.
	 * This is either the Square itself or the FirstSquare (if the ISquare is occupied) for normal Squares.
	 * A LargeSquare always returns itself. A TrapDoor returns it's related LargeSquare if it's occupied
	 * (and also sends it's player there). A Snake or a ladder reruns the method on it's related ISquare.
	 * @return
	 */
	public ISquare landHereOrGoHome();
	
	//DR more details, what is this method used for, what does it return?
	/**
	 * Returns true if the ISquare is a LargeSquare.
	 * @return
	 */
	public boolean isLargeSquare();
}
