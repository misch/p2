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

public class PlayerTest {
	
	Game game = new Game();
	Board board = game.getBoard();
	
	@Test
	public Player newPlayer(){
		Player player = new Player(game, board, "player", Colour.red);
		
		assertTrue(player.getAmoebas().size()==0);
		assertTrue(player.getBioPoints()==0);
		assertTrue(player.getColour().toString().equals("red"));
		assertTrue(player.getName().equals("player"));
		assertTrue(player.getScore()==0);
		return player;
	}
	
	@Given("newPlayer")
	public Player testInitAmoeba(Player player){
		player.initAmoeba();
		assertTrue(player.countAmoebas() == 1);
		
		return player;
		
	}
	
	@Given("newPlayer")
	public Player testAddScore(Player player){
		player.addScore(3);
		assertTrue(player.getScore()==3);
		return player;
	}
	
	@Given("testInitAmoeba")
	public Player testMoveAndFeedAmoebas(Player player){
		player.moveAndFeedAmoebas();
		assertTrue(player.getAmoebas().get(0).getSquare().countFood("red")==4);
		assertTrue(player.getAmoebas().get(0).getSquare().countTotalFood()==5);
		
		return player;
	}
	
	@Given("testInitAmoeba")
	public Player testAddBiopoints(Player player){
		player.addBiopoints(10);
		assertTrue(player.getBioPoints()==10);
		return player;
	}
	
	@Given("testAddBiopoints")
	public Player testDivideAmoebas(Player player){
		player.divideAmoebas();
		assertTrue(player.getBioPoints()==4);
		assertTrue(player.countAmoebas()==2);
		
		player.addBiopoints(8);
		player.divideAmoebas();
		assertTrue(player.getBioPoints()==0);
		assertTrue(player.countAmoebas()==4);
		
		return player;
	}
}
