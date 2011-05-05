package ursuppe;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Queue;

import ch.unibe.jexample.JExample;
import ch.unibe.jexample.Given;
import org.junit.Test;

import org.junit.runner.RunWith;

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
	public Amoeba testSetOnSquare(Amoeba amoeba){
		return amoeba;
	}
}
