package ursuppe;
import static org.junit.Assert.*;


import java.util.ArrayList;
import java.util.Queue;

import ch.unibe.jexample.JExample;
import ch.unibe.jexample.Given;
import org.junit.Test;

import org.junit.runner.RunWith;

import ursuppe.Board.WindDirection;
import ursuppe.Game.Colour;

@RunWith(JExample.class)

public class SquareTest {
	
	private Game game = new Game();
	Player player = game.getPlayers().remove();
	
	@Test
	public ISquare newSquare(){
		ISquare square = new Square(game,3,2);
		
		assertTrue(square.getHorizontalPosition()==3);
		assertTrue(square.getVerticalPosition()==2);
		assertTrue(square.countAmoebas()==0);
		assertTrue(square.countTotalFood()==6);
		assertTrue(square.countFood("R")==2);
		assertTrue(square.countFood("B")==2);
		assertTrue(square.countFood("G")==2);
		
		return square;
	}
	
	/*@Given("newSquare")
	public ISquare testEnter(ISquare square){
		square.enter(new Amoeba(game, player, Colour.red, square));
		
		assertTrue(square.countAmoebas()==1);
		
		return square;
	}*/
		
	@Given("newSquare")
	public ISquare testAddFood(ISquare square){
		
		square.addFood("R", 2);
		square.addFood("B", 3);
		square.addFood("G",1);
		assertTrue(square.countFood("R")==4);
		assertTrue(square.countFood("B")==5);
		assertTrue(square.countFood("G")==3);
		assertTrue(square.countTotalFood()==12);
		return square;
	}
	
	@Given("newSquare")
	public ISquare testEatFoodStuff(ISquare square){
		square.eatFoodStuff("R",3);
		assertTrue(square.countFood("R") ==2);
		assertTrue(square.countTotalFood()==3);
		
		return square;
	}
	
}
