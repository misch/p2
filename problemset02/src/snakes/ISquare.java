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
/**
 * Squares compose the board of a {@link Game}. They hold the {@link Player}s of a game and specify where a player has to go, if he moves.
 * <p>A square has to be in a valid position of a game
 */
public interface ISquare {
	
	//DR see below
	/**
	 * Returns the position of the square in the {@link Game}.
	 * The value is between 1 and the size of the Game.
	 * 
	 * @return position the place of the square in the game
	 */
	public int position();
	
	//DR don't talk about Interfaces, use <i> moves </i> for params in the description, try to get auto spelling correction for Eclipse
	/**
	 * Finds out which square a {@link Player} will land on. 
	 * Normally this is the square  <code>moves</code> steps ahead. If it isn't possible to enter this square (the square is occupied and not a largeSquare), it returns the square which it is to enter by using <code>landHereOrGoHome()</code>.
	 * <p><code>moves</code> has to be �0.
	 * 
	 * @param moves 	the number of squares the player should go forward
	 * 
	 * @return			the square the player should land on
	 * 
	 * @see landHereOrGoHome()
	 */
	public ISquare moveAndLand(int moves);
	
	//DR don't talk about Interfaces, use the @return, you tell why this method is important but more details would be nice
	/**
	 * Returns whether this square is the first square of the {@link Game}.
	 * The first square has the position 1. It holds all the {@link Player} in the beginning of the game. If a player wants to land on an already occupied normal square, he is sent back to the firstSquare
	 * 
	 * @return 		<code>true</code> if this square is the first one (position 1);
	 * 					<code>false</code> if the square's position isn't 1
	 */
	public boolean isFirstSquare();
	
	//DR don't talk about Interfaces, use the @return, you tell that a player wins and why this square is important that's good
	/**
	 * Returns true if the square is the last square of the {@link Game}.
	 * If a {@link Player} lands on the last square, he wins the game.
	 * 
	 * @return 		<code>true</code> if this square is the last one;
	 * 					<code>false</code> if it isn't
	 */
	public boolean isLastSquare();
	
	/*DR you used @param but write down which player is submitted, try to point out connections between other methods (with @see)
	 * and write down special cases.
	 */
	/**
	 * Makes the player enter the square.
	 * A square can only be entered if it isn't occupied jet. This doesn't count for the firstSquare and any largeSquare, which can hold several players.
	 * 
	 * @param player	the player which should enter this square
	 */
	public void enter(Player player);
	 
	/**
	 * Removes the player from the square.
	 * Sets player to null (or removes the player from the players list, if the ISquare is a LargeSquare).
	 * The player has to be on the Square to be removed.
	 * 
	 * @param player	the player which should leave this square
	 */
	public void leave(Player player);
	
	/*DR write down what the method returns behind @return and above write what the method does, 
	 * how it's used and how it communicates with other methods and classes
	 */
	/**
	 * Returns true if there is a player on the square.
	 * In this case it cannot be entered, except for {@link LargeSquares}.
	 * 
	 * @return		<code>true</code> if there is a player on this square
	 * 					<code>false</code> if there isn't
	 */
	public boolean isOccupied();
	
	/*DR don't talk about ISquares just talk about Squares
	 * but well separated how the different types of squares return different things
	 * again what does this method return?
	 */
	/**
	 * Finds out where if a {@link Player} can land on this square or has to go elsewhere.
	 * This is either this square itself or the firstSquare (if the square is occupied) for normal squares.
	 * A largeSquare always returns itself. A trapDoor returns it's related largeSquare if it's occupied
	 * (and also sends it's player there). A snake or a ladder reruns the method on it's related square.
	 * 
	 * @return 		the square where the player has to go.
	 */
	public ISquare landHereOrGoHome();
	
	//DR more details, what is this method used for, what does it return?
	/**
	 * Returns true if the square is a LargeSquare.
	 * This method is mainly used to check if it is possible to have several players on this square.
	 * @return		<code>true</code> if this square is a largeSquare
	 * 					<code>false</code> if it isn't
	 */
	public boolean isLargeSquare();
}
