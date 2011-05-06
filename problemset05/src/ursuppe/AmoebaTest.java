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

public class AmoebaTest {
	
	Game game = new Game();
	Player player;
	Colour colour;
	ISquare square;
	
	@Test
	public Amoeba newAmoeba(){
		Queue<Player> players = game.getPlayers();
		player = players.remove();
		colour = player.getColour();
		square = game.getSquare(3,2);
		Amoeba amoeba = new Amoeba(game, player, colour, square);
		
		assertTrue(amoeba.getBioPoints()==36);
		assertTrue(amoeba.getDamagePoints()==0);
		 
		return amoeba;
	}
	
	@Given("newAmoeba")
	public void testSetOnSquare(Amoeba amoeba){
		amoeba.setOnSquare(3,4);
		
		assertTrue(amoeba.getSquare().equals(game.getSquare(3,4)));
	}
	
	@Given("newAmoeba")
	public void testDrift(Amoeba amoeba){

		WindDirection dir = game.getBoard().getWindDirection();
		amoeba.drift();
		
		assertTrue(amoeba.getSquare().equals(game.getSquareInDirection(square, dir)));
	}
	
	@Given("newAmoeba")
	public void testToString(Amoeba amoeba){
		
		assertTrue(amoeba.toString().equals(colour.toString()));
	}
	
	@Given("newAmoeba")
	public void testFeed(Amoeba amoeba){
		
		amoeba.feed();
		
		assertTrue(square.countTotalFood() == 5);
		assertTrue(square.countFood(colour.toString()) == 4);
		
		amoeba.feed();
		
		assertTrue(square.countTotalFood() == 5);
		assertTrue(square.countFood(colour.toString()) == 4);
		assertTrue(amoeba.countDamagePoints() == 1);
	}
	
	@Given("newAmoeba")
	public void testDie(Amoeba amoeba){
		
		amoeba.die();		
		assertNull(amoeba.getSquare());		
	}
}
