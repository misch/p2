package snakes;

public interface ISquare {
	/**
	 * Returns the position of the square in the game.
	 * The value is ³1 and ² the size of the game.
	 * @return
	 */
	public int position();
	/**
	 * Returns the ISquare witch is moves forward.
	 * If it isn't possible to enter this Square, it returns the ISquare witch is to enter (by game rules).
	 * @param moves
	 * @return
	 */
	public ISquare moveAndLand(int moves);
	/**
	 * Returns true if the ISquare is the first square of the game.
	 * The first square has the position 1.
	 * @return
	 */
	public boolean isFirstSquare();
	/**
	 * Returns true if the ISquare is the last square of the game.
	 * If a player lands on the last square, he wins the game.
	 * @return
	 */
	public boolean isLastSquare();
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
	/**
	 * Returns true if there is a player on the square.
	 * In this case it cannot be entered, except for LargeSquares.
	 * @return
	 */
	public boolean isOccupied();
	/**
	 * Returns the ISquare where the player has to go.
	 * This is either the Square itself or the FirstSquare (if the ISquare is occupied) for normal Squares.
	 * A LargeSquare always returns itself. A TrapDoor returns it's related LargeSquare if it's occupied
	 * (and also sends it's player there). A Snake or a ladder reruns the method on it's related ISquare.
	 * @return
	 */
	public ISquare landHereOrGoHome();
	/**
	 * Returns true if the ISquare is a LargeSquare.
	 * @return
	 */
	public boolean isLargeSquare();
}
