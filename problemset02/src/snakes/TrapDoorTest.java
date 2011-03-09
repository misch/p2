package snakes;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;
import ch.unibe.jexample.JExample;
import ch.unibe.jexample.Given;
import org.junit.Test;

import org.junit.runner.RunWith;

@RunWith(JExample.class)
public class TrapDoorTest {
	
	private Player jack;
	private Player jill;

	@Test
	public Game newGame() {
		jack = new Player("Jack");
		jill = new Player("Jill");
		Player[] args = { jack, jill };
		Game game = new Game(6, args);
		game.setSquareToLargeSquare(4);//had to change the sequence, (no TrapDoor without LargeSquare)
		game.setSquareToTrapDoor(2, 2);
		assertTrue(game.notOver());
		assertTrue(game.firstSquare().isOccupied());
		assertEquals(1, jack.position());
		assertEquals(1, jill.position());
		assertEquals(jack, game.currentPlayer());
		return game;
	}
	
	@Given("newGame")
	public Game jackToTrapDoor(Game game) {
		game.movePlayer(1);
		assertTrue(game.notOver());
		assertEquals(2, jack.position());
		assertEquals(1, jill.position());
		assertEquals(jill, game.currentPlayer());
		return game;
	}
	
	@Given("jackToTrapDoor")
	public Game jillToTrapDoor(Game game) {
		game.movePlayer(1);
		assertTrue(game.notOver());
		assertEquals(4, jack.position());
		assertEquals(4, jill.position());
		assertEquals(jack, game.currentPlayer());
		return game;
	}
	@Given("jillToTrapDoor")
	public Game checkStrings(Game game){
		assertEquals("[2==>4]", game.getSquare(2).toString());
		assertEquals("[1][2==>4][3][|4|<Jack><Jill>][5][6]", game.toString());
		return game;
	}
}
