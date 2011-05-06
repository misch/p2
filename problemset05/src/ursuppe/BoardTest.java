package ursuppe;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Queue;

import ch.unibe.jexample.JExample;
import ch.unibe.jexample.Given;
import org.junit.Test;

import org.junit.runner.RunWith;

import ursuppe.Board.WindDirection;

@RunWith(JExample.class)

public class BoardTest {
	Game game = new Game();
	
	
	@Test
	public Board newBoard(){
		Board board = game.getBoard();
				
		for(int i= 0; i<5; i++)
			for(int j = 0; j<5; j++)
				assertNotNull(board.getSquare(i, j));
		
		assertTrue(board.getSquare(0,0).isNullSquare());
		assertTrue(board.getSquare(4,0).isNullSquare());
		assertTrue(board.getSquare(4,1).isNullSquare());
		assertTrue(board.getSquare(4,2).isNullSquare());
		assertTrue(board.getSquare(4,4).isNullSquare());
		assertTrue(board.getSquare(2,2).isNullSquare());
		
		assertFalse(board.getSquare(0,1).isNullSquare());
		assertFalse(board.getSquare(1,2).isNullSquare());
		
		return board;
	}
	
	@Given("newBoard")
	public void testSetWindDirection(Board board){
		
		board.setWindDirection();
		
		assertNotNull(board.getWindDirection());
	}
	
	@Given("newBoard")
	public void testIsInBoardRange(Board board){
		
		assertFalse(board.isInBoardRange(0,0));
		assertFalse(board.isInBoardRange(4,0));
		assertFalse(board.isInBoardRange(4,1));
		assertFalse(board.isInBoardRange(4,2));
		assertFalse(board.isInBoardRange(4,4));
		assertFalse(board.isInBoardRange(2,2));
		
		for(int i=1; i<5; i++)
			for(int j=0; j<4; j++)
				if( !(i==2 && j==2))
					assertTrue(board.isInBoardRange(j,i));
	}
	
	@Given("newBoard")
	public void testGetSquareInWindDirection(Board board){
		
		ISquare square = board.getSquare(3,3);
		WindDirection dir1, dir2, dir3, dir4;
		dir1 = WindDirection.nord;
		dir2 = WindDirection.east;
		dir3 = WindDirection.south;
		dir4 = WindDirection.west;
		
		assertTrue(board.getSquareInDirection(square, dir1).equals(board.getSquare(2,3)));
		assertTrue(board.getSquareInDirection(square, dir2).equals(board.getSquare(3,4)));
		assertTrue(board.getSquareInDirection(square, dir3).equals(board.getSquare(4,3)));
		assertTrue(board.getSquareInDirection(square, dir4).equals(board.getSquare(3,2)));
	}
	
	@Given("newBoard")
	public void testGetNeighbourSquares(Board board){
		
		ISquare square1 = board.getSquare(2,4);
		ISquare square2 = board.getSquare(1,0);
		ISquare square3 = board.getSquare(4,3);
		ISquare square4 = board.getSquare(3,3);
		
		
		ArrayList<ISquare> neighbours1 = board.getNeighbourSquares(square1);
		ArrayList<ISquare> neighbours2 = board.getNeighbourSquares(square2);
		ArrayList<ISquare> neighbours3 = board.getNeighbourSquares(square3);
		ArrayList<ISquare> neighbours4 = board.getNeighbourSquares(square4);
		
		assertTrue(neighbours1.size() == 3);
		assertTrue(neighbours2.size() == 2);
		assertTrue(neighbours3.size() == 1);
		assertTrue(neighbours4.size() == 4);
	}
}
