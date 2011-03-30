package ursuppe;


import static org.junit.Assert.*;


import java.util.Queue;

import ch.unibe.jexample.JExample;
import ch.unibe.jexample.Given;
import org.junit.Test;


import org.junit.runner.RunWith;

@RunWith(JExample.class)

public class GameTest {
	
	private Player hans;
	private Player fritz;
	private Player nathanael;

	@Test
	public Game newGame() {
		
		Game game = new Game();
		
		Queue<Player> players = game.getPlayers();
		hans=players.remove();
		fritz=players.remove();
		nathanael=players.remove();
		
		assertTrue(players.isEmpty());
		
		assertNotNull(game.getSquare(3, 2));
		
		return game;	
	}
	@Given("newGame")
	public Game testNullSquares(Game game){
		assertNull(game.getSquare(0,0));
		assertNull(game.getSquare(4,0));
		assertNull(game.getSquare(4,1));
		assertNull(game.getSquare(4,2));
		assertNull(game.getSquare(4,4));
		return game;
	}
	@Given("newGame")
	public Game testCompass(Game game){
		assertNull(game.getSquare(2,2));
		return game;
	}
	
	@Given("newGame")
	public Game testFoodstuff(Game game){
		assertTrue(game.getSquare(3,2).countGreenFood()==2);
		assertTrue(game.getSquare(3,2).countBlueFood()==2);
		assertTrue(game.getSquare(3,2).countRedFood()==2);
		assertTrue(game.getSquare(3,2).countTotalFood()==6);
		return game;
	}
	@Given("newGame")
	public Game testAmoebas(Game game){
		assertTrue(hans.countAmoebas()==2);
		assertTrue(fritz.countAmoebas()==2);
		assertTrue(nathanael.countAmoebas()==2);
		return game;
	}
}
