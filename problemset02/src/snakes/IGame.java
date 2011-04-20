package snakes;

import java.util.List;



public interface IGame {

	public abstract boolean isValidPosition(int position);

	public abstract void play();

	public abstract boolean notOver();

	public abstract boolean isOver();

	public abstract Player currentPlayer();

	public abstract void movePlayer(int roll);

	public abstract Player winner();

	public abstract ISquare firstSquare();

	public abstract ISquare getSquare(int position);

	public abstract String toString();

	public abstract void setSquareToLadder(int position, int transport);

	public abstract void setSquareToSnake(int position, int transport);

	public abstract void setSquareToLargeSquare(int position);

	public abstract void setSquareToTrapDoor(int position, int transport);

	public abstract ISquare findSquare(int position, int moves);

	public abstract List<Player> getPlayers();

	public abstract IDie getDie();

}